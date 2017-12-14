package com.gqfbtc.mvp.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.circledialog.CircleDialog;
import com.circledialog.callback.ConfigButton;
import com.circledialog.callback.ConfigItems;
import com.circledialog.callback.ConfigTitle;
import com.circledialog.params.ButtonParams;
import com.circledialog.params.ItemsParams;
import com.circledialog.params.TitleParams;
import com.fivefivelike.mybaselibrary.base.BasePullFragment;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.gqfbtc.R;
import com.gqfbtc.Utils.UiHeplUtils;
import com.gqfbtc.adapter.WithdrawalRecordAdapter;
import com.gqfbtc.entity.bean.PaymentBTCETHAddress;
import com.gqfbtc.entity.bean.Withdrawal;
import com.gqfbtc.greenDaoUtils.DBUtils;
import com.gqfbtc.greenDaoUtils.SingSettingDBUtil;
import com.gqfbtc.mvp.activity.user.MyAddressActivity;
import com.gqfbtc.mvp.databinder.BaseFragmentPullBinder;
import com.gqfbtc.mvp.delegate.BaseFragentPullDelegate;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 郭青枫 on 2017/10/21.
 */

public class AssetsBtcWithdrawalFragment extends BasePullFragment<BaseFragentPullDelegate, BaseFragmentPullBinder> {

    List<Withdrawal> defDatas;
    WithdrawalRecordAdapter recordAdapter;
    HeaderAndFooterWrapper adapter;
    List<PaymentBTCETHAddress> paymentBTCETHAddresses;
    String withdrawFee;
    BigDecimal rate;

    public void setWithdrawFee(String withdrawFee) {
        this.withdrawFee = withdrawFee;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initList();
        getAddress();

    }

    private void initList() {
        defDatas = new ArrayList<>();
        viewDelegate.setNoDataTxt("暂无提现记录");
        recordAdapter = new WithdrawalRecordAdapter(getActivity(), defDatas);
        adapter = new HeaderAndFooterWrapper(recordAdapter);
        adapter.addHeaderView(initHeaderView());
        initRecycleViewPull(adapter, adapter.getHeadersCount(), new LinearLayoutManager(getActivity()));
        viewDelegate.setIsPullDown(false);
    }

    private void getAddress() {
        if (SingSettingDBUtil.isUser()) {
            addRequest(binder.getCoinAddressList(3 + "", this));
        } else {
            addRequest(binder.ic_getIntermediaryBeneBankInfo(3 + "", this));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (paymentBTCETHAddresses != null) {
            paymentBTCETHAddresses = DBUtils.getAllCoinBTCAddress();
            getAddress();
        }
    }

    EditText et_withdrawal_money;
    LinearLayout lin_poundage;
    TextView tv_poundage;
    TextView tv_commit;
    TextView tv_withdrawal_address;

    public BigDecimal getRate() {
        return rate;
    }

    private View initHeaderView() {
        View view = getActivity().getLayoutInflater().inflate(R.layout.layout_withdrawal, null);
        view.findViewById(R.id.tv_address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyAddressActivity.class));
            }
        });
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        et_withdrawal_money = view.findViewById(R.id.et_withdrawal_money);
        lin_poundage = view.findViewById(R.id.lin_poundage);
        tv_commit = view.findViewById(R.id.tv_commit);
        lin_poundage.setVisibility(View.GONE);
        tv_withdrawal_address = view.findViewById(R.id.tv_withdrawal_address);
        tv_poundage = view.findViewById(R.id.tv_poundage);
        //费率
        et_withdrawal_money.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editable)) {
                    lin_poundage.setVisibility(View.GONE);
                } else {
                    lin_poundage.setVisibility(View.VISIBLE);
                    if (TextUtils.isEmpty(withdrawFee)) {
                        rate = new BigDecimal("0.0005");
                    } else {
                        rate = new BigDecimal(withdrawFee);
                    }
                    tv_poundage.setText(rate.toString() + "BTC");
                }
            }
        });
        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                withdrawCommit();
            }
        });
        tv_withdrawal_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (paymentBTCETHAddresses != null && paymentBTCETHAddresses.size() != 0) {
                    showAddress();
                } else {
                    ToastUtil.show("您还没有提币地址，请在个人中心中添加");
                }
            }
        });
        return view;
    }

    private void withdrawCommit() {
        if (selectPaymentBTCETHAddress == null) {
            ToastUtil.show("请选择一个提币地址");
            return;
        }
        if (
                !UiHeplUtils.judgeRequestContentIsNull(et_withdrawal_money.getText().toString(), "请输入提币数量")
                ) {
            addRequest(binder.deposit_add(selectPaymentBTCETHAddress.getCollectionAddr() + "", et_withdrawal_money.getText().toString(), rate.toString(), this));
        }
    }

    @Override
    public BaseFragmentPullBinder getDataBinder(BaseFragentPullDelegate viewDelegate) {
        return new BaseFragmentPullBinder(viewDelegate);
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                onRefresh();
                paymentBTCETHAddresses = GsonUtil.getInstance().toList(data, PaymentBTCETHAddress.class);
                if (selectPaymentBTCETHAddress != null && !TextUtils.isEmpty(tv_withdrawal_address.getText().toString())) {
                    if (paymentBTCETHAddresses.size() != 0) {
                        tv_withdrawal_address.setText("");
                        for (int i = 0; i < paymentBTCETHAddresses.size(); i++) {
                            if (selectPaymentBTCETHAddress.getId() == paymentBTCETHAddresses.get(i).getId()) {
                                tv_withdrawal_address.setText(selectPaymentBTCETHAddress.getAlias() + selectPaymentBTCETHAddress.getCollectionAddr());
                                break;
                            }
                        }
                    } else {
                        selectPaymentBTCETHAddress = null;
                        tv_withdrawal_address.setText("");
                    }
                }
                break;
            case 0x124:
                List<Withdrawal> list = GsonUtil.getInstance().toList(data, "list", Withdrawal.class);
                getDataBack(defDatas, list, adapter);
                break;
            case 0x125:
                UiHeplUtils.initDefaultToastDialog(getActivity(), info, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).show();
                break;
        }
    }

    List<String> address;
    PaymentBTCETHAddress selectPaymentBTCETHAddress;

    private void showAddress() {
        address = new ArrayList<>();
        for (int i = 0; i < paymentBTCETHAddresses.size(); i++) {
            address.add(paymentBTCETHAddresses.get(i).getAlias() + paymentBTCETHAddresses.get(i).getCollectionAddr());
        }
        new CircleDialog.Builder(getActivity())
                .setWidth(0.9f)
                .setTitle("请选择一个收币地址")
                .configTitle(new ConfigTitle() {
                    @Override
                    public void onConfig(TitleParams params) {
                        params.backgroundColor = getResources().getColor(R.color.white);
                    }
                })
                .setItems(address.toArray(new String[address.size()]), new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        selectPaymentBTCETHAddress = paymentBTCETHAddresses.get(i - 1);
                        tv_withdrawal_address.setText(address.get(i - 1));
                    }
                })
                .configItems(new ConfigItems() {
                    @Override
                    public void onConfig(ItemsParams params) {
                        params.textColor = getResources().getColor(R.color.color_font1);
                        params.textSize = getResources().getDimensionPixelSize(R.dimen.text_trans_26px);
                        params.backgroundColor = getResources().getColor(R.color.white);
                    }
                })
                .setNegative("取消", null)
                .configNegative(new ConfigButton() {
                    @Override
                    public void onConfig(ButtonParams params) {
                        //取消按钮字体颜色
                        params.textColor = getResources().getColor(R.color.color_font3);
                        params.textSize = getResources().getDimensionPixelSize(R.dimen.text_trans_30px);
                        params.backgroundColor = getResources().getColor(R.color.white);
                    }
                }).show();
    }

    @Override
    protected Class<BaseFragentPullDelegate> getDelegateClass() {
        return BaseFragentPullDelegate.class;
    }


    @Override
    protected void refreshData() {
        addRequest(binder.withdraw(this));
    }
}

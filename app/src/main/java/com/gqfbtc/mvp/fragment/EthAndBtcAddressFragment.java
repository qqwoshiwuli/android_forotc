package com.gqfbtc.mvp.fragment;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BasePullFragment;
import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.gqfbtc.R;
import com.gqfbtc.Utils.UiHeplUtils;
import com.gqfbtc.adapter.MyAddressAdapter;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.gqfbtc.entity.bean.PaymentBTCETHAddress;
import com.gqfbtc.greenDaoUtils.DBUtils;
import com.gqfbtc.greenDaoUtils.SingSettingDBUtil;
import com.gqfbtc.mvp.activity.main.GetCodeActivity;
import com.gqfbtc.mvp.databinder.BaseFragmentPullBinder;
import com.gqfbtc.mvp.delegate.BaseFragentPullDelegate;

import java.util.ArrayList;
import java.util.List;

import static com.gqfbtc.mvp.activity.main.GetCodeActivity.codeKey;


public class EthAndBtcAddressFragment extends BasePullFragment<BaseFragentPullDelegate, BaseFragmentPullBinder> {

    public static final int type_collection = 3;
    public static final int type_eth = 2;
    public static final int type_btc = 1;

    int type;//1. btc地址// 2.eth地址  3.收款地址

    List<PaymentBTCETHAddress> defDatas;
    MyAddressAdapter adapter;
    PaymentBTCETHAddress delectAddress;


    public interface Linsener {
        void setResultOk();
    }

    Linsener linsener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        linsener = (Linsener) activity;
    }

    @Override
    protected Class<BaseFragentPullDelegate> getDelegateClass() {
        return BaseFragentPullDelegate.class;
    }

    public static EthAndBtcAddressFragment newInstance(
            int type) {
        EthAndBtcAddressFragment newFragment = new EthAndBtcAddressFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ((savedInstanceState != null)
                && savedInstanceState.containsKey("type")) {
            type = savedInstanceState.getInt("type");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("type", type);
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        Bundle arguments = getArguments();
        type = arguments.getInt("type");
        initList();
    }


    private void initList() {
        //viewDelegate.setShowNoData(false);
        defDatas = new ArrayList<>();
        adapter = new MyAddressAdapter(getActivity(), defDatas);
        adapter.setType(type);
        if (type == EthAndBtcAddressFragment.type_btc) {
            viewDelegate.setNoDataTxt(CommonUtils.getString(R.string.str_nodata_btcaddress));
        } else if (type == EthAndBtcAddressFragment.type_eth) {

        } else if (type == EthAndBtcAddressFragment.type_collection) {
            viewDelegate.setNoDataTxt(getResources().getString(R.string.str_nodata_moneyaddress));
        }
        initRecycleViewPull(adapter, new LinearLayoutManager(getActivity()));
        viewDelegate.setIsLoadMore(false);
        viewDelegate.viewHolder.swipeRefreshLayout.setRefreshing(true);
        onRefresh();
        adapter.setDefaultClickLinsener(new DefaultClickLinsener() {
            @Override
            public void onClick(View view, int position, Object item) {
                if (type == EthAndBtcAddressFragment.type_btc) {
                    if (view.getId() == R.id.lin_delect) {
                        //删除
                        initDelectDialog((PaymentBTCETHAddress) item);
                    } else {
                        //拷贝
                        copy((PaymentBTCETHAddress) item);
                    }
                } else if (type == EthAndBtcAddressFragment.type_eth) {

                } else if (type == EthAndBtcAddressFragment.type_collection) {
                    //删除
                    initDelectDialog((PaymentBTCETHAddress) item);
                }
            }
        });
    }

    private void copy(PaymentBTCETHAddress s) {
        ClipboardManager mClipboardManager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData myClip = ClipData.newPlainText("text", s.getCollectionAddr());
        mClipboardManager.setPrimaryClip(myClip);
        ToastUtil.show("已复制地址到粘贴板");
    }

    private void initDelectDialog(PaymentBTCETHAddress s) {
        String title = null;
        if (type == EthAndBtcAddressFragment.type_btc) {
            title = s.getAlias();
        } else if (type == EthAndBtcAddressFragment.type_eth) {

        } else if (type == EthAndBtcAddressFragment.type_collection) {
            title = s.getBankName() + s.getBranchName();
        }
        delectAddress = s;
        UiHeplUtils.initDefaultDialog(getActivity(), "是否删除地址：" + title, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetCodeActivity.startActBuyFrag(EthAndBtcAddressFragment.this, 0x123);
            }
        }).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            String code = data.getStringExtra(codeKey);
            if (SingSettingDBUtil.isUser()) {
                addRequest(binder.delUserAddress(delectAddress.getId() + "", code, this));
            } else {
                addRequest(binder.ic_dealIntermediaryBeneBankInfo(delectAddress.getId() + "", code, this));
            }
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
                List<PaymentBTCETHAddress> list;
                list = GsonUtil.getInstance().toList(data, PaymentBTCETHAddress.class);
                getDataBack(defDatas, list, adapter);
                if (type == type_btc) {
                    DBUtils.setUserCoinBTCAddressList(list);
                } else if (type == type_eth) {

                } else if (type == type_collection) {

                }
                break;
            case 0x124:
                //删除成功
                DBUtils.delectUserCoinBTCAddress(delectAddress);
                onRefresh();
                linsener.setResultOk();
                break;
        }
    }

    @Override
    protected void refreshData() {
        viewDelegate.viewHolder.swipeRefreshLayout.setRefreshing(true);
        if (SingSettingDBUtil.isUser()) {
            //用户
            if (type == type_btc) {
                addRequest(binder.getCoinAddressList(3 + "", this));
            } else if (type == type_eth) {
                addRequest(binder.getCoinAddressList(4 + "", this));
            } else if (type == type_collection) {
                addRequest(binder.getPaymentAddressList(this));
            }
        } else {
            if (type == type_btc) {
                addRequest(binder.ic_getIntermediaryBeneBankInfo(3 + "", this));
            } else if (type == type_eth) {
                addRequest(binder.ic_getIntermediaryBeneBankInfo(4 + "", this));
            } else if (type == type_collection) {
                addRequest(binder.ic_getIntermediaryBeneBankInfo(null, this));
            }
        }

    }
}

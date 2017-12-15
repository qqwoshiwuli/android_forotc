package com.gqfbtc.mvp.activity.posted;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.gqfbtc.Utils.UiHeplUtils;
import com.gqfbtc.adapter.TransactAdvertisingAdapter;
import com.gqfbtc.entity.bean.BeforeSaveAd;
import com.gqfbtc.entity.bean.HomeAdvertising;
import com.gqfbtc.entity.bean.PaymentBTCETHAddress;
import com.gqfbtc.mvp.activity.SuccessActivity;
import com.gqfbtc.mvp.activity.user.CollectionAddressActivity;
import com.gqfbtc.mvp.databinder.AdvertisingBuyAndSellBinder;
import com.gqfbtc.mvp.delegate.AdvertisingBuyAndSellDelegate;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.List;


public class AdvertisingSellActivity extends BaseDataBindActivity<AdvertisingBuyAndSellDelegate, AdvertisingBuyAndSellBinder> {

    public static final String sell_type_common = "sell_type_common";
    public static final String sell_type_safe = "sell_type_safe";
    List<PaymentBTCETHAddress> paymentBTCETHAddresses;

    TransactAdvertisingAdapter transactAdvertisingAdapter;

    @Override
    protected Class<AdvertisingBuyAndSellDelegate> getDelegateClass() {
        return AdvertisingBuyAndSellDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        getIntentData();
        initToolbar(new ToolbarBuilder().setTitle("卖BTC").setSubTitle("帮助"));
        if (type.equals(sell_type_common)) {
            viewDelegate.initCommonSell();
        } else {
            viewDelegate.initSafeSell();
        }
        beforeSaveAd();
        viewDelegate.viewHolder.tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commit();
            }
        });

    }

    private void commit() {
        if (viewDelegate.isSafe()) {
            if (viewDelegate.viewHolder.tv_toggle.isChecked()) {
                //安全固定卖
                if (
                        !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_unit_price.getText().toString(), "请输入单价") &&
                                !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_btc.getText().toString(), "请输入卖出数量") &&
                                !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.chooseday1, "请输入选择开放时间") &&
                                !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_starting_buy.getText().toString(), "请输入最低买入")
                        ) {
                    if (transactAdvertisingAdapter.getSelectPositions().size() != 0) {
                        addRequest(binder.saveAd7(
                                "1",
                                viewDelegate.viewHolder.et_unit_price.getText().toString(),
                                viewDelegate.viewHolder.et_btc.getText().toString(),
                                viewDelegate.viewHolder.et_remark.getText().toString(),
                                viewDelegate.chooseday1 + "_" + viewDelegate.choosetime1,
                                viewDelegate.chooseday2 + "_" + viewDelegate.choosetime2,
                                viewDelegate.viewHolder.et_starting_buy.getText().toString(),
                                transactAdvertisingAdapter.getSelectId(),
                                this
                        ));
                    } else {
                        ToastUtil.show("请选择收款地址");
                    }
                }
            } else {
                if (
                        !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_premium.getText().toString(), "请输入溢价比") &&
                                !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_btc.getText().toString(), "请输入卖出数量") &&
                                !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.chooseday1, "请输入选择开放时间") &&
                                !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_starting_buy.getText().toString(), "请输入最低买入")
                        ) {
                    if (transactAdvertisingAdapter.getSelectPositions().size() != 0) {
                        //安全浮动卖
                        addRequest(binder.saveAd8(
                                "1",
                                viewDelegate.viewHolder.et_premium.getText().toString(),
                                viewDelegate.viewHolder.et_unit_price.getText().toString(),
                                viewDelegate.viewHolder.et_btc.getText().toString(),
                                viewDelegate.viewHolder.et_remark.getText().toString(),
                                viewDelegate.chooseday1 + "_" + viewDelegate.choosetime1,
                                viewDelegate.chooseday2 + "_" + viewDelegate.choosetime2,
                                viewDelegate.viewHolder.et_starting_buy.getText().toString(),
                                !TextUtils.isEmpty(viewDelegate.viewHolder.et_upper_limit_price.getText().toString()) ? viewDelegate.viewHolder.et_upper_limit_price.getText().toString() : "0",
                                transactAdvertisingAdapter.getSelectId(),
                                this
                        ));
                    } else {
                        ToastUtil.show("请选择收款地址");
                    }
                }
            }
        } else {
            if (viewDelegate.viewHolder.tv_toggle.isChecked()) {
                if (
                        !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_unit_price.getText().toString(), "请输入单价") &&
                                !UiHeplUtils.judgeRequestContentIsNull(transactAdvertisingAdapter.getSelectId(), "请选择一个收款账号") &&
                                !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_btc.getText().toString(), "请输入卖出数量")
                        ) {
                    //普通固定卖
                    addRequest(binder.saveAd5(
                            "1",
                            viewDelegate.viewHolder.et_unit_price.getText().toString(),
                            viewDelegate.viewHolder.et_btc.getText().toString(),
                            viewDelegate.viewHolder.et_remark.getText().toString(),
                            "100",
                            transactAdvertisingAdapter.getSelectId(),
                            this
                    ));
                }
            } else {
                if (
                        !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_premium.getText().toString(), "请输入溢价比") &&
                                !UiHeplUtils.judgeRequestContentIsNull(transactAdvertisingAdapter.getSelectId(), "请选择一个收款账号") &&
                                !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_btc.getText().toString(), "请输入卖出数量")
                        ) {
                    //普通浮动卖
                    addRequest(binder.saveAd6(
                            "1",
                            viewDelegate.viewHolder.et_premium.getText().toString(),
                            viewDelegate.viewHolder.et_unit_price.getText().toString(),
                            viewDelegate.viewHolder.et_btc.getText().toString(),
                            viewDelegate.viewHolder.et_remark.getText().toString(),
                            "100",
                            !TextUtils.isEmpty(viewDelegate.viewHolder.et_upper_limit_price.getText().toString()) ? viewDelegate.viewHolder.et_upper_limit_price.getText().toString() : "0",
                            transactAdvertisingAdapter.getSelectId(),
                            this
                    ));
                }
            }
        }
    }

    private void beforeSaveAd() {
        viewDelegate.beforeSaveAd(beforeSaveAd);
        viewDelegate.viewHolder.tv_administer_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CollectionAddressActivity.startAct(AdvertisingSellActivity.this, 0x123);
            }
        });
        if (beforeSaveAd.getBeneBanks() != null && beforeSaveAd.getBeneBanks().size() != 0) {
            paymentBTCETHAddresses = beforeSaveAd.getBeneBanks();
            initAddress();
        } else {
            addRequest(binder.getPaymentAddressList(this));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (transactAdvertisingAdapter != null) {
            addRequest(binder.getPaymentAddressList(this));
        }
    }

    public static void startAct(Activity activity,
                                String type,
                                BeforeSaveAd beforeSaveAd) {
        Intent intent = new Intent(activity, AdvertisingSellActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("beforeSaveAd", beforeSaveAd);
        activity.startActivity(intent);
    }

    private String type;
    private BeforeSaveAd beforeSaveAd;

    private void getIntentData() {
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        beforeSaveAd = intent.getParcelableExtra("beforeSaveAd");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_OK) {
            addRequest(binder.getPaymentAddressList(this));
        }
    }

    private void initAddress() {
        viewDelegate.viewHolder.rv_address.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        transactAdvertisingAdapter = new TransactAdvertisingAdapter(this, paymentBTCETHAddresses);
        viewDelegate.viewHolder.rv_address.setAdapter(transactAdvertisingAdapter);
        transactAdvertisingAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                transactAdvertisingAdapter.setSelectPositions(position);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    @Override
    public AdvertisingBuyAndSellBinder getDataBinder(AdvertisingBuyAndSellDelegate viewDelegate) {
        return new AdvertisingBuyAndSellBinder(viewDelegate);
    }

    HomeAdvertising advertising;

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                //发布广告成功
                advertising = GsonUtil.getInstance().toObj(data, HomeAdvertising.class);
                SuccessActivity.startActWithAdvertising(AdvertisingSellActivity.this, advertising, SuccessActivity.INTENT_SUCCESS_ADVERTISING, 0x123);
                break;
            case 0x124:
                //地址列表
                paymentBTCETHAddresses = GsonUtil.getInstance().toList(data, PaymentBTCETHAddress.class);
                initAddress();
                break;
        }
    }
}

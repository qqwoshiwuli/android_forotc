package com.gqfbtc.mvp.activity.posted;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.gqfbtc.Utils.UiHeplUtils;
import com.gqfbtc.dialog.AddressDialog;
import com.gqfbtc.entity.bean.BeforeSaveAd;
import com.gqfbtc.entity.bean.HomeAdvertising;
import com.gqfbtc.entity.bean.PaymentBTCETHAddress;
import com.gqfbtc.mvp.activity.SuccessActivity;
import com.gqfbtc.mvp.databinder.AdvertisingBuyAndSellBinder;
import com.gqfbtc.mvp.delegate.AdvertisingBuyAndSellDelegate;

import java.util.List;


public class AdvertisingBuyActivity extends BaseDataBindActivity<AdvertisingBuyAndSellDelegate, AdvertisingBuyAndSellBinder> {

    public static final String buy_type_common = "buy_type_common";
    public static final String buy_type_safe = "buy_type_safe";
    List<PaymentBTCETHAddress> paymentBTCETHAddresses;
    PaymentBTCETHAddress selectPaymentBTCETHAddress;

    @Override
    protected Class<AdvertisingBuyAndSellDelegate> getDelegateClass() {
        return AdvertisingBuyAndSellDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        getIntentData();
        initToolbar(new ToolbarBuilder().setTitle("买BTC").setSubTitle("帮助"));
        if (type.equals(buy_type_common)) {
            viewDelegate.initCommonBuy();
        } else {
            viewDelegate.initSafeBuy();
        }
        beforeSaveAd();
        viewDelegate.viewHolder.tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commit();

            }
        });

    }


    private void beforeSaveAd() {
        viewDelegate.beforeSaveAd(beforeSaveAd);
        if (viewDelegate.isSafe()) {
            //获取收币地址
            addRequest(binder.getCoinAddressList("3", this));
        }
        viewDelegate.viewHolder.tv_addressr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddressDialog();
            }
        });
    }

    private void showAddressDialog() {
        new AddressDialog(this)
                .setDefaultClickLinsener(new DefaultClickLinsener() {
                    @Override
                    public void onClick(View view, int position, Object item) {
                        selectPaymentBTCETHAddress = (PaymentBTCETHAddress) item;
                        viewDelegate.viewHolder.tv_addressr.setText(selectPaymentBTCETHAddress.getAlias());
                    }
                })
                .setDimessStr("取消")
                .showWithData(paymentBTCETHAddresses);
    }

    private void commit() {
        if (viewDelegate.isSafe()) {
            if (viewDelegate.viewHolder.tv_toggle.isChecked()) {
                //安全固定买
                if (
                        !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_unit_price.getText().toString(), "请输入单价") &&
                                !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_btc.getText().toString(), "请输入购买数量") &&
                                !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.chooseday1, "请输入选择开放时间") &&
                                !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_starting_buy.getText().toString(), "请输入最低买入")
                        ) {
                    if (selectPaymentBTCETHAddress != null) {
                        addRequest(binder.saveAd3(
                                "1",
                                viewDelegate.viewHolder.et_unit_price.getText().toString(),
                                viewDelegate.viewHolder.et_btc.getText().toString(),
                                viewDelegate.viewHolder.et_remark.getText().toString(),
                                viewDelegate.chooseday1 + "_" + viewDelegate.choosetime1,
                                viewDelegate.chooseday2 + "_" + viewDelegate.choosetime2,
                                viewDelegate.viewHolder.et_starting_buy.getText().toString(),
                                selectPaymentBTCETHAddress.getId() + "",
                                this
                        ));
                    } else {
                        ToastUtil.show("请选择收币地址");
                    }
                }
            } else {
                if (
                        !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_premium.getText().toString(), "请输入溢价比") &&
                                !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_btc.getText().toString(), "请输入购买数量") &&
                                !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.chooseday1, "请输入选择开放时间") &&
                                !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_starting_buy.getText().toString(), "请输入最低买入")
                        ) {
                    if (selectPaymentBTCETHAddress != null) {
                        //安全浮动买
                        addRequest(binder.saveAd4(
                                "1",
                                viewDelegate.viewHolder.et_premium.getText().toString(),
                                viewDelegate.viewHolder.et_unit_price.getText().toString(),
                                viewDelegate.viewHolder.et_btc.getText().toString(),
                                viewDelegate.viewHolder.et_remark.getText().toString(),
                                viewDelegate.chooseday1 + "_" + viewDelegate.choosetime1,
                                viewDelegate.chooseday2 + "_" + viewDelegate.choosetime2,
                                viewDelegate.viewHolder.et_starting_buy.getText().toString(),
                                !TextUtils.isEmpty(viewDelegate.viewHolder.et_upper_limit_price.getText().toString()) ? viewDelegate.viewHolder.et_upper_limit_price.getText().toString() : "0",
                                selectPaymentBTCETHAddress.getId() + "",
                                this
                        ));
                    } else {
                        ToastUtil.show("请选择收币地址");
                    }
                }
            }
        } else {
            if (viewDelegate.viewHolder.tv_toggle.isChecked()) {
                if (
                        !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_unit_price.getText().toString(), "请输入单价") &&
                                !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_btc.getText().toString(), "请输入购买数量") &&
                                !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.chooseday1, "请输入选择开放时间") &&
                                !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_starting_buy.getText().toString(), "请输入最低买入")
                        ) {
                    //普通固定买
                    addRequest(binder.saveAd1(
                            "1",
                            viewDelegate.viewHolder.et_unit_price.getText().toString(),
                            viewDelegate.viewHolder.et_btc.getText().toString(),
                            viewDelegate.viewHolder.et_remark.getText().toString(),
                            viewDelegate.chooseday1 + "_" + viewDelegate.choosetime1,
                            viewDelegate.chooseday2 + "_" + viewDelegate.choosetime2,
                            viewDelegate.viewHolder.et_starting_buy.getText().toString(),
                            this
                    ));
                }
            } else {
                if (
                        !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_premium.getText().toString(), "请输入溢价比") &&
                                !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_btc.getText().toString(), "请输入购买数量") &&
                                !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.chooseday1, "请输入选择开放时间") &&
                                !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_starting_buy.getText().toString(), "请输入最低买入")
                        ) {
                    //普通浮动买
                    addRequest(binder.saveAd2(
                            "1",
                            viewDelegate.viewHolder.et_premium.getText().toString(),
                            viewDelegate.viewHolder.et_unit_price.getText().toString(),
                            viewDelegate.viewHolder.et_btc.getText().toString(),
                            viewDelegate.viewHolder.et_remark.getText().toString(),
                            viewDelegate.chooseday1 + "_" + viewDelegate.choosetime1,
                            viewDelegate.chooseday2 + "_" + viewDelegate.choosetime2,
                            viewDelegate.viewHolder.et_starting_buy.getText().toString(),
                            !TextUtils.isEmpty(viewDelegate.viewHolder.et_upper_limit_price.getText().toString()) ? viewDelegate.viewHolder.et_upper_limit_price.getText().toString() : "0",
                            this
                    ));
                }
            }
        }
    }


    public static void startAct(Activity activity,
                                String type,
                                BeforeSaveAd beforeSaveAd) {
        Intent intent = new Intent(activity, AdvertisingBuyActivity.class);
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
    public AdvertisingBuyAndSellBinder getDataBinder(AdvertisingBuyAndSellDelegate viewDelegate) {
        return new AdvertisingBuyAndSellBinder(viewDelegate);
    }

    HomeAdvertising advertising;

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                //广告发布成功
                advertising = GsonUtil.getInstance().toObj(data, HomeAdvertising.class);
                SuccessActivity.startActWithAdvertising(AdvertisingBuyActivity.this, advertising, SuccessActivity.INTENT_SUCCESS_ADVERTISING, HomeAdvertising.coin_type_btc, 0x123);
                break;
            case 0x124:
                //获取地址列表
                paymentBTCETHAddresses = GsonUtil.getInstance().toList(data, PaymentBTCETHAddress.class);
                break;
        }
    }
}

package com.gqfbtc.mvp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.circledialog.CircleDialogHelper;
import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.gqfbtc.R;
import com.gqfbtc.entity.bean.BeforeSaveAd;
import com.gqfbtc.entity.bean.CheckFrozen;
import com.gqfbtc.entity.bean.HandlingCharge;
import com.gqfbtc.entity.bean.HomeAdvertising;
import com.gqfbtc.entity.bean.PaymentBTCETHAddress;
import com.gqfbtc.mvp.activity.posted.AdvertisingBuyActivity;
import com.gqfbtc.mvp.activity.posted.AdvertisingSellActivity;
import com.gqfbtc.mvp.databinder.ChooseBuyBtcModeBinder;
import com.gqfbtc.mvp.delegate.ChooseBuyBtcModeDelegate;

import java.util.ArrayList;
import java.util.List;


public class ChooseBuyBtcModeActivity extends BaseDataBindActivity<ChooseBuyBtcModeDelegate, ChooseBuyBtcModeBinder> {

    public static final String type_buy = "type_buy";
    public static final String type_sell = "type_sell";

    public static final String action_advertising = "action_advertising";
    public static final String action_transact = "action_transact";
    CheckFrozen checkFrozen;
    boolean isSafe = false;

    int indexTime = 0;

    @Override
    protected Class<ChooseBuyBtcModeDelegate> getDelegateClass() {
        return ChooseBuyBtcModeDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        getIntentData();
        if (type.equals(type_buy)) {
            initToolbar(new ToolbarBuilder().setTitle("买BTC").setSubTitle("帮助"));
            viewDelegate.viewHolder.tv_putong_commit.setText("普通购买");
            viewDelegate.viewHolder.tv_anquan_commit.setText("安全购买");
        } else {
            initToolbar(new ToolbarBuilder().setTitle("卖BTC").setSubTitle("帮助"));
            viewDelegate.viewHolder.tv_putong_commit.setText("普通出售");
            viewDelegate.viewHolder.tv_anquan_commit.setText("安全出售");
        }
        initAction();
        viewDelegate.setOnClickListener(this, R.id.tv_anquan_commit, R.id.tv_putong_commit);
    }

    private void initAction() {
        if (action.equals(action_advertising)) {
            viewDelegate.initAdvertising(type);
            if (checkFrozen != null) {
                viewDelegate.initToast(checkFrozen);
            }
        } else {
            if (handlingCharge != null) {
                viewDelegate.initTransact(handlingCharge);
                if (!TextUtils.isEmpty(handlingCharge.getTime())) {
                    //倒计时
                    indexTime = Integer.parseInt(handlingCharge.getTime());
                    if (indexTime > 0) {
                        handler.sendEmptyMessageDelayed(1, 1000);
                    }
                }
                viewDelegate.viewHolder.lin_warning.setVisibility(View.VISIBLE);
                if (type.equals(type_buy)) {
                    viewDelegate.viewHolder.tv_warning.setText(CommonUtils.getString(R.string.str_hand_buy_warning));
                } else {
                    viewDelegate.viewHolder.tv_warning.setText(CommonUtils.getString(R.string.str_hand_sale_warning));
                }
            }
        }
    }
    //定时更新接口数据
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if (indexTime > 0) {
                        indexTime--;
                        handler.sendEmptyMessageDelayed(1, 1000);
                        viewDelegate.viewHolder.tv_time.setVisibility(View.VISIBLE);
                        viewDelegate.viewHolder.tv_time.setText("*该广告选择了溢价模式，以上价格会在 " + indexTime + "s 后随市场价刷新，建议尽快提交订单。");
                        //该广告选择了溢价模式，以上价格会在 60s 后随市场价刷新，建议尽快提交订单
                    } else {
                        viewDelegate.viewHolder.tv_time.setVisibility(View.GONE);
                        addRequest(binder.beforeSaveDeal(
                                aid,
                                price,
                                btcNum,
                                dealMoney,
                                intermediaryId,
                                ChooseBuyBtcModeActivity.this
                        ));
                    }
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_anquan_commit:
                isSafe = true;
                if (action.equals(action_advertising)) {
                    if (type.equals(type_buy)) {
                        //安全买之前验证是否有收币地址
                        addRequest(binder.beforeSaveAd("1", dealMoney, "0", "1", this));
                    } else {
                        //安全卖之前验证是否有收款地址
                        addRequest(binder.beforeSaveAd("1", dealMoney, "1", "1", this));
                    }
                } else {
                    if (type.equals(type_buy)) {
                        if (handlingCharge.getDealCollectionCoinAddrList() != null && handlingCharge.getDealCollectionCoinAddrList().size() != 0) {
                            showChooseAddress("请选择一个收币地址");
                        } else {
                            CircleDialogHelper.initDefaultToastDialog(this, "请您在个人中心中添加一个收币地址", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    onBackPressed();
                                }
                            }).show();
                        }
                    } else {
                        if (handlingCharge.getUserCollectionCashAddrList() != null && handlingCharge.getUserCollectionCashAddrList().size() != 0) {
                            showChooseAddress("请选择一个收款地址");
                        } else {
                            CircleDialogHelper.initDefaultToastDialog(this, "请您在个人中心中添加一个收款地址", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    onBackPressed();
                                }
                            }).show();
                        }
                        //addRequest(binder.saveDeal(handlingCharge.getAdId(), handlingCharge.getDealPrice(), handlingCharge.getDealQuantity(), handlingCharge.getIntermediaryId(), isSafe, type.equals(type_buy), null, this));
                    }
                }
                break;
            case R.id.tv_putong_commit:
                isSafe = false;
                if (action.equals(action_advertising)) {
                    if (type.equals(type_buy)) {
                        addRequest(binder.beforeSaveAd("1", dealMoney, "0", "0", this));

                    } else {
                        addRequest(binder.beforeSaveAd("1", dealMoney, "1", "0", this));
                    }
                } else {
                    addRequest(binder.saveDeal(handlingCharge.getAdId(), handlingCharge.getDealPrice(), handlingCharge.getDealQuantity(), handlingCharge.getIntermediaryId(), isSafe, type.equals(type_buy), null, dealMoney, this));
                }
                break;
        }
    }

    //选择收币地址
    private void showChooseAddress(String title) {
        List<String> address = new ArrayList<>();
        if (type.equals(type_buy)) {
            for (int i = 0; i < handlingCharge.getDealCollectionCoinAddrList().size(); i++) {
                address.add(handlingCharge.getDealCollectionCoinAddrList().get(i).getAlias() + handlingCharge.getDealCollectionCoinAddrList().get(i).getCollectionAddr());
            }
        } else {
            for (int i = 0; i < handlingCharge.getUserCollectionCashAddrList().size(); i++) {
                address.add(handlingCharge.getUserCollectionCashAddrList().get(i).getBankName() + handlingCharge.getUserCollectionCashAddrList().get(i).getCollectionAddr());
            }
        }
        CircleDialogHelper.initDefaultItemDialogWithTitle(this, title, address, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (type.equals(type_buy)) {
                    PaymentBTCETHAddress paymentBTCETHAddress = handlingCharge.getDealCollectionCoinAddrList().get(i - 1);
                    addRequest(binder.saveDeal(handlingCharge.getAdId(), handlingCharge.getDealPrice(), handlingCharge.getDealQuantity(), handlingCharge.getIntermediaryId(), isSafe, type.equals(type_buy), paymentBTCETHAddress.getId() + "", dealMoney, ChooseBuyBtcModeActivity.this));
                } else {
                    PaymentBTCETHAddress paymentBTCETHAddress = handlingCharge.getUserCollectionCashAddrList().get(i - 1);
                    addRequest(binder.saveDeal(handlingCharge.getAdId(), handlingCharge.getDealPrice(), handlingCharge.getDealQuantity(), handlingCharge.getIntermediaryId(), isSafe, type.equals(type_buy), paymentBTCETHAddress.getId() + "", dealMoney, ChooseBuyBtcModeActivity.this));
                }
            }
        }).show();
    }

    //发布广告进入方式
    public static void startAct(Activity activity,
                                String type,
                                String action,
                                CheckFrozen checkFrozen,
                                int requestCode) {
        Intent intent = new Intent(activity, ChooseBuyBtcModeActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("action", action);
        intent.putExtra("checkFrozen", checkFrozen);
        activity.startActivityForResult(intent, requestCode);
    }
    //购买出售进入方式
    public static void startAct(Activity activity,
                                String type,
                                String action,
                                String dealMoney,
                                HandlingCharge handlingCharge,
                                String aid,
                                String price,
                                String btcNum,
                                String intermediaryId,
                                int requestCode) {
        Intent intent = new Intent(activity, ChooseBuyBtcModeActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("action", action);
        intent.putExtra("dealMoney", dealMoney);
        intent.putExtra("handlingCharge", handlingCharge);
        intent.putExtra("aid", aid);
        intent.putExtra("price", price);
        intent.putExtra("btcNum", btcNum);
        intent.putExtra("intermediaryId", intermediaryId);
        activity.startActivityForResult(intent, requestCode);
    }

    private String type;
    private String action;
    private String dealMoney;
    private HandlingCharge handlingCharge;
    private String aid;
    private String price;
    private String btcNum;
    private String intermediaryId;

    private void getIntentData() {
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        action = intent.getStringExtra("action");
        dealMoney = intent.getStringExtra("dealMoney");
        handlingCharge = intent.getParcelableExtra("handlingCharge");
        checkFrozen = intent.getParcelableExtra("checkFrozen");
        aid = intent.getStringExtra("aid");
        price = intent.getStringExtra("price");
        btcNum = intent.getStringExtra("btcNum");
        intermediaryId = intent.getStringExtra("intermediaryId");
    }

    @Override
    public ChooseBuyBtcModeBinder getDataBinder(ChooseBuyBtcModeDelegate viewDelegate) {
        return new ChooseBuyBtcModeBinder(viewDelegate);
    }

    @Override
    protected void onServiceSuccess(final String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                //验证
                BeforeSaveAd beforeSaveAd = GsonUtil.getInstance().toObj(data, BeforeSaveAd.class);
                if (action.equals(action_advertising)) {
                    if (isSafe) {
                        if (type.equals(type_buy)) {
                            AdvertisingBuyActivity.startAct(this, AdvertisingBuyActivity.buy_type_safe, beforeSaveAd);
                        } else {
                            AdvertisingSellActivity.startAct(this, AdvertisingSellActivity.sell_type_safe, beforeSaveAd);
                        }
                    } else {
                        if (type.equals(type_buy)) {
                            AdvertisingBuyActivity.startAct(this, AdvertisingBuyActivity.buy_type_common, beforeSaveAd);
                        } else {
                            AdvertisingSellActivity.startAct(this, AdvertisingSellActivity.sell_type_common, beforeSaveAd);
                        }
                    }
                }
                break;
            case 0x124:
                //成功保存订单
                String transactId = GsonUtil.getInstance().getValue(data, "dealId");
                SuccessActivity.startActWithId(this, transactId, SuccessActivity.INTENT_SUCCESS_ORDER, HomeAdvertising.coin_type_btc, 0x123);
                break;
            case 0x125:
                handlingCharge = GsonUtil.getInstance().toObj(data, HandlingCharge.class);
                initAction();
                break;
        }
    }


    @Override
    public void onClick(View view, int position, Object item) {
        super.onClick(view, position, item);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);//清空消息方便gc回收
        super.onDestroy();

    }
}

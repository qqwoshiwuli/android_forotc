package com.gqfbtc.mvp.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.circledialog.view.listener.OnInputClickListener;
import com.fivefivelike.mybaselibrary.base.BasePullActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.gqfbtc.R;
import com.gqfbtc.Utils.UiHeplUtils;
import com.gqfbtc.adapter.MessageAdapter;
import com.gqfbtc.entity.bean.AdvertisingDetails;
import com.gqfbtc.entity.bean.AdvertisingMessage;
import com.gqfbtc.entity.bean.HandlingCharge;
import com.gqfbtc.entity.bean.HomeAdvertising;
import com.gqfbtc.entity.bean.UserLogin;
import com.gqfbtc.greenDaoUtils.SingSettingDBUtil;
import com.gqfbtc.mvp.databinder.BaseActivityPullBinder;
import com.gqfbtc.mvp.delegate.BuyAndSellBTCDelegate;
import com.gqfbtc.widget.FoucsLinearLayoutManager;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.List;


public class BuyAndSellBTCActivity extends BasePullActivity<BuyAndSellBTCDelegate, BaseActivityPullBinder<BuyAndSellBTCDelegate>> {
    List<AdvertisingMessage> defDatas;
    HeaderAndFooterWrapper adapter;
    MessageAdapter messageAdapter;
    HandlingCharge handlingCharge = null;
    AdvertisingDetails advertisingDetails;
    UserLogin userLogin;
    View bottomView;
    String str;//提示文字

    @Override
    protected Class<BuyAndSellBTCDelegate> getDelegateClass() {
        return BuyAndSellBTCDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        getIntentData();
        userLogin = SingSettingDBUtil.getUserLogin();
        if (homeAdvertising.isSale()) {
            initToolbar(new ToolbarBuilder().setTitle("买BTC").setSubTitle("帮助"));
        } else {
            initToolbar(new ToolbarBuilder().setTitle("卖BTC").setSubTitle("帮助"));
        }
        addRequest(binder.getAdDetail(homeAdvertising.getAdId(), this));
    }

    public static void startAct(Activity activity,
                                HomeAdvertising homeAdvertising) {
        Intent intent = new Intent(activity, BuyAndSellBTCActivity.class);
        intent.putExtra("homeAdvertising", homeAdvertising);
        activity.startActivity(intent);
    }

    private HomeAdvertising homeAdvertising;

    private void getIntentData() {
        Intent intent = getIntent();
        homeAdvertising = intent.getParcelableExtra("homeAdvertising");
    }


    private void initBottomView() {
        bottomView = getLayoutInflater().inflate(R.layout.layout_buy_bottom, null);
        bottomView.findViewById(R.id.tv_commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(WaitTransactActivity.class).startAct();
            }
        });
        bottomView.findViewById(R.id.lin_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userLogin != null) {
                    UiHeplUtils.initDefaultInputDialog(BuyAndSellBTCActivity.this, "添加留言", "对广告有任何问题可在这里补充", "留言", new OnInputClickListener() {
                        @Override
                        public void onClick(String text, View v) {
                            if (!UiHeplUtils.judgeRequestContentIsNull(text, "留言不能为空")) {
                                addRequest(binder.saveMessage(homeAdvertising.getAdId(), text, BuyAndSellBTCActivity.this));
                            }
                        }
                    }).show();
                } else {
                    ToastUtil.show("请先登录后再留言");
                }
            }
        });
        if (homeAdvertising.isSale()) {
            str = "购买";
        } else {
            str = "出售";
        }
        viewDelegate.viewHolder.lin_pull.addView(bottomView);
        ((TextView) bottomView.findViewById(R.id.tv_commit)).setText(str);
        ((TextView) bottomView.findViewById(R.id.tv_commit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SingSettingDBUtil.isLogin(BuyAndSellBTCActivity.this)) {
                    UiHeplUtils.initDefaultDialog(BuyAndSellBTCActivity.this, "确定要" + ((TextView) view).getText().toString() + "吗?", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //计算手续费
                            if (!UiHeplUtils.judgeRequestContentIsNull(viewDelegate.et_forecast_btc.getText().toString(), "请输入" + str + "数量")) {
                                String id;
                                if(viewDelegate.checkbox.isChecked()){
                                    id="0";
                                }else{
                                    if(viewDelegate.selectGuarantorAdapter.getSelectPositon()==-1){
                                        ToastUtil.show("请选择中介，或交由系统分配");
                                        return;
                                    }else{
                                        id=advertisingDetails.getIntermediaryList().get(viewDelegate.selectGuarantorAdapter.getSelectPositon()).getIntermediaryId();
                                    }
                                }
                                addRequest(binder.beforeSaveDeal(homeAdvertising.getAdId(),
                                        advertisingDetails.getPrice() + "",
                                        viewDelegate.et_forecast_btc.getText().toString(),
                                        viewDelegate.et_forecast_cny.getText().toString(),
                                        id,
                                        BuyAndSellBTCActivity.this
                                ));
                            }
                        }
                    }).show();
                }
            }
        });
    }

    private void initList() {
        viewDelegate.setShowNoData(false);
        messageAdapter = new MessageAdapter(this, defDatas);
        adapter = new HeaderAndFooterWrapper(messageAdapter);
        adapter.addHeaderView(viewDelegate.initHeader(advertisingDetails));
        initRecycleViewPull(adapter, adapter.getHeadersCount(), new FoucsLinearLayoutManager(this));
        viewDelegate.setIsLoadMore(false);
        viewDelegate.setIsPullDown(false);

    }

    @Override
    public BaseActivityPullBinder getDataBinder(BuyAndSellBTCDelegate viewDelegate) {
        return new BaseActivityPullBinder(viewDelegate);
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                //留言刷新
                List<AdvertisingMessage> list = GsonUtil.getInstance().toList(data, AdvertisingMessage.class);
                getDataBack(defDatas, list, adapter);
                viewDelegate.isHaveMessage(list.size() > 0);
                break;
            case 0x124:
                //订单详情
                advertisingDetails = GsonUtil.getInstance().toObj(data, AdvertisingDetails.class);
                defDatas = advertisingDetails.getAdMessageList();
                initList();
                viewDelegate.isHaveMessage(defDatas.size() > 0);
                initBottomView();
                if (userLogin != null) {
                    if ((userLogin.getId() + "").equals(advertisingDetails.getUserId())) {
                        //用户自己的广告，只能留言
                        bottomView.findViewById(R.id.tv_commit).setVisibility(View.GONE);
                    }
                }
                break;
            case 0x125:
                //留言
                if (SingSettingDBUtil.isLogin(this)) {
                    onRefresh();
                    ToastUtil.show(info);
                }
                break;
            case 0x126:
                //获取手续费
                if (SingSettingDBUtil.isLogin(this)) {
                    handlingCharge = GsonUtil.getInstance().toObj(data, HandlingCharge.class);
                    chooseMode();
                }
                break;
        }
    }

    private void chooseMode() {
        String id = viewDelegate.checkbox.isChecked() ? "0" : advertisingDetails.getIntermediaryList().get(viewDelegate.selectGuarantorAdapter.getSelectPositon()).getIntermediaryId();
        ChooseBuyBtcModeActivity.startAct(this,
                !homeAdvertising.isSale() ? ChooseBuyBtcModeActivity.type_sell : ChooseBuyBtcModeActivity.type_buy,
                ChooseBuyBtcModeActivity.action_transact,
                viewDelegate.et_forecast_cny.getText().toString(),
                handlingCharge,
                homeAdvertising.getAdId(),
                advertisingDetails.getPrice() + "",
                viewDelegate.et_forecast_btc.getText().toString(),
                id,
                0x123);
    }

    @Override
    protected void refreshData() {
        addRequest(binder.getMessage(homeAdvertising.getAdId(), this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

package com.gqfbtc.mvp.activity.transact;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.gqfbtc.R;
import com.gqfbtc.Utils.KeyboardChangeListener;
import com.gqfbtc.Utils.UiHeplUtils;
import com.gqfbtc.entity.bean.OrderDetails;
import com.gqfbtc.entity.bean.PaymentBTCETHAddress;
import com.gqfbtc.entity.bean.UserLogin;
import com.gqfbtc.greenDaoUtils.SingSettingDBUtil;
import com.gqfbtc.mvp.activity.TradersInfoActivity;
import com.gqfbtc.mvp.databinder.WaitTransactBinder;
import com.gqfbtc.mvp.delegate.WaitTransactDelegate;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

/**
 * Created by 郭青枫 on 2017/12/15 0015.
 */

public class BigDealsActivity extends BaseDataBindActivity<WaitTransactDelegate, WaitTransactBinder> implements KeyboardChangeListener.KeyBoardListener {
    private KeyboardChangeListener mKeyboardChangeListener;
    UserLogin userLogin;
    OrderDetails orderDetails;
    PaymentBTCETHAddress paymentBTCETHAddress;
    int cannelTime = 0;


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        getIntentData();
        initToolbar(new ToolbarBuilder().setTitle("交易详情"));
        userLogin = SingSettingDBUtil.getUserLogin();
        mKeyboardChangeListener = new KeyboardChangeListener(this);
        mKeyboardChangeListener.setKeyBoardListener(this);
        RongIM.getInstance().setMessageAttachedUserInfo(true);
        initRefush();
        viewDelegate.initBigDealsView();
    }

    public static void startAct(Activity activity,
                                String id) {
        Intent intent = new Intent(activity, BigDealsActivity.class);
        intent.putExtra("id", id);
        activity.startActivity(intent);
    }

    private String id;

    private void getIntentData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
    }

    @Override
    protected void clickRightTv() {
        super.clickRightTv();
        //跳转双方信息详情页面
        TradersInfoActivity.startAct(this, GsonUtil.getInstance().toJson(orderDetails.getInterNeedInfoVO()));
    }

    @Override
    public void onKeyboardChange(boolean isShow, int keyboardHeight) {
        viewDelegate.isKeySorftShow = isShow;
        viewDelegate.viewHolder.fl_top.setVisibility(!isShow ? View.VISIBLE : View.GONE);
        handler.sendEmptyMessageDelayed(1, 100);
    }

    private Handler handler = new Handler() {//进行延时跳转
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    //界面上部显隐
                    viewDelegate.linsenerLayout();
                    break;
                case 2:
                    //中介刷新
                    refreshInfo(false);
                    break;
                case 3:
                    //广告方响应时间
                    setTime();
                    break;
            }
        }
    };

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                //获取订单详情
                orderDetails = GsonUtil.getInstance().toObj(data, OrderDetails.class);
                initView();
                if (!SingSettingDBUtil.isUser()) {
                    handler.sendEmptyMessageDelayed(2, 3000);
                }
                break;
            case 0x124:
                //订单修改成功
                orderDetails = GsonUtil.getInstance().toObj(data, OrderDetails.class);
                initView();
                break;
            case 0x125:
                //拒绝订单 下架
                onBackPressed();
                break;
        }
    }

    private void initView() {
        if (SingSettingDBUtil.isUser()) {
            initToolbar(new ToolbarBuilder().setTitle(orderDetails.getTitle()));
        } else {
            initToolbar(new ToolbarBuilder().setTitle(orderDetails.getTitle()).setSubTitle(CommonUtils.getString(R.string.str_subtitle_tradersinfo)));
        }
        viewDelegate.initDataView(orderDetails);
        //币种
        viewDelegate.viewHolder.tv_total_price.setText("链克");
        viewDelegate.viewHolder.tv_btc.setText(orderDetails.getDealMoneyStr());
        viewDelegate.viewHolder.tv_poundage.setText(orderDetails.getDealQuantityStr());
        //订单提示
        viewDelegate.viewHolder.tv_bigdeals_toast.setText(orderDetails.getFrozenCoin());
        viewDelegate.viewHolder.tv_bigdeals_toast.setVisibility(TextUtils.isEmpty(orderDetails.getFrozenCoin()) ? View.GONE : View.VISIBLE);
        //订单倒计时
        if (orderDetails.getLeftSecond() >= 0) {
            cannelTime = orderDetails.getLeftSecond();
            setTime();
        }
        viewDelegate.setOnClickListener(this, R.id.fl_collection, R.id.fl_call_service, R.id.lin_my_pay_type, R.id.tv_payment);
        if (viewDelegate.fragment == null) {
            setWindowManagerLayoutParams(0);
            viewDelegate.initChatView(getSupportFragmentManager().beginTransaction(), userLogin, orderDetails.getId(), orderDetails.getCode());
        }
    }

    private void setTime() {
        if (cannelTime > -1) {
            if (cannelTime == 0) {
                viewDelegate.viewHolder.fl_collection.setEnabled(false);
                UiHeplUtils.initDefaultToastDialog(this, "广告发起方超过10分钟无响应,该订单被自动取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBackPressed();
                    }
                }).setCanceledOnTouchOutside(false).show();
            } else {
                String time;
                time = (((cannelTime / 60) < 10) ? "0" + cannelTime / 60 : cannelTime / 60) + " : " + (((cannelTime % 60) < 10) ? "0" + cannelTime % 60 : cannelTime % 60);
                viewDelegate.viewHolder.tv_bigdeals_toast.setText(
                        orderDetails.getFrozenCoin() +
                                "\n广告发起人剩余响应时间" + time
                );
                cannelTime--;
                handler.sendEmptyMessageDelayed(3, 1000);
            }
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.fl_collection:
                //推进订单
                if (!orderDetails.getEv()) {
                    UiHeplUtils.initDefaultDialog(this, orderDetails.getShowMsg(), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            pushOrder();
                        }
                    }).show();
                } else {
                    viewDelegate.isNeedAppraise(this, orderDetails);
                }
                break;
            case R.id.fl_call_service:
                if (!orderDetails.isRefuseAndDown()) {
                    RongIM.getInstance().startConversation(this, Conversation.ConversationType.PRIVATE, "10u", "");
                } else {
                    //拒绝订单
                    UiHeplUtils.initDefaultDialog(this, "是否" + viewDelegate.viewHolder.tv_call_service.getText() + "?", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            addRequest(binder.dealwkc_dealCancle(orderDetails.getId(), BigDealsActivity.this));
                        }
                    }).show();
                }
                break;
        }
    }

    private void pushOrder() {
        cannelTime = -1;
        addRequest(binder.dealwkc_change(orderDetails.getId(), orderDetails.getDealStatus(), BigDealsActivity.this));
    }

    private void initRefush() {
        //刷新
        refreshInfo(true);
        viewDelegate.viewHolder.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshInfo(false);
            }
        });
    }

    private void refreshInfo(boolean isShowDialog) {
        addRequest(binder.dealwkc_dealdt(id, isShowDialog, BigDealsActivity.this));
    }


    @Override
    public WaitTransactBinder getDataBinder(WaitTransactDelegate viewDelegate) {
        return new WaitTransactBinder(viewDelegate);
    }


    @Override
    protected Class<WaitTransactDelegate> getDelegateClass() {
        return WaitTransactDelegate.class;
    }

    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);//清空消息方便gc回收
        super.onDestroy();
    }
}

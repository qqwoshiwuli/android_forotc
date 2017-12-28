package com.gqfbtc.mvp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseActivity;
import com.fivefivelike.mybaselibrary.entity.ResultDialogEntity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.ActUtil;
import com.gqfbtc.R;
import com.gqfbtc.entity.bean.HomeAdvertising;
import com.gqfbtc.mvp.activity.advertising.BigDealsAdvertisingActivity;
import com.gqfbtc.mvp.activity.advertising.BuyAndSellBTCActivity;
import com.gqfbtc.mvp.activity.main.LoginActivity;
import com.gqfbtc.mvp.activity.transact.BigDealsActivity;
import com.gqfbtc.mvp.activity.transact.WaitTransactActivity;
import com.gqfbtc.mvp.delegate.SuccessDelegate;

import org.greenrobot.eventbus.EventBus;

public class SuccessActivity extends BaseActivity<SuccessDelegate> {

    public static final int INTENT_SUCCESS_PASSWORD = 1;
    public static final int INTENT_SUCCESS_ADVERTISING = 2;
    public static final int INTENT_SUCCESS_EVALUATE = 3;
    public static final int INTENT_SUCCESS_UPDATA = 4;
    public static final int INTENT_SUCCESS_ORDER = 5;
    String[] toolTitle = {"找回密码", "发布成功", "评价成功", "修改成功", "订单成功"};
    String[] title = {"密码设置成功", "广告发布成功", "评价提交成功", "修改成功，请重新登陆", "订单成功"};
    String[] content = {
            "已为您重新登陆，正在为您跳转到首页"
            , "正在为您跳转到广告详情页"
            , "感谢您的评价，正在为您跳转到订单详情页"
            , "您已成功修改登录密码，正在为您跳转\n至登录页重新登陆"
            , "您订单成功，正在为您跳转到订单详情页"
    };
    String[] toast = {"立即前往", "立即前往", "立即前往", "立即前往", "立即前往"};


    @Override
    protected Class<SuccessDelegate> getDelegateClass() {
        return SuccessDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        getIntentData();
        initToolbar(new ToolbarBuilder().setTitle(toolTitle[type - 1]));
        viewDelegate.viewHolder.tv_title.setText(title[type - 1]);
        viewDelegate.viewHolder.tv_content.setText(content[type - 1]);
        viewDelegate.viewHolder.tv_tosat.setText(toast[type - 1]);

        handler.sendEmptyMessageDelayed(1, 2000);
        viewDelegate.setOnClickListener(this, R.id.tv_tosat);
    }

    public static void startAct(Activity activity,
                                int type,
                                int requestCode) {
        Intent intent = new Intent(activity, SuccessActivity.class);
        intent.putExtra("type", type);
        activity.startActivityForResult(intent, requestCode);
        activity.finish();
    }

    public static void startActWithId(Activity activity,
                                      String id,
                                      int type,
                                      String coinType,
                                      int requestCode) {
        Intent intent = new Intent(activity, SuccessActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("type", type);
        intent.putExtra("coinType", coinType);
        activity.startActivityForResult(intent, requestCode);
        activity.finish();
    }


    public static void startActWithAdvertising(Activity activity,
                                               HomeAdvertising homeAdvertising,
                                               int type,
                                               String coinType,
                                               int requestCode) {
        Intent intent = new Intent(activity, SuccessActivity.class);
        intent.putExtra("homeAdvertising", homeAdvertising);
        intent.putExtra("type", type);
        intent.putExtra("coinType", coinType);
        activity.startActivityForResult(intent, requestCode);
        activity.finish();
    }

    private int type;
    private String id;
    private String coinType;
    HomeAdvertising homeAdvertising;

    private void getIntentData() {
        Intent intent = getIntent();
        type = intent.getIntExtra("type", 0);
        id = intent.getStringExtra("id");
        coinType = intent.getStringExtra("coinType");
        homeAdvertising = intent.getParcelableExtra("homeAdvertising");
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_tosat:
                doStartActivity();
                break;
        }
    }

    private Handler handler = new Handler() {//进行延时跳转
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    doStartActivity();
                    break;
            }
        }
    };


    private void doStartActivity() {
        if (type == INTENT_SUCCESS_PASSWORD) {
            //去首页
            ResultDialogEntity resultDialogEntity = new ResultDialogEntity();
            resultDialogEntity.setCode("0");
            EventBus.getDefault().post(resultDialogEntity);
        } else if (type == INTENT_SUCCESS_ADVERTISING) {
            //去广告详情
            if (HomeAdvertising.coin_type_btc.equals(coinType)) {
                BuyAndSellBTCActivity.startAct(this, homeAdvertising);
            } else {
                BigDealsAdvertisingActivity.startAct(this, homeAdvertising);
            }
        } else if (type == INTENT_SUCCESS_EVALUATE) {
            //去订单详情
            if (HomeAdvertising.coin_type_btc.equals(coinType)) {
                WaitTransactActivity.startAct(this, id);
            } else {
                BigDealsActivity.startAct(this, id);
            }
        } else if (type == INTENT_SUCCESS_UPDATA) {
            //去登陆页
            Intent intent = new Intent(this, LoginActivity.class);
            ActUtil.getInstance().killAllActivity(this);
            startActivity(intent);
        } else if (type == INTENT_SUCCESS_ORDER) {
            //去订单详情
            if (HomeAdvertising.coin_type_btc.equals(coinType)) {
                WaitTransactActivity.startAct(this, id);
            } else {
                BigDealsActivity.startAct(this, id);
            }
        }
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);//清空消息方便gc回收
    }
}

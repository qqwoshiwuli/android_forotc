package com.gqfbtc.mvp.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.utils.SaveUtil;
import com.gqfbtc.R;
import com.gqfbtc.greenDaoUtils.SingSettingDBUtil;
import com.gqfbtc.mvp.activity.main.MainActivity;
import com.gqfbtc.mvp.databinder.WelcomBinder;
import com.gqfbtc.mvp.delegate.WelcomDelegate;

public class WelcomActivity extends BaseDataBindActivity<WelcomDelegate, WelcomBinder> {

    @Override
    protected Class<WelcomDelegate> getDelegateClass() {
        return WelcomDelegate.class;
    }

    @Override
    public WelcomBinder getDataBinder(WelcomDelegate viewDelegate) {
        return new WelcomBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        addNoStatusBarFlag();
        SingSettingDBUtil.setIsUser("");
        handler.sendEmptyMessageDelayed(1, 500);
        viewDelegate.viewHolder.iv_pic.setImageResource(R.drawable.welcom);
    }

    private void doAct() {
        startActivity(new Intent(WelcomActivity.this, MainActivity.class));
        finish();
    }

    private Handler handler = new Handler() {//进行延时跳转
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    doAct();
                    break;
            }
        }
    };


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        super.onServiceError(data, info, status, requestCode);
        switch (requestCode) {
        }
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);//清空消息方便gc回收
        super.onDestroy();
    }

}

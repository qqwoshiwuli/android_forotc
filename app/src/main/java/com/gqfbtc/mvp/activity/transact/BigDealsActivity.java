package com.gqfbtc.mvp.activity.transact;

import android.support.v4.widget.SwipeRefreshLayout;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.gqfbtc.Utils.KeyboardChangeListener;
import com.gqfbtc.entity.bean.UserLogin;
import com.gqfbtc.greenDaoUtils.SingSettingDBUtil;
import com.gqfbtc.mvp.databinder.WaitTransactBinder;
import com.gqfbtc.mvp.delegate.WaitTransactDelegate;

import io.rong.imkit.RongIM;

/**
 * Created by 郭青枫 on 2017/12/15 0015.
 */

public class BigDealsActivity extends BaseDataBindActivity<WaitTransactDelegate, WaitTransactBinder> implements KeyboardChangeListener.KeyBoardListener {
    private KeyboardChangeListener mKeyboardChangeListener;
    UserLogin userLogin;


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("交易详情"));
        userLogin = SingSettingDBUtil.getUserLogin();
        mKeyboardChangeListener = new KeyboardChangeListener(this);
        mKeyboardChangeListener.setKeyBoardListener(this);
        RongIM.getInstance().setMessageAttachedUserInfo(true);
        initRefush();
        viewDelegate.initBigDealsView();
    }

    @Override
    public void onKeyboardChange(boolean isShow, int keyboardHeight) {

    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {

    }

    private void initRefush() {
        //刷新
        refreshInfo();
        viewDelegate.viewHolder.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshInfo();
            }
        });
    }

    //刷新接口
    private void refreshInfo() {

    }

    @Override
    public WaitTransactBinder getDataBinder(WaitTransactDelegate viewDelegate) {
        return new WaitTransactBinder(viewDelegate);
    }


    @Override
    protected Class<WaitTransactDelegate> getDelegateClass() {
        return WaitTransactDelegate.class;
    }
}

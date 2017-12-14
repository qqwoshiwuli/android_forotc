package com.gqfbtc.mvp.activity.main;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.gqfbtc.Utils.UiHeplUtils;
import com.gqfbtc.entity.bean.UserLogin;
import com.gqfbtc.greenDaoUtils.SingSettingDBUtil;
import com.gqfbtc.mvp.databinder.LoginAndRegisterBinder;
import com.gqfbtc.mvp.delegate.LoginAndRegisterDelegate;

/**
 * Created by 郭青枫 on 2017/11/15.
 */

public class GetCodeActivity extends BaseDataBindActivity<LoginAndRegisterDelegate, LoginAndRegisterBinder> {


    public static final String codeKey = "code";
    boolean isHaveCode = false;

    UserLogin userLogin;

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("短信验证"));
        viewDelegate.initGetCode();
        userLogin = SingSettingDBUtil.getUserLogin();
        viewDelegate.viewHolder.tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commit();
            }
        });
        setResult(RESULT_CANCELED);
        viewDelegate.viewHolder.tv_other_get_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRequest(binder.getMsgCode(userLogin.getPhone(), GetCodeActivity.this));
            }
        });
    }

    private void commit() {
        if (!isHaveCode) {
            ToastUtil.show("请点击获取验证码");
            return;
        }
        if (!UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_other_code.getText().toString(), "请输入验证码")) {
            setResult(RESULT_OK, new Intent().putExtra(codeKey, viewDelegate.viewHolder.et_other_code.getText().toString()));
            onBackPressed();
        }
    }

    public static void startActBuyAct(Activity context,
                                      int requestCode) {
        Intent intent = new Intent(context, GetCodeActivity.class);
        context.startActivityForResult(intent, requestCode);
    }

    public static void startActBuyFrag(Fragment context,
                                       int requestCode) {
        Intent intent = new Intent(context.getActivity(), GetCodeActivity.class);
        context.startActivityForResult(intent, requestCode);
    }

    @Override
    public LoginAndRegisterBinder getDataBinder(LoginAndRegisterDelegate viewDelegate) {
        return new LoginAndRegisterBinder(viewDelegate);
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x124:
                isHaveCode = true;
                UiHeplUtils.getCode(viewDelegate.viewHolder.tv_other_get_code, Long.parseLong(data));
                break;
        }
    }

    @Override
    protected Class<LoginAndRegisterDelegate> getDelegateClass() {
        return LoginAndRegisterDelegate.class;
    }
}

package com.gqfbtc.mvp.activity.main;

import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.gqfbtc.Utils.UiHeplUtils;
import com.gqfbtc.entity.bean.UserLogin;
import com.gqfbtc.greenDaoUtils.SingSettingDBUtil;
import com.gqfbtc.mvp.activity.SuccessActivity;
import com.gqfbtc.mvp.databinder.LoginAndRegisterBinder;
import com.gqfbtc.mvp.delegate.LoginAndRegisterDelegate;

public class FindPasswordActivity extends BaseDataBindActivity<LoginAndRegisterDelegate, LoginAndRegisterBinder> {


    boolean isNext = false;//是否进入下一步
    String phone;

    @Override
    protected Class<LoginAndRegisterDelegate> getDelegateClass() {
        return LoginAndRegisterDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("找回密码"));
        viewDelegate.initFindPassword();
        viewDelegate.viewHolder.tv_other_get_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取验证码
                if (!UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_other_phone.getText().toString(), "请输入手机号")) {
                    addRequest(binder.getMsgCode(viewDelegate.viewHolder.et_other_phone.getText().toString(), FindPasswordActivity.this));
                }
            }
        });
        viewDelegate.viewHolder.tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isNext) {
                    //验证验证码
                    if (!UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_other_phone.getText().toString(), "请输入手机号") &&
                            !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_other_code.getText().toString(), "请输入验证码")) {
                        addRequest(binder.doFindUser(viewDelegate.viewHolder.et_other_phone.getText().toString(), viewDelegate.viewHolder.et_other_code.getText().toString(), FindPasswordActivity.this));
                    }

                } else {
                    //设置新密码
                    if (!UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_other_phone.getText().toString(), "请输入手机号")) {
                        addRequest(binder.editNewPassword(phone, viewDelegate.viewHolder.et_other_phone.getText().toString(), FindPasswordActivity.this));
                    }
                }
            }
        });
    }

    @Override
    public LoginAndRegisterBinder getDataBinder(LoginAndRegisterDelegate viewDelegate) {
        return new LoginAndRegisterBinder(viewDelegate);
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                //重新登陆成功
                UserLogin userLogin = GsonUtil.getInstance().toObj(data, UserLogin.class);
                SingSettingDBUtil.setNewUserLogin(userLogin);
                SuccessActivity.startAct(this, SuccessActivity.INTENT_SUCCESS_PASSWORD, 0x123);
                break;
            case 0x124:
                UiHeplUtils.getCode(viewDelegate.viewHolder.tv_other_get_code, Long.parseLong(data));
                break;
            case 0x125:
                //验证码成功
                phone = viewDelegate.viewHolder.et_other_phone.getText().toString();
                viewDelegate.initFindPasswordNext();
                isNext = true;
                break;
            case 0x126:
                addRequest(binder.doLogin(
                        phone,
                        viewDelegate.viewHolder.et_other_phone.getText().toString(),
                        this));
                break;
        }
    }


}

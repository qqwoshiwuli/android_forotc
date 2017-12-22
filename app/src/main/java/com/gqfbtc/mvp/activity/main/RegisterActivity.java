package com.gqfbtc.mvp.activity.main;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.gqfbtc.R;
import com.gqfbtc.Utils.UiHeplUtils;
import com.gqfbtc.dialog.ProtocolDialog;
import com.gqfbtc.entity.bean.UserLogin;
import com.gqfbtc.greenDaoUtils.SingSettingDBUtil;
import com.gqfbtc.mvp.databinder.LoginAndRegisterBinder;
import com.gqfbtc.mvp.delegate.LoginAndRegisterDelegate;

public class RegisterActivity extends BaseDataBindActivity<LoginAndRegisterDelegate, LoginAndRegisterBinder> {


    @Override
    protected Class<LoginAndRegisterDelegate> getDelegateClass() {
        return LoginAndRegisterDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("注册"));
        viewDelegate.initRegister();
        viewDelegate.setOnClickListener(this, R.id.tv_commit, R.id.tv_bottom_toast, R.id.tv_protocol, R.id.tv_register_get_code);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_commit:
                signup();
                break;
            case R.id.tv_bottom_toast:
                onBackPressed();
                break;
            case R.id.tv_protocol:
                //协议
                new ProtocolDialog(this).show();
                break;
            case R.id.tv_register_get_code:
                getcode();
                break;
        }
    }

    private void getcode() {
        if (TextUtils.isEmpty(viewDelegate.viewHolder.et_register_phone.getText().toString())) {
            ToastUtil.show("请设置用户名");
            return;
        }
        addRequest(binder.getMsgCode(
                viewDelegate.viewHolder.et_register_phone.getText().toString(),
                this
        ));
    }

    private void signup() {
        if (!viewDelegate.viewHolder.cb_check.isChecked()) {
            ToastUtil.show("请阅读并同意FOROTC协议后进行注册");
            return;
        }
        if (TextUtils.isEmpty(viewDelegate.viewHolder.et_register_phone.getText().toString())) {
            ToastUtil.show("请输入手机号");
            return;
        }
        if (TextUtils.isEmpty(viewDelegate.viewHolder.et_register_email.getText().toString())) {
            ToastUtil.show("请设置邮箱");
            return;
        }
        if (TextUtils.isEmpty(viewDelegate.viewHolder.et_register_password.getText().toString())) {
            ToastUtil.show("请设置密码");
            return;
        }
        if (TextUtils.isEmpty(viewDelegate.viewHolder.et_register_code.getText().toString())) {
            ToastUtil.show("请输入验证码");
            return;
        }
        addRequest(binder.signup(
                viewDelegate.viewHolder.et_register_phone.getText().toString(),
                viewDelegate.viewHolder.et_register_password.getText().toString(),
                viewDelegate.viewHolder.et_register_email.getText().toString(),
                viewDelegate.viewHolder.et_register_code.getText().toString(),
                this
        ));

    }

    @Override
    public LoginAndRegisterBinder getDataBinder(LoginAndRegisterDelegate viewDelegate) {
        return new LoginAndRegisterBinder(viewDelegate);
    }

    UserLogin userLogin;

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                //注册成功
                userLogin = GsonUtil.getInstance().toObj(data, UserLogin.class);
                SingSettingDBUtil.setNewUserLogin(userLogin);
                ToastUtil.show(info);
                if (!TextUtils.isEmpty(userLogin.getNickName())) {
                    //进入首页
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                } else {
                    if (SingSettingDBUtil.isUser()) {
                        //进入设置昵称页面
                        startActivity(new Intent(RegisterActivity.this, SetUserNameActivity.class));
                    } else {
                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    }
                }
                finish();
                break;
            case 0x124:
                //获取验证码
                UiHeplUtils.getCode(viewDelegate.viewHolder.tv_register_get_code, Long.parseLong(data));
                break;
        }
    }

}

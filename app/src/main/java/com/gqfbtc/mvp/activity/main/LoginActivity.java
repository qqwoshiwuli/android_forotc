package com.gqfbtc.mvp.activity.main;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.circledialog.CircleDialogHelper;
import com.circledialog.view.listener.OnInputClickListener;
import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.gqfbtc.R;
import com.gqfbtc.base.AppConst;
import com.gqfbtc.entity.bean.UserLogin;
import com.gqfbtc.greenDaoUtils.SingSettingDBUtil;
import com.gqfbtc.http.HttpUrl;
import com.gqfbtc.mvp.databinder.LoginAndRegisterBinder;
import com.gqfbtc.mvp.delegate.LoginAndRegisterDelegate;

import static com.gqfbtc.http.HttpUrl.getBAseUrl;


public class LoginActivity extends BaseDataBindActivity<LoginAndRegisterDelegate, LoginAndRegisterBinder> {


    @Override
    protected Class<LoginAndRegisterDelegate> getDelegateClass() {
        return LoginAndRegisterDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("登录").setShowBack(false));
        viewDelegate.initLogin();
        viewDelegate.setOnClickListener(this, R.id.tv_commit, R.id.tv_bottom_toast, R.id.tv_forget_password);
        viewDelegate.getmToolbarTitle().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AppConst.isEditUrl) {
                    String hiit = getBAseUrl();
                    CircleDialogHelper.initDefaultInputDialog(LoginActivity.this, "baseUrl", hiit, "确定", new OnInputClickListener() {
                        @Override
                        public void onClick(String text, View v) {
                            HttpUrl.saveBaseUrl(text);
                        }
                    }).setDefaultInputTxt(hiit).show();
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_commit:
                //登录
                login();
                break;
            case R.id.tv_bottom_toast:
                //注册
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.tv_forget_password:
                //忘记密码
                startActivity(new Intent(LoginActivity.this, FindPasswordActivity.class));
                break;
        }
    }

    private void login() {
        if (TextUtils.isEmpty(viewDelegate.viewHolder.et_login_phone.getText().toString())) {
            ToastUtil.show("请输入用户名");
            return;
        }
        if (TextUtils.isEmpty(viewDelegate.viewHolder.et_login_password.getText().toString())) {
            ToastUtil.show("请输入密码");
            return;
        }

        //包含@中介登录
        if (viewDelegate.viewHolder.et_login_phone.getText().toString().contains("@")) {
            addRequest(binder.ic_doLogin(
                    viewDelegate.viewHolder.et_login_phone.getText().toString(),
                    viewDelegate.viewHolder.et_login_password.getText().toString(),
                    this));
        } else {
            addRequest(binder.doLogin(
                    viewDelegate.viewHolder.et_login_phone.getText().toString(),
                    viewDelegate.viewHolder.et_login_password.getText().toString(),
                    this));
        }
    }

    @Override
    public LoginAndRegisterBinder getDataBinder(LoginAndRegisterDelegate viewDelegate) {
        return new LoginAndRegisterBinder(viewDelegate);
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        ToastUtil.show(info);
        switch (requestCode) {
            case 0x123:
                //登陆成功
                UserLogin userLogin = GsonUtil.getInstance().toObj(data, UserLogin.class);
                SingSettingDBUtil.setNewUserLogin(userLogin);
                if (!TextUtils.isEmpty(userLogin.getNickName())) {
                    //进入首页
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                } else {
                    if (SingSettingDBUtil.isUser()) {
                        //进入设置昵称页面
                        startActivity(new Intent(this, SetUserNameActivity.class));
                        finish();
                    } else {
                        startActivity(new Intent(this, MainActivity.class));
                        finish();
                    }
                }
                break;
        }
    }
}

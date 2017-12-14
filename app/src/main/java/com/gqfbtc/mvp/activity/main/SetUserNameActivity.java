package com.gqfbtc.mvp.activity.main;

import android.text.TextUtils;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.gqfbtc.Utils.CheckInfoUtil;
import com.gqfbtc.Utils.UiHeplUtils;
import com.gqfbtc.entity.bean.ImUser;
import com.gqfbtc.entity.bean.UserLogin;
import com.gqfbtc.greenDaoUtils.SingSettingDBUtil;
import com.gqfbtc.mvp.databinder.LoginAndRegisterBinder;
import com.gqfbtc.mvp.delegate.LoginAndRegisterDelegate;

public class SetUserNameActivity extends BaseDataBindActivity<LoginAndRegisterDelegate, LoginAndRegisterBinder> {


    UserLogin userLogin;

    String nickName;

    @Override
    protected Class<LoginAndRegisterDelegate> getDelegateClass() {
        return LoginAndRegisterDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("设置用户名"));
        viewDelegate.initSetUserName();
        userLogin = SingSettingDBUtil.getUserLogin();
        viewDelegate.viewHolder.tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commit();
            }
        });
    }

    private void commit() {
        if (!UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_other_phone.getText().toString(), "请设置用户昵称")
                ) {
            if (!CheckInfoUtil.checkNickName(viewDelegate.viewHolder.et_other_phone.getText().toString())) {
                ToastUtil.show("昵称长度不能超过6位");
                return;
            }
            nickName = viewDelegate.viewHolder.et_other_phone.getText().toString();
            addRequest(binder.editUserNickName(
                    userLogin.getId() + "",
                    viewDelegate.viewHolder.et_other_phone.getText().toString(),
                    this
            ));
        }
    }

    @Override
    public LoginAndRegisterBinder getDataBinder(LoginAndRegisterDelegate viewDelegate) {
        return new LoginAndRegisterBinder(viewDelegate);
    }

    String toast;

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                //设置昵称成功
                if (!TextUtils.isEmpty(nickName)) {
                    userLogin.setNickName(viewDelegate.viewHolder.et_other_phone.getText().toString());
                    SingSettingDBUtil.setNewUserLogin(userLogin);
                    addRequest(binder.imtoken(this));
                }
                toast = info;
                break;
            case 0x124:
                //获取imtoken
                ImUser imUser = GsonUtil.getInstance().toObj(data, ImUser.class);
                UserLogin userLogin = SingSettingDBUtil.getUserLogin();
                userLogin.setImToken(imUser.getToken());
                SingSettingDBUtil.setNewUserLogin(userLogin);
                ToastUtil.show(toast);
                gotoActivity(MainActivity.class).setIsFinish(true).startAct();
                break;
        }
    }

    @Override
    protected void onServiceError(String data, String info, int status, int requestCode) {
        super.onServiceError(data, info, status, requestCode);
        if (requestCode == 0x124) {
            //imtoken获取失败也跳转到首页
            gotoActivity(MainActivity.class).setIsFinish(true).startAct();
        }
    }
}

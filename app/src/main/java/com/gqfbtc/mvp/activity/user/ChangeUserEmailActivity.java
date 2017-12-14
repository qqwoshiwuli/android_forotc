package com.gqfbtc.mvp.activity.user;

import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.gqfbtc.Utils.UiHeplUtils;
import com.gqfbtc.entity.bean.UserLogin;
import com.gqfbtc.greenDaoUtils.SingSettingDBUtil;
import com.gqfbtc.mvp.databinder.ChangeUserSetBinder;
import com.gqfbtc.mvp.delegate.ChangeUserSetDelegate;


public class ChangeUserEmailActivity extends BaseDataBindActivity<ChangeUserSetDelegate, ChangeUserSetBinder> {

    UserLogin userLogin;

    @Override
    protected Class<ChangeUserSetDelegate> getDelegateClass() {
        return ChangeUserSetDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("修改邮箱").setSubTitle("帮助"));
        viewDelegate.initEmail();
        userLogin = SingSettingDBUtil.getUserLogin();
        viewDelegate.viewHolder.et_input1.setText(userLogin.getEmail());
        viewDelegate.viewHolder.tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_input1.getText().toString(), "请输入新邮箱")
                        ) {
                    addRequest(binder.editUserEmail(
                            viewDelegate.viewHolder.et_input1.getText().toString(),
                            ChangeUserEmailActivity.this
                    ));
                }
            }
        });
    }

    @Override
    public ChangeUserSetBinder getDataBinder(ChangeUserSetDelegate viewDelegate) {
        return new ChangeUserSetBinder(viewDelegate);
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x124:
                userLogin.setEmail(viewDelegate.viewHolder.et_input1.getText().toString());
                SingSettingDBUtil.setNewUserLogin(userLogin);
                ToastUtil.show(info);
                onBackPressed();
                break;
        }
    }
}

package com.gqfbtc.mvp.activity.user;

import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.gqfbtc.Utils.UiHeplUtils;
import com.gqfbtc.mvp.activity.SuccessActivity;
import com.gqfbtc.mvp.databinder.ChangeUserSetBinder;
import com.gqfbtc.mvp.delegate.ChangeUserSetDelegate;


public class ChangeUserPasswordActivity extends BaseDataBindActivity<ChangeUserSetDelegate, ChangeUserSetBinder> {


    @Override
    protected Class<ChangeUserSetDelegate> getDelegateClass() {
        return ChangeUserSetDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("修改密码").setSubTitle("帮助"));
        viewDelegate.initPassword();
        viewDelegate.viewHolder.tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_input1.getText().toString(), "请输入原密码") &&
                        !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_input1.getText().toString(), "请输入新密码")) {
                    addRequest(binder.editUserPassword(
                            viewDelegate.viewHolder.et_input1.getText().toString(),
                            viewDelegate.viewHolder.et_input2.getText().toString(),
                            ChangeUserPasswordActivity.this
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
            case 0x123:
                SuccessActivity.startAct(ChangeUserPasswordActivity.this, SuccessActivity.INTENT_SUCCESS_UPDATA, 0x123);
                break;
        }
    }
}

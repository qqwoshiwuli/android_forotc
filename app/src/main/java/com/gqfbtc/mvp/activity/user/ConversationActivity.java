package com.gqfbtc.mvp.activity.user;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;

import com.fivefivelike.mybaselibrary.base.BaseActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.gqfbtc.R;
import com.gqfbtc.mvp.delegate.CustomerServiceActDelegate;

import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imlib.model.Conversation;

import static com.gqfbtc.base.AppConst.serviceId;

/**
 * Created by 郭青枫 on 2017/11/14.
 */
public class ConversationActivity extends BaseActivity<CustomerServiceActDelegate> {


    @Override
    protected Class<CustomerServiceActDelegate> getDelegateClass() {
        return CustomerServiceActDelegate.class;
    }

    @Override
    public void onBackPressed() {
        ConversationFragment fragment = (ConversationFragment) getSupportFragmentManager().findFragmentById(R.id.rong_content);
        if (!fragment.onBackPressed()) {
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        //SoftHideKeyBoardUtil.assistActivity(this);
        initToolbar(new ToolbarBuilder().setTitle("客服中心"));
        setWindowManagerLayoutParams(WindowManagerLayoutParamsNone);
        ConversationFragment fragment = new ConversationFragment();
        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversation")
                .appendPath(Conversation.ConversationType.CUSTOMER_SERVICE.getName().toLowerCase())
                .appendQueryParameter("targetId", serviceId).build();

        fragment.setUri(uri);

        /* 加载 ConversationFragment */
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.rong_content, fragment);
        transaction.commit();
    }

}
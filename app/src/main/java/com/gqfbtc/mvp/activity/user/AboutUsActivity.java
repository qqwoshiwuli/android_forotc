package com.gqfbtc.mvp.activity.user;

import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.gqfbtc.mvp.activity.main.WebVeiwActivity;
import com.gqfbtc.mvp.delegate.AboutUsDelegate;


public class AboutUsActivity extends BaseActivity<AboutUsDelegate> {


    @Override
    protected Class<AboutUsDelegate> getDelegateClass() {
        return AboutUsDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("关于我们"));
        viewDelegate.viewHolder.tv_telegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebVeiwActivity.startAct(AboutUsActivity.this, viewDelegate.viewHolder.tv_telegram.getText().toString());
            }
        });

    }
}

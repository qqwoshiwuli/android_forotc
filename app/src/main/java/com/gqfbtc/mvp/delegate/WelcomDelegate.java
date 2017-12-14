package com.gqfbtc.mvp.delegate;

import android.view.View;
import android.widget.ImageView;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.gqfbtc.R;

public class WelcomDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcom;
    }


    public static class ViewHolder {
        public View rootView;
        public ImageView iv_pic;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_pic = (ImageView) rootView.findViewById(R.id.iv_pic);
        }

    }
}
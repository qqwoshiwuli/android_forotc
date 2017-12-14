package com.gqfbtc.mvp.delegate;


import android.support.v4.view.ViewPager;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.gqfbtc.R;
import com.gqfbtc.widget.CommonTabLayout;

public class MyAdvertisingDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_advertising;
    }


    public static class ViewHolder {
        public View rootView;
        public CommonTabLayout tl_2;
        public ViewPager vp_advertising;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tl_2 = (CommonTabLayout) rootView.findViewById(R.id.tl_2);
            this.vp_advertising = (ViewPager) rootView.findViewById(R.id.vp_advertising);
        }

    }
}

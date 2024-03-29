package com.gqfbtc.mvp.delegate;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.gqfbtc.R;
import com.gqfbtc.widget.SlidingTabLayout;

/**
 * Created by 郭青枫 on 2017/10/14.
 */

public class ActivityPageDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_page;
    }


    public static class ViewHolder {
        public View rootView;
        public SlidingTabLayout tl_2;
        public ViewPager vp_page;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tl_2 = (SlidingTabLayout) rootView.findViewById(R.id.tl_2);
            this.vp_page = (ViewPager) rootView.findViewById(R.id.vp_page);
        }

    }
}

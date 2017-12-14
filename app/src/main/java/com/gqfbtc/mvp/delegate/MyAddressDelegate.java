package com.gqfbtc.mvp.delegate;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.gqfbtc.R;
import com.gqfbtc.widget.CommonTabLayout;

/**
 * Created by 郭青枫 on 2017/10/14.
 */

public class MyAddressDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_address;
    }


    public static class ViewHolder {
        public View rootView;
        public CommonTabLayout tl_2;
        public ViewPager vp_address;
        public TextView tv_commit;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tl_2 = (CommonTabLayout) rootView.findViewById(R.id.tl_2);
            this.vp_address = (ViewPager) rootView.findViewById(R.id.vp_address);
            this.tv_commit = (TextView) rootView.findViewById(R.id.tv_commit);
        }

    }
}

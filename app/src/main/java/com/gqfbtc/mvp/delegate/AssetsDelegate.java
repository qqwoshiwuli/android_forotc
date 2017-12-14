package com.gqfbtc.mvp.delegate;

import android.view.View;
import android.widget.FrameLayout;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.gqfbtc.R;
import com.gqfbtc.widget.CommonTabLayout;

/**
 * Created by 郭青枫 on 2017/10/14.
 */

public class AssetsDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_assets;
    }


    public static class ViewHolder {
        public View rootView;
        public CommonTabLayout tl_2;
        public FrameLayout fl_root;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tl_2 = (CommonTabLayout) rootView.findViewById(R.id.tl_2);
            this.fl_root = (FrameLayout) rootView.findViewById(R.id.fl_root);
        }

    }
}

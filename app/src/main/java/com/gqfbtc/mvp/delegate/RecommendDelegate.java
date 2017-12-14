package com.gqfbtc.mvp.delegate;


import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.gqfbtc.R;

public class RecommendDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
        viewHolder.sv_root.setVisibility(View.GONE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recommend;
    }


    public static class ViewHolder {
        public View rootView;
        public ImageView iv_pic;
        public TextView tv_num;
        public TextView tv_rewards;
        public LinearLayout lin_img;
        public LinearLayout lin_literals;
        public LinearLayout lin_url;
        public NestedScrollView sv_root;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_pic = (ImageView) rootView.findViewById(R.id.iv_pic);
            this.tv_num = (TextView) rootView.findViewById(R.id.tv_num);
            this.tv_rewards = (TextView) rootView.findViewById(R.id.tv_rewards);
            this.lin_img = (LinearLayout) rootView.findViewById(R.id.lin_img);
            this.lin_literals = (LinearLayout) rootView.findViewById(R.id.lin_literals);
            this.lin_url = (LinearLayout) rootView.findViewById(R.id.lin_url);
            this.sv_root = (NestedScrollView) rootView.findViewById(R.id.sv_root);
        }

    }
}

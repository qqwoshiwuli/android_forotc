package com.gqfbtc.mvp.delegate;


import android.view.View;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.gqfbtc.R;
import com.gqfbtc.Utils.glide.CircleProgressView;

import cn.bingoogolapple.bgabanner.BGABanner;

public class ShareImgDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_share_img;
    }


    public static class ViewHolder {
        public View rootView;
        public BGABanner banner;
        public CircleProgressView iv_progress;
        public TextView tv_commit;
        public TextView tv_save;
        public TextView tv_change;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.banner = (BGABanner) rootView.findViewById(R.id.banner);
            this.iv_progress = (CircleProgressView) rootView.findViewById(R.id.iv_progress);
            this.tv_commit = (TextView) rootView.findViewById(R.id.tv_commit);
            this.tv_save = (TextView) rootView.findViewById(R.id.tv_save);
            this.tv_change = (TextView) rootView.findViewById(R.id.tv_change);
        }

    }
}
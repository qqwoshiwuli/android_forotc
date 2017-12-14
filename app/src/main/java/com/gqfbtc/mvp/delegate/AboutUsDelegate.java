package com.gqfbtc.mvp.delegate;


import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.fivefivelike.mybaselibrary.view.IconFontTextview;
import com.gqfbtc.R;

public class AboutUsDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_us;
    }


    public static class ViewHolder {
        public View rootView;
        public TextView tv_telegram;
        public TextView tv_slack;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_telegram = (TextView) rootView.findViewById(R.id.tv_telegram);
            this.tv_slack = (TextView) rootView.findViewById(R.id.tv_slack);
        }

    }
}

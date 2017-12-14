package com.gqfbtc.mvp.delegate;

import android.view.View;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.gqfbtc.R;

/**
 * Created by 郭青枫 on 2017/10/14.
 */

public class SuccessDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_success;
    }


    public static class ViewHolder {
        public View rootView;
        public TextView tv_title;
        public TextView tv_content;
        public TextView tv_tosat;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_content = (TextView) rootView.findViewById(R.id.tv_content);
            this.tv_tosat = (TextView) rootView.findViewById(R.id.tv_tosat);
        }

    }
}

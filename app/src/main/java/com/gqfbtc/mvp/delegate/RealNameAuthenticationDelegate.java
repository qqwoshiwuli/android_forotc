package com.gqfbtc.mvp.delegate;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.gqfbtc.R;

public class RealNameAuthenticationDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_real_name_authentication;
    }


    public static class ViewHolder {
        public View rootView;
        public TextView tv_toast;
        public ImageView iv_front_idcard;
        public ImageView iv_reverse_idcard;
        public ImageView iv_people_idcard;
        public TextView tv_commit;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_toast = (TextView) rootView.findViewById(R.id.tv_toast);
            this.iv_front_idcard = (ImageView) rootView.findViewById(R.id.iv_front_idcard);
            this.iv_reverse_idcard = (ImageView) rootView.findViewById(R.id.iv_reverse_idcard);
            this.iv_people_idcard = (ImageView) rootView.findViewById(R.id.iv_people_idcard);
            this.tv_commit = (TextView) rootView.findViewById(R.id.tv_commit);
        }

    }
}

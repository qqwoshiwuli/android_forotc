package com.gqfbtc.mvp.delegate;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.gqfbtc.R;
import com.gqfbtc.widget.CommonTabLayout;
import com.gqfbtc.widget.SingleLineZoomTextView;

/**
 * Created by 郭青枫 on 2017/10/14.
 */

public class AssetsBtcDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_assets_btc;
    }


    public static class ViewHolder {
        public View rootView;
        public TextView tv_all_money;
        public SingleLineZoomTextView tv_canuse;
        public SingleLineZoomTextView tv_unuse;
        public LinearLayout lin_all_money;
        public CommonTabLayout tl_2;
        public FrameLayout fl_root;
        public SwipeRefreshLayout swipeRefreshLayout;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_all_money = (TextView) rootView.findViewById(R.id.tv_all_money);
            this.tv_canuse = (SingleLineZoomTextView) rootView.findViewById(R.id.tv_canuse);
            this.tv_unuse = (SingleLineZoomTextView) rootView.findViewById(R.id.tv_unuse);
            this.lin_all_money = (LinearLayout) rootView.findViewById(R.id.lin_all_money);
            this.tl_2 = (CommonTabLayout) rootView.findViewById(R.id.tl_2);
            this.fl_root = (FrameLayout) rootView.findViewById(R.id.fl_root);
            this.swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        }

    }
}

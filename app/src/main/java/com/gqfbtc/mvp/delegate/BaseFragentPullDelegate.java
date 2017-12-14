package com.gqfbtc.mvp.delegate;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.gqfbtc.R;
import com.gqfbtc.base.BaseMyPullDelegate;


/**
 * Created by 郭青枫 on 2017/9/26.
 */

public class BaseFragentPullDelegate extends BaseMyPullDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.public_pull_recycleview;
    }


    public static class ViewHolder {
        public View rootView;
        public RecyclerView pull_recycleview;
        public SwipeRefreshLayout swipeRefreshLayout;
        public RelativeLayout no_data;
        public FrameLayout fl_pull;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.pull_recycleview = (RecyclerView) rootView.findViewById(R.id.pull_recycleview);
            this.swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
            this.no_data = (RelativeLayout) rootView.findViewById(R.id.no_data);
            this.fl_pull = (FrameLayout) rootView.findViewById(R.id.fl_pull);
        }

    }
}

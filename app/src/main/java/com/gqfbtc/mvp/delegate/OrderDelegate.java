package com.gqfbtc.mvp.delegate;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.gqfbtc.R;
import com.gqfbtc.base.BaseMyPullDelegate;

/**
 * Created by 郭青枫 on 2017/10/14.
 */

public class OrderDelegate extends BaseMyPullDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_pull;
    }


    public static class ViewHolder {
        public View rootView;
        public RecyclerView pull_recycleview;
        public SwipeRefreshLayout swipeRefreshLayout;
        public RelativeLayout no_data;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.pull_recycleview = (RecyclerView) rootView.findViewById(R.id.pull_recycleview);
            this.swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
            this.no_data = (RelativeLayout) rootView.findViewById(R.id.no_data);
        }

    }
}

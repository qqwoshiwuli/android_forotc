package com.gqfbtc.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.fivefivelike.mybaselibrary.base.BasePullDelegate;
import com.fivefivelike.mybaselibrary.view.LoadMoreListView;
import com.gqfbtc.R;


/**
 * Created by 郭青枫 on 2017/9/26.
 */

public abstract class BaseMyPullDelegate extends BasePullDelegate {

    public void initRecycleviewPull(RecyclerView.Adapter adapter, RecyclerView.LayoutManager manager, LoadMoreListView.Callback callback, int headerCount, SwipeRefreshLayout.OnRefreshListener onRefreshListener) {
        super.initRecycleviewPull(adapter, manager, callback, headerCount, onRefreshListener);
        setColorSchemeResources(R.color.black);
    }


}
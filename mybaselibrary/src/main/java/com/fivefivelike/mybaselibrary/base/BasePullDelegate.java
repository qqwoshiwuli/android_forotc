package com.fivefivelike.mybaselibrary.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.R;
import com.fivefivelike.mybaselibrary.utils.paginate.LoadingListItemCreator;
import com.fivefivelike.mybaselibrary.utils.paginate.LoadingListItemSpanLookup;
import com.fivefivelike.mybaselibrary.utils.paginate.Paginate;
import com.fivefivelike.mybaselibrary.utils.paginate.ViewHolder;
import com.fivefivelike.mybaselibrary.view.LoadMoreListView;
import com.fivefivelike.mybaselibrary.view.ProgressView;

import java.util.List;

/**
 * Created by 郭青枫 on 2017/7/10.
 */

public abstract class BasePullDelegate extends BaseDelegate {
    // 分页页数
    public int page;
    //分页长度
    public int pagesize = 20;
    private LoadMode mMode = LoadMode.REFRESH;
    private boolean mIsLoadMore = true;
    private String noDataTxt;
    private RelativeLayout no_data;
    private Paginate.Callbacks callbacks;
    private boolean isNoData = false;
    private LoadingListItemCreator loadingListItemCreator;
    private int headerCount = 0;//头布局数量
    /**
     * 下拉刷新控件
     */
    private SwipeRefreshLayout mWwipeRefreshLayout;
    private RecyclerView mPullRecyclerView;
    /**
     * 用链式达到一句话设置{@link #mPullRecyclerView}的加载操作
     */
    private Paginate mPaginate;
    /**
     * 当{@link #mPullRecyclerView}设置 GridLayoutManager用到
     */
    private int SPAN_SIZE = 0;
    /**
     * {@link #mPullRecyclerView}的上拉显示布局
     */
    private View mFootView;
    /**
     * 用来判断{@link #mPullRecyclerView}加载状态 true是加载中,fasle是不在加载中
     */
    private boolean isLoading;
    /**
     * 用来判断{@link #mPullRecyclerView}中的是不是已经加载了所有的数据
     */
    private boolean isFinish;


    public void setColorSchemeResources(int... colorResIds) {
        if (mWwipeRefreshLayout != null) {
            mWwipeRefreshLayout.setColorSchemeResources(colorResIds);
        }
    }

    boolean isShowNoData = true;

    public boolean isShowNoData() {
        return isShowNoData;
    }

    public void setShowNoData(boolean showNoData) {
        isShowNoData = showNoData;
    }

    /**
     * 初始化使用RecyclerView的上拉页面
     *
     * @param adapter RecyclerView 的adapter
     * @param manager RecyclerView的显示方式
     */
    public void initRecycleviewPull(RecyclerView.Adapter adapter, RecyclerView.LayoutManager manager, final LoadMoreListView.Callback callback, int headerCount, SwipeRefreshLayout.OnRefreshListener onRefreshListener) {
        mWwipeRefreshLayout = getViewById(R.id.swipeRefreshLayout);
        mPullRecyclerView = getViewById(R.id.pull_recycleview);
        mPullRecyclerView.setLayoutManager(manager);
        mPullRecyclerView.setAdapter(adapter);
        this.headerCount = headerCount;
        callbacks = new Paginate.Callbacks() {
            @Override
            public void onLoadMore() {
                isLoading = true;
                callback.loadData();
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }

            @Override
            public boolean hasLoadedAllItems() {
                return isFinish;
            }
        };
        loadingListItemCreator = new LoadingListItemCreator() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                addFoot(parent);
                return new ViewHolder(mFootView);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            }
        };
        mPaginate = Paginate.with(mPullRecyclerView, callbacks).setLoadingTriggerThreshold(2)
                .addLoadingListItem(true)
                .setLoadingListItemCreator(loadingListItemCreator)
                .setLoadingListItemSpanSizeLookup(new LoadingListItemSpanLookup() {
                    @Override
                    public int getSpanSize() {
                        return SPAN_SIZE;
                    }
                })
                .setPagesize(pagesize)
                .setHeaderCount(headerCount)
                .build();


        mWwipeRefreshLayout.setOnRefreshListener(onRefreshListener);
    }

    private void addFoot(ViewGroup parent) {
        mFootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_layout, parent, false);//初始化尾布局
        mFootView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ProgressView loadingView = new ProgressView(parent.getContext());//尾部加载中状态
        loadingView.setIndicatorId(ProgressView.BallPulse);
        loadingView.setIndicatorColor(0xff69b3e0);
        TextView endView = new TextView(parent.getContext());//所有数据加载完布局
        endView.setGravity(Gravity.CENTER);
        endView.setText("已经到底啦~");
        LinearLayout loadLayout = (LinearLayout) mFootView.findViewById(R.id.loading_view_layout);
        LinearLayout endLayout = (LinearLayout) mFootView.findViewById(R.id.end_layout);
        RelativeLayout nodata = (RelativeLayout) mFootView.findViewById(R.id.no_data);
        nodata.getLayoutParams().height = mPullRecyclerView.getHeight();
        loadLayout.setVisibility(View.GONE);
        nodata.setVisibility(View.GONE);
        loadLayout.addView(loadingView);
        endLayout.addView(endView);
        nodata.setVisibility(isShowNoData ? View.VISIBLE : View.GONE);
        loadLayout.setVisibility(View.GONE);
        endLayout.setVisibility(View.GONE);

        if (!mIsLoadMore) {
            loadLayout.setVisibility(View.GONE);
            endLayout.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(noDataTxt)) {
            ((TextView) mFootView.findViewById(R.id.tv_nodata)).setText(noDataTxt);
        }
    }

    /**
     * 数据请求回来调用
     */
    protected void requestBack(List srcList) {
        mWwipeRefreshLayout.setRefreshing(false);
        hideNoData();
        switch (mMode) {
            case REFRESH://下拉
                if (srcList != null) {
                    srcList.clear();
                }
                break;
            case DOWN://上拉
                break;
        }
        if (mPullRecyclerView != null) {
            isFinish = false;
            isLoading = false;
            if (mFootView != null) {
                mFootView.findViewById(R.id.loading_view_layout).setVisibility(mIsLoadMore ? View.VISIBLE : View.GONE);
                mFootView.findViewById(R.id.end_layout).setVisibility(View.GONE);
            }
        }
    }

    public void loadFinish() {
        switch (mMode) {
            case REFRESH://下拉
                showNoData();
                break;
            case DOWN://上拉
                if (mPullRecyclerView != null) {
                    isFinish = true;
                    isLoading = false;
                    if (mFootView != null) {
                        mFootView.findViewById(R.id.loading_view_layout).setVisibility(View.GONE);
                        mFootView.findViewById(R.id.end_layout).setVisibility(mIsLoadMore ? View.VISIBLE : View.GONE);
                    }
                }
                break;
        }
    }

    public void hideNoData() {
        //getViewById(R.id.no_data).setVisibility(View.GONE);
        isNoData = false;
        if (mFootView != null) {
            mFootView.findViewById(R.id.no_data).setVisibility(View.GONE);
        }
    }

    public void showNoData() {
        isNoData = true;
        if (isShowNoData && mFootView != null) {
            mFootView.findViewById(R.id.no_data).setVisibility(View.VISIBLE);
            mFootView.findViewById(R.id.loading_view_layout).setVisibility(View.GONE);
            mFootView.findViewById(R.id.loading_view_layout).setVisibility(View.GONE);
        }
    }

    /**
     * 设置是否上拉加载
     *
     * @param isLoadMore
     */
    public void setIsLoadMore(boolean isLoadMore) {
        this.mIsLoadMore = isLoadMore;
    }

    /**
     * 设置是否下拉刷新
     *
     * @param isPullDown
     */
    public void setIsPullDown(boolean isPullDown) {
        if (mWwipeRefreshLayout != null) {
            mWwipeRefreshLayout.setEnabled(isPullDown);
        }
    }

    /**
     * 请求数据
     *
     * @param loadMode 类型
     */
    public void requestData(LoadMode loadMode) {
        switch (loadMode) {
            case REFRESH://下拉刷新
                mMode = LoadMode.REFRESH;
                page = 1;
                break;
            case DOWN://上拉加载
                mMode = LoadMode.DOWN;
                page++;
                break;
        }
    }

    public enum LoadMode {
        /**
         * 下拉刷新
         */
        REFRESH,
        /**
         * 上拉加载
         */
        DOWN
    }

    public void stopRefresh() {
        if (mWwipeRefreshLayout != null) {
            mWwipeRefreshLayout.setRefreshing(false);
        }
    }

    public SwipeRefreshLayout getWwipeRefreshLayout() {
        return mWwipeRefreshLayout;
    }

    public RecyclerView getPullRecyclerView() {
        return mPullRecyclerView;
    }

    public void setNoDataTxt(String noDataTxt) {
        this.noDataTxt = noDataTxt;
    }
}

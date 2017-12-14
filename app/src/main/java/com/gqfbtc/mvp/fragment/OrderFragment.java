package com.gqfbtc.mvp.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BasePullFragment;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.gqfbtc.R;
import com.gqfbtc.adapter.OrderListAdapter;
import com.gqfbtc.base.Application;
import com.gqfbtc.entity.bean.OrderListItem;
import com.gqfbtc.entity.event.TransactEvent;
import com.gqfbtc.mvp.activity.WaitTransactActivity;
import com.gqfbtc.mvp.databinder.BaseActivityPullBinder;
import com.gqfbtc.mvp.delegate.BaseActivityPullDelegate;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 郭青枫 on 2017/10/16.
 */

public class OrderFragment extends BasePullFragment<BaseActivityPullDelegate, BaseActivityPullBinder> {

    List<OrderListItem> defDatas;
    OrderListAdapter adapter;

    List<TransactEvent> transactEvents = new ArrayList<>();

    public void addTransactEvent(TransactEvent transactEvent) {
        if (!transactEvents.contains(transactEvent)) {
            transactEvents.add(transactEvent);
            if (adapter != null) {
                adapter.setTransactEvents(transactEvents);
            }
        }
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle(CommonUtils.getString(R.string.str_title_orderfragment)).setSubTitle(CommonUtils.getString(R.string.str_subtitle_customer_service)).setShowBack(false));
        initList();
        onRefresh();
        viewDelegate.viewHolder.swipeRefreshLayout.setRefreshing(true);
    }

    private void initList() {
        defDatas = new ArrayList<>();
        adapter = new OrderListAdapter(getActivity(), defDatas);
        adapter.setTransactEvents(transactEvents);
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                adapter.removeDot(position);
                if (!adapter.getDatas().get(position).getCancel()) {
                    WaitTransactActivity.startAct(getActivity(), adapter.getDatas().get(position).getId());
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        viewDelegate.setNoDataTxt(CommonUtils.getString(R.string.str_nodata_order));
        initRecycleViewPull(adapter, new LinearLayoutManager(getActivity()));
    }

    protected void clickRightTv() {
        super.clickRightTv();
        Application.getInstance().startCustomerService(getActivity());
    }

    @Override
    public BaseActivityPullBinder getDataBinder(BaseActivityPullDelegate viewDelegate) {
        return new BaseActivityPullBinder(viewDelegate);
    }

    @Override
    public void onResume() {
        super.onResume();
        onRefresh();
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                List<OrderListItem> listItems = GsonUtil.getInstance().toList(data, "list", OrderListItem.class);
                getDataBack(defDatas, listItems, adapter);
                break;
        }
    }

    @Override
    protected Class<BaseActivityPullDelegate> getDelegateClass() {
        return BaseActivityPullDelegate.class;
    }

    @Override
    protected void refreshData() {
        addRequest(binder.orderList(this));
    }
}

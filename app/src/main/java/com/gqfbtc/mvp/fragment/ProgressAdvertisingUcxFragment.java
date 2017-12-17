package com.gqfbtc.mvp.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BasePullFragment;
import com.fivefivelike.mybaselibrary.entity.ResultDialogEntity;
import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.view.dialog.ResultDialog;
import com.gqfbtc.R;
import com.gqfbtc.Utils.UiHeplUtils;
import com.gqfbtc.adapter.MyAdvertisingAdapter;
import com.gqfbtc.dialog.TimeChooseDialog;
import com.gqfbtc.entity.bean.MyAdvertising;
import com.gqfbtc.mvp.databinder.BaseFragmentPullBinder;
import com.gqfbtc.mvp.delegate.BaseFragentPullDelegate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 郭青枫 on 2017/10/27.
 */

public class ProgressAdvertisingUcxFragment extends BasePullFragment<BaseFragentPullDelegate, BaseFragmentPullBinder> {

    List<MyAdvertising> defDatas;
    MyAdvertisingAdapter adapter;
    private static final String ACTION_SHOW = "showing";//展示广告
    private String downAdId = "";

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initList();
    }

    private void initList() {
        defDatas = new ArrayList<>();
        adapter = new MyAdvertisingAdapter(getActivity(), defDatas);
        adapter.setShow(true);
        adapter.setOnAdapterClick(new MyAdvertisingAdapter.OnAdapterClick() {
            @Override
            public void onEditClick(int position) {

            }

            @Override
            public void onShelvesClick(final int position) {
                downAdId = adapter.getDatas().get(position).getId();
                addRequest(binder.adwkc_beforedown(downAdId, ProgressAdvertisingUcxFragment.this));
            }

            @Override
            public void onTimeClick(int position) {
                initTimeDialog(position);
            }
        });
        viewDelegate.setNoDataTxt(CommonUtils.getString(R.string.str_nodata_advertising_start));
        initRecycleViewPull(adapter, new LinearLayoutManager(getActivity()));
        viewDelegate.setIsLoadMore(false);
        viewDelegate.viewHolder.swipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }

    private void initTimeDialog(final int posotion) {
        new TimeChooseDialog(getActivity(), new TimeChooseDialog.OnTimeChooseLinsener() {
            @Override
            public void setOnTimeChooseListener(String day1, String time1, String day2, String time2) {
                addRequest(binder.changetime(adapter.getDatas().get(posotion).getId(), day1 + "_" + time1, day2 + "_" + time2, ProgressAdvertisingUcxFragment.this));
            }
        }).show();
    }

    @Override
    public BaseFragmentPullBinder getDataBinder(BaseFragentPullDelegate viewDelegate) {
        return new BaseFragmentPullBinder(viewDelegate);
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                List<MyAdvertising> list = GsonUtil.getInstance().toList(data, MyAdvertising.class);
                getDataBack(defDatas, list, adapter);
                break;
            case 0x124:
                onRefresh();
                break;
            case 0x125:
                UiHeplUtils.initDefaultToastDialog(getActivity(), info, null);
                onRefresh();
                break;
            case 0126:
                addRequest(binder.down(downAdId, ProgressAdvertisingUcxFragment.this));
                break;
        }
    }

    @Override
    protected Class<BaseFragentPullDelegate> getDelegateClass() {
        return BaseFragentPullDelegate.class;
    }

    @Override
    protected void refreshData() {
        addRequest(binder.adsucx(ACTION_SHOW, this));
    }

    @Override
    public void onClick(View view, int position, Object item) {
        ResultDialogEntity resultDialogEntity = (ResultDialogEntity) item;
        if (position == ResultDialog.CONFIRM_POSITION) {
            if ("S200".equals(resultDialogEntity.getCode()) || "S201".equals(resultDialogEntity.getCode())) {
                //广告确定下架
                addRequest(binder.down(downAdId, ProgressAdvertisingUcxFragment.this));
            }
        }
    }
}
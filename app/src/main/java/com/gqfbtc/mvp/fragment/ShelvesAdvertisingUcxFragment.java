package com.gqfbtc.mvp.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BasePullFragment;
import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
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

public class ShelvesAdvertisingUcxFragment extends BasePullFragment<BaseFragentPullDelegate, BaseFragmentPullBinder> {

    List<MyAdvertising> defDatas;
    MyAdvertisingAdapter adapter;
    private static final String ACTION_CLOSE = "closed";//下架广告


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initList();
    }

    private void initList() {
        defDatas = new ArrayList<>();
        adapter = new MyAdvertisingAdapter(getActivity(), defDatas);
        adapter.setShow(false);
        adapter.setOnAdapterClick(new MyAdvertisingAdapter.OnAdapterClick() {
            @Override
            public void onEditClick(int position) {

            }

            @Override
            public void onShelvesClick(final int position) {
                UiHeplUtils.initDefaultDialog(getActivity(), CommonUtils.getString(R.string.str_warning_isadvertising), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addRequest(binder.up(adapter.getDatas().get(position).getId(), ShelvesAdvertisingUcxFragment.this));
                    }
                }).show();
            }

            @Override
            public void onTimeClick(int position) {
                initTimeDialog(position);
            }
        });
        viewDelegate.setNoDataTxt(getResources().getString(R.string.str_nodata_advertising_end));
        initRecycleViewPull(adapter, new LinearLayoutManager(getActivity()));
        viewDelegate.setIsLoadMore(false);
        viewDelegate.viewHolder.swipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }

    @Override
    public BaseFragmentPullBinder getDataBinder(BaseFragentPullDelegate viewDelegate) {
        return new BaseFragmentPullBinder(viewDelegate);
    }

    private void initTimeDialog(final int posotion) {
        new TimeChooseDialog(getActivity(), new TimeChooseDialog.OnTimeChooseLinsener() {
            @Override
            public void setOnTimeChooseListener(String day1, String time1, String day2, String time2) {
                addRequest(binder.changetime(adapter.getDatas().get(posotion).getId(), day1 + "_" + time1, day2 + "_" + time2, ShelvesAdvertisingUcxFragment.this));
            }
        }).show();
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
        }
    }

    @Override
    protected Class<BaseFragentPullDelegate> getDelegateClass() {
        return BaseFragentPullDelegate.class;
    }

    @Override
    protected void refreshData() {
        addRequest(binder.ads(ACTION_CLOSE, this));
    }
}
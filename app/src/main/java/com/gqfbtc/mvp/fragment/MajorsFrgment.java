package com.gqfbtc.mvp.fragment;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;

import com.fivefivelike.mybaselibrary.base.BaseDataBindFragment;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.gqfbtc.R;
import com.gqfbtc.adapter.MajorsAdvertisingAdapter;
import com.gqfbtc.mvp.databinder.MajorsBinder;
import com.gqfbtc.mvp.delegate.MajorsDelegate;

import java.util.ArrayList;
import java.util.List;

public class MajorsFrgment extends BaseDataBindFragment<MajorsDelegate, MajorsBinder> {

    MajorsAdvertisingAdapter buyMajorsAdvertisingAdapter;
    MajorsAdvertisingAdapter sellMajorsAdvertisingAdapter;


    public interface Linsener {
        void changeMode(Class c);
    }

    Linsener linsener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        linsener = (Linsener) activity;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("交易").setSubTitle("切至普通版").setShowBack(false));
        viewDelegate.getmToolbarSubTitle().setTextColor(getResources().getColor(R.color.color_blue));
        viewDelegate.initInput();
        initList();
    }


    private void initList() {
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            datas.add("");
        }
        buyMajorsAdvertisingAdapter = new MajorsAdvertisingAdapter(getActivity(), datas, MajorsAdvertisingAdapter.type_buy);
        sellMajorsAdvertisingAdapter = new MajorsAdvertisingAdapter(getActivity(), datas, MajorsAdvertisingAdapter.type_sell);
        viewDelegate.viewHolder.rc_buy.setLayoutManager(new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        viewDelegate.viewHolder.rc_sell.setLayoutManager(new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        viewDelegate.viewHolder.rc_buy.setAdapter(buyMajorsAdvertisingAdapter);
        viewDelegate.viewHolder.rc_sell.setAdapter(sellMajorsAdvertisingAdapter);
    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        super.onServiceError(data, info, status, requestCode);
        switch (requestCode) {
        }
    }

    @Override
    protected void clickRightTv() {
        super.clickRightTv();
        //切换普通模式
        linsener.changeMode(MajorsFrgment.class);
    }

    @Override
    protected Class<MajorsDelegate> getDelegateClass() {
        return MajorsDelegate.class;
    }

    @Override
    public MajorsBinder getDataBinder(MajorsDelegate viewDelegate) {
        return new MajorsBinder(viewDelegate);
    }

}

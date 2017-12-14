package com.gqfbtc.mvp.popu;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.fivefivelike.mybaselibrary.view.popupWindow.BasePopupWindow;
import com.gqfbtc.R;
import com.gqfbtc.adapter.HomeRSelectAdapter;
import com.gqfbtc.callback.PopuListOnItemClick;
import com.gqfbtc.entity.HomeRightPopuEntity;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 郭青枫 on 2017/8/17.
 */

public class HomeRightPopu extends BasePopupWindow {


    private RecyclerView recycler_view;
    private List<HomeRightPopuEntity> list;
    private HomeRSelectAdapter adapter;
    PopuListOnItemClick popuListOnItemClick;

    public void setPopuListOnItemClick(PopuListOnItemClick popuListOnItemClick) {
        this.popuListOnItemClick = popuListOnItemClick;
    }

    public HomeRightPopu(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_home_top_right_popu;
    }

    @Override
    public void initView() {
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        recycler_view = findViewById(R.id.recyclerView);
        list = new ArrayList<>();
        adapter = new HomeRSelectAdapter(context, list);
        recycler_view.setLayoutManager(new LinearLayoutManager(context) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        recycler_view.setAdapter(adapter);
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

                adapter.setSelectPositon(adapter.getSelectPositon() == position ? -1 : position);
                if (popuListOnItemClick != null) {
                    popuListOnItemClick.onItemClick(position);
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }


    public HomeRightPopu setSelectItem(String[] items, int[] ids) {
        list.clear();
        List<HomeRightPopuEntity> itemList = new ArrayList<>();
        for (int i = 0; i < items.length; i++) {
            itemList.add(new HomeRightPopuEntity(ids[i], items[i]));
        }
        if (itemList != null && itemList.size() > 0) {
            list.addAll(itemList);
        }
        adapter.notifyDataSetChanged();
        return this;
    }


}

package com.gqfbtc.mvp.popu;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.fivefivelike.mybaselibrary.view.PopupBackView;
import com.fivefivelike.mybaselibrary.view.popupWindow.BasePopupWindow;
import com.gqfbtc.R;
import com.gqfbtc.adapter.HomeLSelectAdapter;
import com.gqfbtc.callback.PopuListOnItemClick;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 郭青枫 on 2017/8/17.
 */

public class HomeLeftPopu extends BasePopupWindow {


    private RecyclerView recycler_view;
    private RelativeLayout rel_root;
    Button view_dimess;
    PopupBackView popupBackView;
    private List<String> list;
    private HomeLSelectAdapter adapter;
    PopuListOnItemClick popuListOnItemClick;
    int selectPositon = 0;

    public void setSelectPositon(int firstPositon, int secondPosition) {
        if (firstPositon == 0 && secondPosition == 0) {
            this.selectPositon = 0;
        } else if (firstPositon == 0 && secondPosition == 1) {
            this.selectPositon = 1;
        } else if (firstPositon == 1 && secondPosition == 0) {
            this.selectPositon = 2;
        } else if (firstPositon == 1 && secondPosition == 1) {
            this.selectPositon = 3;
        }
    }

    public HomeLeftPopu(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_home_top_left_popu;
    }

    @Override
    public void initView() {
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        recycler_view = findViewById(R.id.recyclerView);
        popupBackView = findViewById(R.id.popupBackView);
        rel_root = findViewById(R.id.rel_root);
        rel_root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        list = new ArrayList<>();
        adapter = new HomeLSelectAdapter(context, list);
        adapter.setPositon(selectPositon);
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
                dismiss();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    public void setPopuListOnItemClick(PopuListOnItemClick popuListOnItemClick) {
        this.popuListOnItemClick = popuListOnItemClick;
    }

    public void showDropDown(View view) {
        if (adapter != null) {
            adapter.setSelectPositon(selectPositon);
        }
        this.showAsDropDown(view);
    }


    public HomeLeftPopu setSelectItem(String[] items) {
        list.clear();
        List<String> itemList = Arrays.asList(items);
        if (itemList != null && itemList.size() > 0) {
            list.addAll(itemList);
        }
        adapter.notifyDataSetChanged();
        return this;
    }


}

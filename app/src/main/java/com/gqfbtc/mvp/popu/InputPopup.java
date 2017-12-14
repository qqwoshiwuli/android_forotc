package com.gqfbtc.mvp.popu;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.fivefivelike.mybaselibrary.view.popupWindow.BasePopupWindow;
import com.gqfbtc.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by 郭青枫 on 2017/8/17.
 */

public class InputPopup extends BasePopupWindow {


    GetDataBack<InputPopupPOJO> getDataBack;
    private RecyclerView recycler_view;
    private List<String> list;
    private PopupAdapter adapter;

    public InputPopup(Context context, GetDataBack<InputPopupPOJO> getDataBack) {
        super(context);
        this.getDataBack = getDataBack;
    }

    @Override
    public int getLayoutId() {
        return R.layout.popup_txt;
    }

    @Override
    public void initView() {
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setWidth(context.getResources().getDimensionPixelOffset(R.dimen.trans_270px));
        recycler_view = findViewById(R.id.rv_pop);
        list = new ArrayList<>();
        adapter = new PopupAdapter(context, list);
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
                if (getDataBack != null) {
                    getDataBack.onBack(new InputPopupPOJO(InputPopup.this, position));
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        findViewById(R.id.view_dimess).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public InputPopup setSelectItem(String[] items) {
        list.clear();
        List<String> itemList = Arrays.asList(items);
        if (itemList != null && itemList.size() > 0) {
            list.addAll(itemList);
        }
        adapter.notifyDataSetChanged();
        return this;
    }

    public static class InputPopupPOJO {
        public InputPopup inputPopup;
        public int position;

        public InputPopupPOJO(InputPopup inputPopup, int position) {
            this.inputPopup = inputPopup;
            this.position = position;
        }
    }


    class PopupAdapter extends CommonAdapter<String> {

        public PopupAdapter(Context context, List<String> datas) {
            super(context, R.layout.layout_pop_txt, datas);
        }

        @Override
        protected void convert(ViewHolder holder, String s, int position) {
            holder.setText(R.id.tv_popu, s);
            holder.setVisible(R.id.view_line, position != mDatas.size() - 1);
        }
    }

    public interface GetDataBack<T> {
        void onBack(T t);
    }


}

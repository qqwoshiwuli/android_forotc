package com.gqfbtc.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.gqfbtc.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 郭青枫 on 2017/10/28.
 */

public class Adapter_FAQ extends CommonAdapter<String> {


    private TextView tv_title;
    private RecyclerView rv_faq;

    public Adapter_FAQ(Context context, List<String> datas) {
        super(context, R.layout.adapter_faq, datas);
    }

    @Override
    protected void convert(final ViewHolder holder, String s, final int position) {
        tv_title = holder.getView(R.id.tv_title);
        rv_faq = holder.getView(R.id.rv_faq);

        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            datas.add("");
        }
        if (((RecyclerView) holder.getView(R.id.rv_faq)).getAdapter() == null) {
            Adapter_faq_item adapter_faq_item = new Adapter_faq_item(mContext, datas);
            rv_faq.setLayoutManager(new LinearLayoutManager(mContext) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });
            rv_faq.setAdapter(adapter_faq_item);
            adapter_faq_item.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder h, int position) {
                    Adapter_faq_item adapter_faq_item1 = ((Adapter_faq_item) ((RecyclerView) holder.getView(R.id.rv_faq)).getAdapter());
                    adapter_faq_item1.setSelectPositon(adapter_faq_item1.getSelectPositon() == position ? -1 : position);
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                    return false;
                }
            });
        } else {
            ((Adapter_faq_item) ((RecyclerView) holder.getView(R.id.rv_faq)).getAdapter()).setDatas(datas);
        }

    }

}

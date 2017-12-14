package com.gqfbtc.adapter;

import android.content.Context;
import android.widget.TextView;

import com.gqfbtc.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by 郭青枫 on 2017/10/21.
 */

public class RecordAdapter extends CommonAdapter<String> {


    private TextView tv_num;
    private TextView tv_time;
    private TextView tv_statu;

    public RecordAdapter(Context context, List<String> datas) {
        super(context, R.layout.adapter_record, datas);
    }

    @Override
    protected void convert(ViewHolder holder, String s, final int position) {
        tv_num = holder.getView(R.id.tv_num);
        tv_time = holder.getView(R.id.tv_time);
        tv_statu = holder.getView(R.id.tv_statu);
    }

}

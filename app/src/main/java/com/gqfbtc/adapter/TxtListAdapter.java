package com.gqfbtc.adapter;

import android.content.Context;
import android.widget.TextView;

import com.gqfbtc.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by 郭青枫 on 2017/11/11.
 */

public class TxtListAdapter extends CommonAdapter<String> {


    private TextView title;

    public TxtListAdapter(Context context, List<String> datas) {
        super(context, R.layout.txt_left_adapter, datas);
    }

    @Override
    protected void convert(ViewHolder holder, String s, final int position) {
        title = holder.getView(R.id.title);
        title.setText(s);
        title.setBackgroundResource(R.color.white);
    }

}

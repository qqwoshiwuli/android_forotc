package com.gqfbtc.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.view.IconFontTextview;
import com.gqfbtc.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by 郭青枫 on 2017/10/22.
 */

public class HomeLSelectAdapter extends CommonAdapter<String> {


    private TextView tv_title;
    private IconFontTextview tv_dui;

    int selectPositon = 0;
    private View view_line;

    public HomeLSelectAdapter(Context context, List<String> datas) {
        super(context, R.layout.adapter_home_top_left, datas);
    }


    @Override
    protected void convert(ViewHolder holder, String s, final int position) {
        tv_title = holder.getView(R.id.tv_title);
        tv_dui = holder.getView(R.id.tv_dui);
        view_line = holder.getView(R.id.view_line);
        tv_title.setText(s);
        if (position == mDatas.size() - 1) {
            view_line.setVisibility(View.GONE);
        }
        tv_dui.setVisibility(selectPositon == position ? View.VISIBLE : View.INVISIBLE);
        tv_title.setTextColor(selectPositon == position ? mContext.getResources().getColor(R.color.color_333333) : mContext.getResources().getColor(R.color.color_999999));
    }

    public int getSelectPositon() {
        return selectPositon;
    }

    public void setPositon(int selectPositon) {
        this.selectPositon = selectPositon;
    }

    public void setSelectPositon(int selectPositon) {
        this.selectPositon = selectPositon;
        this.notifyDataSetChanged();
    }
}

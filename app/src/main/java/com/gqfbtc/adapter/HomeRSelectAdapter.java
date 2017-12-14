package com.gqfbtc.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.view.IconFontTextview;
import com.gqfbtc.R;
import com.gqfbtc.entity.HomeRightPopuEntity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by 郭青枫 on 2017/10/22.
 */

public class HomeRSelectAdapter extends CommonAdapter<HomeRightPopuEntity> {


    private TextView tv_title;
    private IconFontTextview tv_dui;

    int selectPositon = -1;
    private View view_line;

    public HomeRSelectAdapter(Context context, List<HomeRightPopuEntity> datas) {
        super(context, R.layout.adapter_home_top_right, datas);
    }

    @Override
    protected void convert(ViewHolder holder, HomeRightPopuEntity s, final int position) {
        tv_title = holder.getView(R.id.tv_title);
        tv_dui = holder.getView(R.id.tv_dui);
        view_line = holder.getView(R.id.view_line);
        tv_title.setText(s.getTxt());
        if (position == mDatas.size() - 1) {
            view_line.setVisibility(View.GONE);
        }
        tv_dui.setText(CommonUtils.getString(s.getId()));
    }

    public int getSelectPositon() {
        return selectPositon;
    }

    public void setSelectPositon(int selectPositon) {
        this.selectPositon = selectPositon;
        this.notifyDataSetChanged();
    }
}

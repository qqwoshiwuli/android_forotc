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
 * Created by 郭青枫 on 2017/10/28.
 */

public class Adapter_faq_item extends CommonAdapter<String> {


    private TextView tv_title;
    private IconFontTextview tv_img;
    private TextView tv_content;

    int selectPositon = -1;

    public int getSelectPositon() {
        return selectPositon;
    }

    public void setSelectPositon(int selectPositon) {
        int oldPosition = this.selectPositon;
        this.notifyItemChanged(oldPosition);
        this.selectPositon = selectPositon;
        if (selectPositon != -1) {
            this.notifyItemChanged(selectPositon);
        }
    }

    public Adapter_faq_item(Context context, List<String> datas) {
        super(context, R.layout.adapter_faq_item, datas);
    }

    public void setDatas(List<String> datas) {
        this.mDatas.clear();
        this.mDatas.addAll(datas);
        this.notifyDataSetChanged();
    }

    @Override
    protected void convert(ViewHolder holder, String s, final int position) {
        tv_title = holder.getView(R.id.tv_title);
        tv_img = holder.getView(R.id.tv_img);
        tv_content = holder.getView(R.id.tv_content);

        if (selectPositon == position) {
            tv_img.setTextColor(mContext.getResources().getColor(R.color.color_333333));
            tv_img.setRotation(0);
            tv_content.setVisibility(View.VISIBLE);
        } else {
            tv_img.setTextColor(mContext.getResources().getColor(R.color.color_999999));
            tv_img.setRotation(-90);
            tv_content.setVisibility(View.GONE);
        }

    }

}

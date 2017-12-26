package com.gqfbtc.adapter;

import android.content.Context;
import android.widget.TextView;

import com.gqfbtc.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by 郭青枫 on 2017/12/26 0026.
 */

public class MajorsAdvertisingAdapter extends CommonAdapter<String> {

    public static final int type_buy = 0;
    public static final int type_sell = 1;

    private TextView tv_price;
    private TextView tv_num;
    private TextView tv_amount;
    private TextView tv_commit;

    private int select_type;

    public MajorsAdvertisingAdapter(Context context, List<String> datas, int type) {
        super(context, R.layout.adapter_majors_advertising, datas);
        select_type = type;
    }

    @Override
    protected void convert(ViewHolder holder, final String s, final int position) {
        tv_price = holder.getView(R.id.tv_price);
        tv_num = holder.getView(R.id.tv_num);
        tv_amount = holder.getView(R.id.tv_amount);
        tv_commit = holder.getView(R.id.tv_commit);
        if (select_type == type_buy) {
            tv_price.setTextColor(mContext.getResources().getColor(R.color.color_25A73F));
            tv_num.setTextColor(mContext.getResources().getColor(R.color.color_25A73F));
            tv_amount.setTextColor(mContext.getResources().getColor(R.color.color_25A73F));
            tv_commit.setTextColor(mContext.getResources().getColor(R.color.color_25A73F));
            tv_commit.setBackgroundResource(R.drawable.select_green_border_t10_whitebg_btn);
        } else {
            tv_price.setTextColor(mContext.getResources().getColor(R.color.color_f45638));
            tv_num.setTextColor(mContext.getResources().getColor(R.color.color_f45638));
            tv_amount.setTextColor(mContext.getResources().getColor(R.color.color_f45638));
            tv_commit.setTextColor(mContext.getResources().getColor(R.color.color_f45638));
            tv_commit.setBackgroundResource(R.drawable.select_red_border_t10_whitebg_btn);
        }

    }


}

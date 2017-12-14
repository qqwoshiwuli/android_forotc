package com.gqfbtc.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.gqfbtc.R;
import com.gqfbtc.entity.bean.OrderListItem;
import com.gqfbtc.entity.event.TransactEvent;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by 郭青枫 on 2017/10/19.
 */

public class OrderListAdapter extends CommonAdapter<OrderListItem> {


    private TextView tv_title;
    private TextView tv_type;
    private TextView tv_time;
    private TextView tv_status;
    private TextView tv_price;
    private TextView tv_order_money;
    List<TransactEvent> transactEvents;
    private View view_dot;

    public void setTransactEvents(List<TransactEvent> transactEvents) {
        if (transactEvents == null) {
            this.transactEvents = transactEvents;
        } else {
            this.transactEvents = transactEvents;
            this.notifyDataSetChanged();
        }
    }

    public void removeDot(int position) {
        if (transactEvents != null) {
            for (int i = 0; i < transactEvents.size(); i++) {
                if (transactEvents.get(i).getCode().equals(mDatas.get(position).getCode())) {
                    transactEvents.remove(i);
                    i = i - 1;
                }
            }
        }
        this.notifyItemChanged(position);
    }

    public OrderListAdapter(Context context, List<OrderListItem> datas) {
        super(context, R.layout.adapter_order_list, datas);
    }

    @Override
    protected void convert(ViewHolder holder, OrderListItem s, final int position) {
        tv_title = holder.getView(R.id.tv_title);
        tv_type = holder.getView(R.id.tv_type);
        tv_time = holder.getView(R.id.tv_time);
        tv_status = holder.getView(R.id.tv_status);
        tv_price = holder.getView(R.id.tv_price);
        tv_order_money = holder.getView(R.id.tv_order_money);
        view_dot = holder.getView(R.id.view_dot);

        boolean isShowDot = false;
        for (int i = 0; i < transactEvents.size(); i++) {
            if (s.getCode().equals(transactEvents.get(i).getCode())) {
                isShowDot = true;
                break;
            }
        }
        view_dot.setVisibility(isShowDot ? View.VISIBLE : View.INVISIBLE);

        tv_title.setText("订单-" + s.getCode());
        tv_time.setText(TimeUtils.millis2String(s.getCreateTime(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
        tv_status.setText(s.getTitle());
        tv_price.setText(s.getDealPriceStr());
        tv_type.setText(s.getPayType());
        tv_order_money.setText(s.getDealMoneyStr());

        if (s.getComplete() || s.getCancel()) {
            tv_title.setTextColor(mContext.getResources().getColor(R.color.color_font2));
            tv_status.setTextColor(mContext.getResources().getColor(R.color.color_font2));
        } else {
            tv_title.setTextColor(mContext.getResources().getColor(R.color.color_font1));
            tv_status.setTextColor(mContext.getResources().getColor(R.color.color_font1));
        }

    }

}

package com.gqfbtc.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.gqfbtc.R;
import com.gqfbtc.entity.bean.MyAdvertising;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by 郭青枫 on 2017/10/27.
 */

public class MyAdvertisingAdapter extends CommonAdapter<MyAdvertising> {


    private TextView tv_type;
    private TextView tv_all_price;
    private TextView tv_title;
    private TextView tv_change;
    private TextView tv_edit;
    private TextView tv_shelves;
    private TextView tv_floor;
    private TextView tv_code;
    private TextView tv_time;
    private TextView tv_create_time;
    private TextView tv_volume;


    private boolean isShow = false;

    public void setShow(boolean show) {
        isShow = show;
    }

    public MyAdvertisingAdapter(Context context, List<MyAdvertising> datas) {
        super(context, R.layout.adapter_my_advertising, datas);
    }

    @Override
    protected void convert(ViewHolder holder, MyAdvertising s, final int position) {
        tv_type = holder.getView(R.id.tv_type);
        tv_all_price = holder.getView(R.id.tv_all_price);
        tv_title = holder.getView(R.id.tv_title);
        tv_change = holder.getView(R.id.tv_change);
        tv_edit = holder.getView(R.id.tv_edit);
        tv_shelves = holder.getView(R.id.tv_shelves);
        tv_floor = holder.getView(R.id.tv_floor);
        tv_code = holder.getView(R.id.tv_code);
        tv_time = holder.getView(R.id.tv_time);
        tv_create_time = holder.getView(R.id.tv_create_time);
        tv_volume = holder.getView(R.id.tv_volume);

        tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onAdapterClick != null) {
                    onAdapterClick.onEditClick(position);
                }
            }
        });
        tv_shelves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onAdapterClick != null) {
                    onAdapterClick.onShelvesClick(position);
                }
            }
        });
        tv_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onAdapterClick != null) {
                    onAdapterClick.onTimeClick(position);
                }
            }
        });

        tv_type.setText(s.isSale() ? "出售 BTC" : "购买 BTC");
        tv_title.setText(s.isSale() ? "出售总额" : "购买总额");

        tv_all_price.setText(s.getPrice());
        tv_floor.setText(s.getThreshold());
        tv_create_time.setText(TimeUtils.millis2String(s.getCreateTime(), new SimpleDateFormat("yyyy-MM-dd")));
        tv_code.setText(s.getCode());
        tv_time.setText(s.getTime());

        tv_volume.setText(new BigDecimal(s.getPrice()).multiply(new BigDecimal(s.getQuantity())).toString());


        BigDecimal price = new BigDecimal(s.getPrice());
        BigDecimal btcNum = new BigDecimal(s.getQuantity());
        tv_volume.setText(price.multiply(btcNum).toString());

        //tv_time.setText("每日 " + s.getStartTime().getHour() + ":" + s.getStartTime().getMinute() + " - " + "每日 " + s.getEndTime().getHour() + ":" + s.getEndTime().getMinute());

        tv_shelves.setText(isShow ? "下架" : "上架");
    }

    OnAdapterClick onAdapterClick;

    public void setOnAdapterClick(OnAdapterClick onAdapterClick) {
        this.onAdapterClick = onAdapterClick;
    }

    public interface OnAdapterClick {
        void onEditClick(int position);

        void onShelvesClick(int position);

        void onTimeClick(int position);
    }
}

package com.gqfbtc.adapter;

import android.content.Context;
import android.widget.TextView;

import com.gqfbtc.R;
import com.gqfbtc.entity.bean.Withdrawal;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by 郭青枫 on 2017/10/21.
 */

public class WithdrawalRecordAdapter extends CommonAdapter<Withdrawal> {


    private TextView tv_num;
    private TextView tv_time;
    private TextView tv_statu;

    public WithdrawalRecordAdapter(Context context, List<Withdrawal> datas) {
        super(context, R.layout.adapter_record, datas);
    }

    @Override
    protected void convert(ViewHolder holder, Withdrawal s, final int position) {
        tv_num = holder.getView(R.id.tv_num);
        tv_time = holder.getView(R.id.tv_time);
        tv_statu = holder.getView(R.id.tv_statu);

        tv_num.setText(new BigDecimal(s.getQuantity()).setScale(8).toString());
        tv_time.setText(com.blankj.utilcode.util.TimeUtils.millis2String(s.getCreateTime(), new SimpleDateFormat("yyyy-MM-dd")));
        tv_statu.setText(s.getStatus());

    }

}

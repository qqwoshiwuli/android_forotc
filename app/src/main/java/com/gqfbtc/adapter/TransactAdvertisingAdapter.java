package com.gqfbtc.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gqfbtc.R;
import com.gqfbtc.entity.bean.PaymentBTCETHAddress;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 郭青枫 on 2017/11/13.
 */

public class TransactAdvertisingAdapter extends CommonAdapter<PaymentBTCETHAddress> {


    private AppCompatImageView iv_pic;
    private TextView tv_content;
    private LinearLayout lin_root;

    List<Integer> selectPositions;

    public List<Integer> getSelectPositions() {
        return selectPositions;
    }

    public String getSelectId() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < selectPositions.size(); i++) {
            if (i > 0) {
                stringBuffer.append(",");
            }
            stringBuffer.append(mDatas.get(selectPositions.get(i)).getId() + "");
        }
        return stringBuffer.toString();
    }

    public void setSelectPositions(Integer position) {
        if (selectPositions.contains(position)) {
            selectPositions.remove(selectPositions.indexOf(position));
        } else {
            selectPositions.add(position);
        }
        this.notifyItemChanged(position);
    }

    public TransactAdvertisingAdapter(Context context, List<PaymentBTCETHAddress> datas) {
        super(context, R.layout.adapter_transact_advertising_item, datas);
        selectPositions = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            selectPositions.add(i);
        }
    }

    @Override
    protected void convert(ViewHolder holder, PaymentBTCETHAddress s, final int position) {
        iv_pic = holder.getView(R.id.iv_pic);
        tv_content = holder.getView(R.id.tv_content);
        lin_root = holder.getView(R.id.lin_root);
        iv_pic.setBackgroundResource(selectPositions.contains(position) ? R.drawable.ic_choose_on : R.drawable.ic_choose_off);

        String address = s.getCollectionAddr().substring(0, 4) + "***" + s.getCollectionAddr().substring(s.getCollectionAddr().length() - 3, s.getCollectionAddr().length());

        tv_content.setText(s.getBankName() + "\t" + address);
    }

}


package com.gqfbtc.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gqfbtc.R;
import com.gqfbtc.entity.bean.PaymentBTCETHAddress;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by 郭青枫 on 2017/10/21.
 */

public class DialogAddressAdapter extends CommonAdapter<PaymentBTCETHAddress> {


    private TextView tv_name;
    private TextView tv_bank;
    private TextView tv_address;
    private TextView tv_msg;


    int selectPositon = -1;
    private LinearLayout lin_root;
    private AppCompatImageView iv_pic;
    private View view_line;

    public int getSelectPositon() {
        return selectPositon;
    }

    public void setSelectPositon(int selectPositon) {
        int oldPosition = this.selectPositon;
        if (oldPosition != -1) {
            this.notifyItemChanged(oldPosition);
        }
        this.selectPositon = selectPositon;
        if (selectPositon != -1) {
            this.notifyItemChanged(selectPositon);
        }
    }

    public DialogAddressAdapter(Context context, List<PaymentBTCETHAddress> datas) {
        super(context, R.layout.adapter_dialog_address, datas);
    }

    @Override
    protected void convert(ViewHolder holder, PaymentBTCETHAddress s, final int position) {
        tv_name = holder.getView(R.id.tv_name);
        tv_bank = holder.getView(R.id.tv_bank);
        tv_address = holder.getView(R.id.tv_address);
        tv_msg = holder.getView(R.id.tv_msg);
        lin_root = holder.getView(R.id.lin_root);
        iv_pic = holder.getView(R.id.iv_pic);
        view_line = holder.getView(R.id.view_line);

        view_line.setVisibility(position == 0 ? View.GONE : View.VISIBLE);

        tv_msg.setVisibility(TextUtils.isEmpty(s.getBranchName()) ? View.GONE : View.VISIBLE);
        tv_msg.setText(s.getBranchName());
        tv_bank.setText(!"3".equals(s.getCollectionType()) && !"4".equals(s.getCollectionType()) ? s.getBankName() : "");
        tv_name.setText(!"3".equals(s.getCollectionType()) && !"4".equals(s.getCollectionType()) ? s.getOwnerName() : s.getAlias());
        tv_address.setText(s.getCollectionAddr());
        iv_pic.setVisibility(View.GONE);
        iv_pic.setBackgroundResource(selectPositon == position ? R.drawable.ic_choose_on : R.drawable.ic_choose_off);


    }

}

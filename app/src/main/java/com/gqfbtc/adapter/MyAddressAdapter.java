package com.gqfbtc.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gqfbtc.R;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.gqfbtc.entity.bean.PaymentBTCETHAddress;
import com.gqfbtc.mvp.fragment.EthAndBtcAddressFragment;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by 郭青枫 on 2017/10/23.
 */

public class MyAddressAdapter extends CommonAdapter<PaymentBTCETHAddress> {


    private TextView tv_name;
    private TextView tv_bank;
    private TextView tv_address;
    private LinearLayout lin_delect;

    private int type;
    private TextView tv_msg;
    private LinearLayout lin_copy;

    private DefaultClickLinsener defaultClickLinsener;

    public void setDefaultClickLinsener(DefaultClickLinsener defaultClickLinsener) {
        this.defaultClickLinsener = defaultClickLinsener;
    }

    public void setType(int type) {
        this.type = type;
    }


    public MyAddressAdapter(Context context, List<PaymentBTCETHAddress> datas) {
        super(context, R.layout.adapter_my_address, datas);
    }

    @Override
    protected void convert(ViewHolder holder, final PaymentBTCETHAddress s, final int position) {
        tv_name = holder.getView(R.id.tv_name);
        tv_bank = holder.getView(R.id.tv_bank);
        tv_address = holder.getView(R.id.tv_address);
        lin_delect = holder.getView(R.id.lin_delect);
        tv_msg = holder.getView(R.id.tv_msg);
        lin_copy = holder.getView(R.id.lin_copy);

        if (type == EthAndBtcAddressFragment.type_btc) {
            tv_name.setText(s.getAlias());
            tv_address.setText(s.getCollectionAddr());
            tv_bank.setVisibility(View.GONE);
            tv_msg.setVisibility(View.GONE);
            lin_delect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (defaultClickLinsener != null) {
                        defaultClickLinsener.onClick(view, position, s);
                    }
                }
            });
            lin_copy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (defaultClickLinsener != null) {
                        defaultClickLinsener.onClick(view, position, s);
                    }
                }
            });
        } else if (type == EthAndBtcAddressFragment.type_eth) {

        } else if (type == EthAndBtcAddressFragment.type_collection) {
            tv_name.setText(s.getOwnerName());
            tv_bank.setText(s.getBankName());
            tv_msg.setText(s.getBranchName());
            tv_msg.setVisibility(TextUtils.isEmpty(s.getBranchName()) ? View.GONE : View.VISIBLE);
            tv_address.setText(s.getCollectionAddr());
            lin_copy.setVisibility(View.GONE);
            lin_delect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (defaultClickLinsener != null) {
                        defaultClickLinsener.onClick(view, position, s);
                    }
                }
            });
        }

    }

}

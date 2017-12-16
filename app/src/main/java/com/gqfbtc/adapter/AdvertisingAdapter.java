package com.gqfbtc.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gqfbtc.R;
import com.gqfbtc.Utils.glide.GlideUtils;
import com.gqfbtc.entity.bean.HomeAdvertising;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 郭青枫 on 2017/10/17.
 */

public class AdvertisingAdapter extends CommonAdapter<HomeAdvertising> {


    private CircleImageView ic_pic;
    private TextView tv_name;
    private TextView tv_money;
    private TextView tv_coin;
    private TextView tv_sell_type;
    private TextView tv_buy;
    private LinearLayout lin_buy;
    private ImageView iv_jisu;
    private ImageView iv_dahu;
    private LinearLayout lin_lable;
    private LinearLayout lin_ucx_lable;
    private TextView tv_response;
    private TextView tv_transact_num;

    public interface OnAdapterClick {
        void onClick(int position);
    }

    public AdvertisingAdapter(Context context, List<HomeAdvertising> datas) {
        super(context, R.layout.adapter_advertising, datas);
    }

    @Override
    protected void convert(ViewHolder holder, final HomeAdvertising s, final int position) {
        ic_pic = holder.getView(R.id.ic_pic);
        tv_name = holder.getView(R.id.tv_name);
        tv_sell_type = holder.getView(R.id.tv_sell_type);
        tv_money = holder.getView(R.id.tv_money);
        tv_coin = holder.getView(R.id.tv_coin);
        tv_buy = holder.getView(R.id.tv_buy);
        lin_buy = holder.getView(R.id.lin_buy);
        iv_jisu = holder.getView(R.id.iv_jisu);
        iv_dahu = holder.getView(R.id.iv_dahu);
        lin_lable = holder.getView(R.id.lin_lable);
        lin_ucx_lable = holder.getView(R.id.lin_ucx_lable);
        tv_response = holder.getView(R.id.tv_response);
        tv_transact_num = holder.getView(R.id.tv_transact_num);

        tv_buy.setText(s.isSale() ? "购买" : "出售");

        GlideUtils.loadImage(s.getOwnerAvatar(), ic_pic);

        String str;
        if (s.getOwnerNickName().length() > 5) {
            str = s.getOwnerNickName().substring(0, 5) + "...";
        } else {
            str = s.getOwnerNickName();
        }


        tv_money.setText(s.getPrice());

        if (HomeAdvertising.coin_type_btc.equals(s.getCurrency())) {
            tv_sell_type.setText("限额 " + s.getTradeFloorAmount() + " ~ " + s.getTradeAmount() + " CNY");
            tv_name.setText(str + " | " + s.getOwnScore());
            iv_jisu.setVisibility(s.isTagsExpress() ? View.VISIBLE : View.GONE);
            iv_dahu.setVisibility(s.isTagsKA() ? View.VISIBLE : View.GONE);
            lin_ucx_lable.setVisibility(View.GONE);
            lin_lable.setVisibility(View.VISIBLE);
        } else {
            tv_sell_type.setText("限额 " + s.getTradeFloorAmount() + " ~ " + s.getLeftQuantity() + " 个");
            tv_name.setText(str + " | " + s.getRespondRatioStr());
            lin_lable.setVisibility(View.GONE);
            lin_ucx_lable.setVisibility(View.VISIBLE);
            tv_response.setText(s.getRespondTimeStr());
            tv_transact_num.setText(s.getSuccCountStr());
        }


    }


}

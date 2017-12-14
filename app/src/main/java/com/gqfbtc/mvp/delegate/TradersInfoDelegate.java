package com.gqfbtc.mvp.delegate;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.gqfbtc.R;

public class TradersInfoDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_traders_info;
    }


    public static class ViewHolder {
        public View rootView;
        public TextView tv_seller_title;
        public TextView tv_seller_phone;
        public RecyclerView rv_seller_add;
        public TextView tv_buyer_title;
        public TextView tv_buyer_phone;
        public TextView tv_seller_coin_add;
        public RecyclerView rv_buyer_add;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_seller_title = (TextView) rootView.findViewById(R.id.tv_seller_title);
            this.tv_seller_phone = (TextView) rootView.findViewById(R.id.tv_seller_phone);
            this.rv_seller_add = (RecyclerView) rootView.findViewById(R.id.rv_seller_add);
            this.tv_buyer_title = (TextView) rootView.findViewById(R.id.tv_buyer_title);
            this.tv_buyer_phone = (TextView) rootView.findViewById(R.id.tv_buyer_phone);
            this.tv_seller_coin_add = (TextView) rootView.findViewById(R.id.tv_seller_coin_add);
            this.rv_buyer_add = (RecyclerView) rootView.findViewById(R.id.rv_buyer_add);
        }

    }
}

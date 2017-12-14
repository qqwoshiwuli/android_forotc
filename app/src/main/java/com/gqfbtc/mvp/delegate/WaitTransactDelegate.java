package com.gqfbtc.mvp.delegate;


import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.gqfbtc.R;
import com.gqfbtc.widget.SingleLineZoomTextView;
import com.gqfbtc.widget.WaitTransactSwipeRefreshLayout;

public class WaitTransactDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wait_transact;
    }


    public static class ViewHolder {
        public View rootView;
        public SingleLineZoomTextView tv_total_price;
        public TextView tv_poundage;
        public SingleLineZoomTextView tv_unit_price;
        public TextView tv_btc;
        public SingleLineZoomTextView tv_freeze;
        public SingleLineZoomTextView tv_payment;
        public TextView tv_paytype;
        public LinearLayout lin_my_pay_type;
        public TextView tv_collection;
        public FrameLayout fl_collection;
        public TextView tv_call_service;
        public FrameLayout fl_call_service;
        public LinearLayout lin_top;
        public FrameLayout fl_top;
        public WaitTransactSwipeRefreshLayout swipeRefreshLayout;
        public FrameLayout fl_root;
        public LinearLayout lin_root;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_total_price = (SingleLineZoomTextView) rootView.findViewById(R.id.tv_total_price);
            this.tv_poundage = (TextView) rootView.findViewById(R.id.tv_poundage);
            this.tv_unit_price = (SingleLineZoomTextView) rootView.findViewById(R.id.tv_unit_price);
            this.tv_btc = (TextView) rootView.findViewById(R.id.tv_btc);
            this.tv_freeze = (SingleLineZoomTextView) rootView.findViewById(R.id.tv_freeze);
            this.tv_payment = (SingleLineZoomTextView) rootView.findViewById(R.id.tv_payment);
            this.tv_paytype = (TextView) rootView.findViewById(R.id.tv_paytype);
            this.lin_my_pay_type = (LinearLayout) rootView.findViewById(R.id.lin_my_pay_type);
            this.tv_collection = (TextView) rootView.findViewById(R.id.tv_collection);
            this.fl_collection = (FrameLayout) rootView.findViewById(R.id.fl_collection);
            this.tv_call_service = (TextView) rootView.findViewById(R.id.tv_call_service);
            this.fl_call_service = (FrameLayout) rootView.findViewById(R.id.fl_call_service);
            this.lin_top = (LinearLayout) rootView.findViewById(R.id.lin_top);
            this.fl_top = (FrameLayout) rootView.findViewById(R.id.fl_top);
            this.swipeRefreshLayout = (WaitTransactSwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
            this.fl_root = (FrameLayout) rootView.findViewById(R.id.fl_root);
            this.lin_root = (LinearLayout) rootView.findViewById(R.id.lin_root);
        }

    }
}

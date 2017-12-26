package com.gqfbtc.mvp.delegate;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.gqfbtc.R;

import cn.bingoogolapple.bgabanner.BGABanner;

public class MajorsDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_majors;
    }

    TextWatcher buyTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            viewHolder.tv_buy_commit.setEnabled(!TextUtils.isEmpty(viewHolder.et_buy_num.getText().toString()) || !TextUtils.isEmpty(viewHolder.et_buy_money.getText().toString()));
        }
    };
    TextWatcher sellTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            viewHolder.tv_sell_commit.setEnabled(!TextUtils.isEmpty(viewHolder.et_sell_num.getText().toString()) || !TextUtils.isEmpty(viewHolder.et_sell_money.getText().toString()));
        }
    };
    
    public void initInput() {
        viewHolder.et_buy_num.addTextChangedListener(buyTextWatcher);
        viewHolder.et_buy_money.addTextChangedListener(buyTextWatcher);
        viewHolder.et_sell_num.addTextChangedListener(sellTextWatcher);
        viewHolder.et_sell_money.addTextChangedListener(sellTextWatcher);
        viewHolder.tv_buy_commit.setEnabled(false);
        viewHolder.tv_sell_commit.setEnabled(false);

    }


    public void initBtc() {
        viewHolder.tv_buy_title.setText("买入BTC");
        viewHolder.tv_sell_title.setText("卖出BTC");
        viewHolder.tv_buy_num_type.setText("BTC");
        viewHolder.tv_sell_num_type.setText("BTC");
        viewHolder.tv_num_title.setText("数量 BTC");
    }

    public void initEth() {
        viewHolder.tv_buy_title.setText("买入ETH");
        viewHolder.tv_sell_title.setText("卖出ETH");
        viewHolder.tv_buy_num_type.setText("ETH");
        viewHolder.tv_sell_num_type.setText("ETH");
        viewHolder.tv_num_title.setText("数量 ETH");
    }

    public static class ViewHolder {
        public View rootView;
        public TextView tv_select;
        public LinearLayout lin_top_left_btn;
        public BGABanner banner_guide_content;
        public TextView tv_buy_title;
        public EditText et_buy_num;
        public TextView tv_buy_num_type;
        public EditText et_buy_money;
        public TextView tv_buy_money_type;
        public TextView tv_buy_price;
        public LinearLayout lin_buy_price;
        public TextView tv_buy_enquiries;
        public TextView tv_buy_commit;
        public TextView tv_sell_title;
        public TextView tv_sell_subtitle;
        public EditText et_sell_num;
        public TextView tv_sell_num_type;
        public EditText et_sell_money;
        public TextView tv_sell_money_type;
        public TextView tv_sell_price;
        public LinearLayout lin_sell_price;
        public TextView tv_sell_enquiries;
        public TextView tv_sell_commit;
        public TextView tv_remark;
        public TextView tv_num_title;
        public RecyclerView rc_sell;
        public RecyclerView rc_buy;
        public SwipeRefreshLayout swipeRefreshLayout;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_select = (TextView) rootView.findViewById(R.id.tv_select);
            this.lin_top_left_btn = (LinearLayout) rootView.findViewById(R.id.lin_top_left_btn);
            this.banner_guide_content = (BGABanner) rootView.findViewById(R.id.banner_guide_content);
            this.tv_buy_title = (TextView) rootView.findViewById(R.id.tv_buy_title);
            this.et_buy_num = (EditText) rootView.findViewById(R.id.et_buy_num);
            this.tv_buy_num_type = (TextView) rootView.findViewById(R.id.tv_buy_num_type);
            this.et_buy_money = (EditText) rootView.findViewById(R.id.et_buy_money);
            this.tv_buy_money_type = (TextView) rootView.findViewById(R.id.tv_buy_money_type);
            this.tv_buy_price = (TextView) rootView.findViewById(R.id.tv_buy_price);
            this.lin_buy_price = (LinearLayout) rootView.findViewById(R.id.lin_buy_price);
            this.tv_buy_enquiries = (TextView) rootView.findViewById(R.id.tv_buy_enquiries);
            this.tv_buy_commit = (TextView) rootView.findViewById(R.id.tv_buy_commit);
            this.tv_sell_title = (TextView) rootView.findViewById(R.id.tv_sell_title);
            this.tv_sell_subtitle = (TextView) rootView.findViewById(R.id.tv_sell_subtitle);
            this.et_sell_num = (EditText) rootView.findViewById(R.id.et_sell_num);
            this.tv_sell_num_type = (TextView) rootView.findViewById(R.id.tv_sell_num_type);
            this.et_sell_money = (EditText) rootView.findViewById(R.id.et_sell_money);
            this.tv_sell_money_type = (TextView) rootView.findViewById(R.id.tv_sell_money_type);
            this.tv_sell_price = (TextView) rootView.findViewById(R.id.tv_sell_price);
            this.lin_sell_price = (LinearLayout) rootView.findViewById(R.id.lin_sell_price);
            this.tv_sell_enquiries = (TextView) rootView.findViewById(R.id.tv_sell_enquiries);
            this.tv_sell_commit = (TextView) rootView.findViewById(R.id.tv_sell_commit);
            this.tv_remark = (TextView) rootView.findViewById(R.id.tv_remark);
            this.tv_num_title = (TextView) rootView.findViewById(R.id.tv_num_title);
            this.rc_sell = (RecyclerView) rootView.findViewById(R.id.rc_sell);
            this.rc_buy = (RecyclerView) rootView.findViewById(R.id.rc_buy);
            this.swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        }

    }
}
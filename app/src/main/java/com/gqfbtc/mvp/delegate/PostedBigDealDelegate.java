package com.gqfbtc.mvp.delegate;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.fivefivelike.mybaselibrary.view.IconFontTextview;
import com.gqfbtc.R;
import com.gqfbtc.dialog.TimeChooseDialog;

import java.math.BigDecimal;

public class PostedBigDealDelegate extends BaseDelegate {
    public ViewHolder viewHolder;
    public String chooseday1 = "每日";
    public String choosetime1 = "10:00";
    public String chooseday2 = "每日";
    public String choosetime2 = "23:00";

    public boolean isBuy = false;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_posted_big_deal;
    }

    TextWatcher et_unit_price_linsener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            judgmentCNY(editable.toString());
        }
    };

    private void judgmentCNY(String input) {
        int indexOf = input.indexOf(".");
        int length = input.length() - 1 - 2;
        if (indexOf != -1 && indexOf < length) {
            BigDecimal cnyBigDecimal = new BigDecimal(input);
            viewHolder.et_unit_price.removeTextChangedListener(et_unit_price_linsener);
            viewHolder.et_unit_price.setText(cnyBigDecimal.setScale(2, BigDecimal.ROUND_DOWN).toString());
            viewHolder.et_unit_price.addTextChangedListener(et_unit_price_linsener);
        }
    }

    public void initBuyView() {
        viewHolder.tv_coin_type.setText("求购币种");
        viewHolder.tv_unit_price.setText("求购单价");
        viewHolder.tv_amount.setText("买\u0020 入\u0020 量");
        viewHolder.tv_starting_buy.setText("最低买入");
        viewHolder.lin_sell.setVisibility(View.GONE);
        viewHolder.tv_commit.setText("发布广告");
        viewHolder.et_amount.setHint("预计购买数量");
        isBuy = true;
        initDefault();
    }

    private void initDefault() {
        viewHolder.et_unit_price.addTextChangedListener(et_unit_price_linsener);
        viewHolder.tv_time.setOnClickListener(timeChoose);
        viewHolder.tv_time.setText(chooseday1 + choosetime1 + "-" + chooseday2 + choosetime2);
    }

    View.OnClickListener timeChoose = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            initTimeDialog();
        }
    };

    private void initTimeDialog() {
        new TimeChooseDialog(getActivity(), new TimeChooseDialog.OnTimeChooseLinsener() {
            @Override
            public void setOnTimeChooseListener(String day1, String time1, String day2, String time2) {
                chooseday1 = day1;
                chooseday2 = day2;
                choosetime1 = time1;
                choosetime2 = time2;
                viewHolder.tv_time.setText(day1 + time1 + "-" + day2 + time2);
            }
        }).setDefaultTime(chooseday1, choosetime1, chooseday2, choosetime2).show();
    }


    public void initSellView() {
        viewHolder.tv_coin_type.setText("出售币种");
        viewHolder.tv_unit_price.setText("出售单价");
        viewHolder.tv_amount.setText("卖\u0020 出\u0020 量");
        viewHolder.tv_starting_buy.setText("最低卖出");
        viewHolder.lin_sell.setVisibility(View.VISIBLE);
        viewHolder.tv_commit.setText("发布广告");
        viewHolder.et_amount.setHint("预计出售数量");
        isBuy = false;
        initDefault();
    }

    public static class ViewHolder {
        public View rootView;
        public TextView tv_coin_type;
        public IconFontTextview tv_coin_type_helper;
        public TextView tv_unit_price;
        public EditText et_unit_price;
        public IconFontTextview tv_unit_price_helper;
        public TextView tv_amount;
        public EditText et_amount;
        public IconFontTextview tv_buy_helper;
        public TextView tv_starting_buy;
        public EditText et_starting_buy;
        public IconFontTextview tv_starting_buy_helper;
        public LinearLayout lin_starting_buy;
        public IconFontTextview tv_account_helper;
        public TextView tv_administer_address;
        public RecyclerView rv_address;
        public LinearLayout lin_sell;
        public TextView tv_time;
        public IconFontTextview tv_time_helper;
        public LinearLayout lin_time;
        public EditText et_remark;
        public TextView tv_commit;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_coin_type = (TextView) rootView.findViewById(R.id.tv_coin_type);
            this.tv_coin_type_helper = (IconFontTextview) rootView.findViewById(R.id.tv_coin_type_helper);
            this.tv_unit_price = (TextView) rootView.findViewById(R.id.tv_unit_price);
            this.et_unit_price = (EditText) rootView.findViewById(R.id.et_unit_price);
            this.tv_unit_price_helper = (IconFontTextview) rootView.findViewById(R.id.tv_unit_price_helper);
            this.tv_amount = (TextView) rootView.findViewById(R.id.tv_amount);
            this.et_amount = (EditText) rootView.findViewById(R.id.et_amount);
            this.tv_buy_helper = (IconFontTextview) rootView.findViewById(R.id.tv_buy_helper);
            this.tv_starting_buy = (TextView) rootView.findViewById(R.id.tv_starting_buy);
            this.et_starting_buy = (EditText) rootView.findViewById(R.id.et_starting_buy);
            this.tv_starting_buy_helper = (IconFontTextview) rootView.findViewById(R.id.tv_starting_buy_helper);
            this.lin_starting_buy = (LinearLayout) rootView.findViewById(R.id.lin_starting_buy);
            this.tv_account_helper = (IconFontTextview) rootView.findViewById(R.id.tv_account_helper);
            this.tv_administer_address = (TextView) rootView.findViewById(R.id.tv_administer_address);
            this.rv_address = (RecyclerView) rootView.findViewById(R.id.rv_address);
            this.lin_sell = (LinearLayout) rootView.findViewById(R.id.lin_sell);
            this.tv_time = (TextView) rootView.findViewById(R.id.tv_time);
            this.tv_time_helper = (IconFontTextview) rootView.findViewById(R.id.tv_time_helper);
            this.lin_time = (LinearLayout) rootView.findViewById(R.id.lin_time);
            this.et_remark = (EditText) rootView.findViewById(R.id.et_remark);
            this.tv_commit = (TextView) rootView.findViewById(R.id.tv_commit);
        }

    }
}
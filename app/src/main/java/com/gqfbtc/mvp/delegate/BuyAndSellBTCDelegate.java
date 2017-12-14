package com.gqfbtc.mvp.delegate;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.gqfbtc.R;
import com.gqfbtc.Utils.glide.GlideUtils;
import com.gqfbtc.adapter.SelectGuarantorAdapter;
import com.gqfbtc.entity.bean.AdvertisingDetails;
import com.kyleduo.switchbutton.SwitchButton;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.math.BigDecimal;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 郭青枫 on 2017/11/28 0028.
 */

public class BuyAndSellBTCDelegate extends BaseActivityPullDelegate {

    private RecyclerView rv_guarantor;
    public SelectGuarantorAdapter selectGuarantorAdapter;
    public SwitchButton checkbox;
    private LinearLayout lin_guarantor;
    private CircleImageView ic_pic;
    private TextView tv_user_name;
    private ImageView iv_jisu;
    private ImageView iv_dahu;
    private ImageView iv_youzhi;
    private TextView tv_score;
    private TextView tv_transact_num;
    private TextView tv_tv_transact_money;
    private TextView tv_unit_price;
    private TextView tv_limit;
    private TextView tv_msg;
    public EditText et_forecast_cny;
    public EditText et_forecast_btc;
    private LinearLayout lin_buy;
    private TextView tv_no_message;
    private TextView tv_toast;
    private LinearLayout lin_guarantee;
    AdvertisingDetails advertisingDetails;

    public View initHeader(AdvertisingDetails a) {
        advertisingDetails = a;
        // View view = viewHolder.rootView.getContext().getLayoutInflater().inflate(R.layout.layout_buy_and_sell, null);
        View view = ((LayoutInflater) viewHolder.rootView.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_buy_and_sell, null, false);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        this.ic_pic = (CircleImageView) view.findViewById(R.id.ic_pic);
        this.tv_user_name = (TextView) view.findViewById(R.id.tv_user_name);
        this.iv_jisu = (ImageView) view.findViewById(R.id.iv_jisu);
        this.iv_dahu = (ImageView) view.findViewById(R.id.iv_dahu);
        this.iv_youzhi = (ImageView) view.findViewById(R.id.iv_youzhi);
        this.tv_score = (TextView) view.findViewById(R.id.tv_score);
        this.tv_transact_num = (TextView) view.findViewById(R.id.tv_transact_num);
        this.tv_tv_transact_money = (TextView) view.findViewById(R.id.tv_tv_transact_money);
        this.tv_unit_price = (TextView) view.findViewById(R.id.tv_unit_price);
        this.tv_limit = (TextView) view.findViewById(R.id.tv_limit);
        this.tv_msg = (TextView) view.findViewById(R.id.tv_msg);
        this.et_forecast_cny = (EditText) view.findViewById(R.id.et_forecast_cny);
        this.et_forecast_btc = (EditText) view.findViewById(R.id.et_forecast_btc);
        this.checkbox = (SwitchButton) view.findViewById(R.id.checkbox);
        this.rv_guarantor = (RecyclerView) view.findViewById(R.id.rv_guarantor);
        this.lin_guarantor = (LinearLayout) view.findViewById(R.id.lin_guarantor);
        this.lin_buy = (LinearLayout) view.findViewById(R.id.lin_buy);
        this.tv_no_message = (TextView) view.findViewById(R.id.tv_no_message);
        this.lin_guarantee = (LinearLayout) view.findViewById(R.id.lin_guarantee);
        this.tv_toast = (TextView) view.findViewById(R.id.tv_toast);

        GlideUtils.loadImage(a.getAvatar(), ic_pic);
        tv_user_name.setText(a.getNickName());
        tv_score.setText(a.getGrade());
        tv_transact_num.setText(a.getDealCountStr());
        tv_tv_transact_money.setText("已在平台交易过\n" + a.getDealQuantityStr());
        tv_unit_price.setText(a.getPriceStr());
        tv_limit.setText(TextUtils.isEmpty(a.getDealRange()) ? "无" : a.getDealRange());
        tv_msg.setText(a.getRemark());

        iv_jisu.setVisibility(a.isTagsExpress() ? View.VISIBLE : View.GONE);
        iv_dahu.setVisibility(a.isTagsKa() ? View.VISIBLE : View.GONE);
        iv_youzhi.setVisibility(View.GONE);

        et_forecast_cny.addTextChangedListener(amountTextWatcher);
        et_forecast_btc.addTextChangedListener(btcTextWatcher);

        if (a.isSale()) {
            et_forecast_cny.setHint("预计购买额度");
            et_forecast_btc.setHint("预计购买数量");
            tv_toast.setText("* 该广告当前可购买额度为" + a.getDealRange());
        } else {
            et_forecast_cny.setHint("预计出售额度");
            et_forecast_btc.setHint("预计出售数量");
            tv_toast.setText("* 该广告当前可出售额度为" + a.getDealRange());
        }

        if (a.getIntermediaryList() != null) {
            selectGuarantorAdapter = new SelectGuarantorAdapter(viewHolder.rootView.getContext(), a.getIntermediaryList());
            rv_guarantor.setLayoutManager(new LinearLayoutManager(viewHolder.rootView.getContext()) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });
            rv_guarantor.setAdapter(selectGuarantorAdapter);
            selectGuarantorAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                    selectGuarantorAdapter.setSelectPositon(selectGuarantorAdapter.getSelectPositon() == position ? -1 : position);
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                    return false;
                }
            });
        }

        lin_guarantor.setVisibility(View.GONE);
        checkbox.setChecked(true);

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                lin_guarantor.setVisibility(isChecked ? View.GONE : View.VISIBLE);
            }
        });
        return view;
    }

    TextWatcher amountTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            amountMoney(editable.toString());
        }
    };
    TextWatcher btcTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            amountBtc(editable.toString());
        }
    };

    private void amountMoney(String money) {
        if (!TextUtils.isEmpty(money)) {
            BigDecimal btcMoney = new BigDecimal(money);
            btcMoney = btcMoney.divide(advertisingDetails.getPrice(), 8, BigDecimal.ROUND_DOWN);
            String end = btcMoney.toString();
            if (!money.equals(end)) {
                et_forecast_btc.removeTextChangedListener(btcTextWatcher);
                et_forecast_btc.setText(end);
                et_forecast_btc.addTextChangedListener(btcTextWatcher);
            } else {
                et_forecast_btc.removeTextChangedListener(btcTextWatcher);
                et_forecast_btc.setText("");
                et_forecast_btc.addTextChangedListener(btcTextWatcher);
            }
        } else {
            et_forecast_btc.removeTextChangedListener(btcTextWatcher);
            et_forecast_btc.setText("");
            et_forecast_btc.addTextChangedListener(btcTextWatcher);
        }
    }

    private void amountBtc(String btc) {
        if (!TextUtils.isEmpty(btc)) {
            BigDecimal btcDouble = new BigDecimal(btc);
            btcDouble = btcDouble.multiply(advertisingDetails.getPrice());
            String end = "" + btcDouble.intValue();
            if (!btc.equals(end)) {
                et_forecast_cny.removeTextChangedListener(amountTextWatcher);
                et_forecast_cny.setText(end);
                et_forecast_cny.addTextChangedListener(amountTextWatcher);
            } else {
                et_forecast_cny.removeTextChangedListener(amountTextWatcher);
                et_forecast_cny.setText("");
                et_forecast_cny.addTextChangedListener(amountTextWatcher);
            }
        } else {
            et_forecast_cny.removeTextChangedListener(amountTextWatcher);
            et_forecast_cny.setText("");
            et_forecast_cny.addTextChangedListener(amountTextWatcher);
        }
    }

    public void isHaveMessage(boolean isHaveMessage) {
        tv_no_message.setVisibility(isHaveMessage ? View.GONE : View.VISIBLE);
        tv_no_message.setText(CommonUtils.getString(R.string.str_nodata_message));
    }


}

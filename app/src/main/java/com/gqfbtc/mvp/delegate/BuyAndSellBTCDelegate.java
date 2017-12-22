package com.gqfbtc.mvp.delegate;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.gqfbtc.Utils.glide.GlideUtils;
import com.gqfbtc.entity.bean.AdvertisingDetails;

import java.math.BigDecimal;

/**
 * Created by 郭青枫 on 2017/11/28 0028.
 */

public class BuyAndSellBTCDelegate extends AdvertisingDelegate {
    AdvertisingDetails advertisingDetails;

    public View initHeader(AdvertisingDetails a) {
        advertisingDetails = a;
        // View view = viewHolder.rootView.getContext().getLayoutInflater().inflate(R.layout.layout_buy_and_sell, null);
        View view = initHeader();

        //页面结构设置
        lin_big_deals.setVisibility(View.GONE);
        lin_bigdeals_input.setVisibility(View.GONE);
        lin_time.setVisibility(View.GONE);
        lin_probability.setVisibility(View.GONE);
        tv_intervening_helper.setVisibility(View.GONE);
        tv_bigdeals_cny_helper.setVisibility(View.GONE);

        //信息绑定
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

        initIntervening(a);

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


}

package com.gqfbtc.mvp.delegate;


import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.fivefivelike.mybaselibrary.view.IconFontTextview;
import com.gqfbtc.R;
import com.gqfbtc.Utils.UiHeplUtils;
import com.gqfbtc.dialog.HelperTostDialog;
import com.gqfbtc.dialog.TimeChooseDialog;
import com.gqfbtc.entity.bean.BeforeSaveAd;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AdvertisingBuyAndSellDelegate extends BaseDelegate {
    public ViewHolder viewHolder;


    BigDecimal premium = new BigDecimal("4.52");
    String changeNum = "0.01";
    public String chooseday1 = "每日";
    public String choosetime1 = "10:00";
    public String chooseday2 = "每日";
    public String choosetime2 = "23:00";
    boolean isBuy = true;
    boolean isSafe = true;
    String unit_price = "";//用户输入的单价
    BeforeSaveAd beforeSaveAd;

    public boolean isBuy() {
        return isBuy;
    }

    public boolean isSafe() {
        return isSafe;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
        viewHolder.tv_toggle.setChecked(false);
        viewHolder.et_unit_price.setEnabled(false);
        viewHolder.tv_toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                viewHolder.lin_premium.setVisibility(isChecked ? View.GONE : View.VISIBLE);
                viewHolder.lin_upper_limit.setVisibility(isChecked ? View.GONE : View.VISIBLE);
                viewHolder.tv_type.setText(
                        isChecked ? "固定报价" : "浮动报价"
                );
                viewHolder.tv_toggle.setText(
                        isChecked ? "切换至浮动报价模式" : "切换至固定报价模式"
                );
                if (!isChecked) {
                    unit_price = viewHolder.et_unit_price.getText().toString();
                }
                viewHolder.et_unit_price.setText(!isChecked ? (premiumBig == null ? getFloatMarketPrice() : premiumBig.toString()) : unit_price);
                viewHolder.et_unit_price.setEnabled(isChecked);
            }
        });
        viewHolder.et_premium.setText(premium + "");
        viewHolder.tv_time.setText(chooseday1 + choosetime1 + "-" + chooseday2 + choosetime2);

        viewHolder.tv_subtract.setOnClickListener(onClickListener);
        viewHolder.tv_add.setOnClickListener(onClickListener);
        viewHolder.tv_time.setOnClickListener(onClickListener);
        viewHolder.tv_addressr.setOnClickListener(onClickListener);

        viewHolder.et_amount.addTextChangedListener(amountTextWatcher);//金额
        viewHolder.et_btc.addTextChangedListener(btcTextWatcher);//btc数量
        viewHolder.et_premium.addTextChangedListener(premiumTextWatcher);//浮动比例
        viewHolder.et_unit_price.addTextChangedListener(unit_priceTextWatcher);//单价

        viewHolder.tv_quote_helper.setOnClickListener(onHelperClick);
        viewHolder.tv_premium_helper.setOnClickListener(onHelperClick);
        viewHolder.tv_currency_helper.setOnClickListener(onHelperClick);
        viewHolder.tv_upper_limit_helper.setOnClickListener(onHelperClick);
        viewHolder.tv_buy_helper.setOnClickListener(onHelperClick);
        viewHolder.tv_starting_buy_helper.setOnClickListener(onHelperClick);
        viewHolder.tv_account_helper.setOnClickListener(onHelperClick);
        viewHolder.tv_address_helper.setOnClickListener(onHelperClick);
        viewHolder.tv_time_helper.setOnClickListener(onHelperClick);

    }

    //价格上线 选填 最小0 最大。。。
    //溢价比例 小数转换百分比 买入量和单价进行换算
    //单价 整数 不四舍五入
    BigDecimal premiumBig;
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
    TextWatcher premiumTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (!UiHeplUtils.isNumeric(editable.toString())) {
                return;
            }
            Double premiumDouble = Double.parseDouble(editable.toString());
            if (premiumDouble > 100) {
                viewHolder.et_premium.removeTextChangedListener(premiumTextWatcher);
                viewHolder.et_premium.setText("100.00");
                viewHolder.et_premium.addTextChangedListener(premiumTextWatcher);
                return;
            }
            if (premiumDouble < -50.00) {
                viewHolder.et_premium.removeTextChangedListener(premiumTextWatcher);
                viewHolder.et_premium.setText("-50.00");
                viewHolder.et_premium.addTextChangedListener(premiumTextWatcher);
                return;
            }
            premiumBig = new BigDecimal(editable.toString());
            premiumBig = premiumBig.divide(new BigDecimal(100));
            premiumBig = new BigDecimal((new BigDecimal(1).add(premiumBig)).multiply(new BigDecimal(beforeSaveAd.getMarketPrice())).intValue());//
            viewHolder.et_unit_price.removeTextChangedListener(unit_priceTextWatcher);
            viewHolder.et_unit_price.setText(premiumBig.toString());
            viewHolder.et_unit_price.addTextChangedListener(unit_priceTextWatcher);

        }
    };
    TextWatcher unit_priceTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (TextUtils.isEmpty(editable.toString())) {
                viewHolder.et_amount.getText().clear();
                viewHolder.et_btc.getText().clear();
            } else {
                if (!TextUtils.isEmpty(viewHolder.et_btc.getText().toString()) && !TextUtils.isEmpty(viewHolder.et_amount.getText().toString())) {
                    String num = viewHolder.et_btc.getText().toString();
                    viewHolder.et_btc.setText(num);
                } else {
                    viewHolder.et_amount.getText().clear();
                    viewHolder.et_btc.getText().clear();
                }
                try {
                    double unit_priceDouble = Double.parseDouble(editable.toString());
                    int unit_priceInt = (int) unit_priceDouble;
                    if (unit_priceDouble > (Double.parseDouble(unit_priceInt + ""))) {
                        viewHolder.et_unit_price.removeTextChangedListener(unit_priceTextWatcher);
                        viewHolder.et_unit_price.setText(unit_priceInt + "");
                        viewHolder.et_unit_price.addTextChangedListener(unit_priceTextWatcher);
                    }
                } catch (Exception e) {

                }
            }
        }
    };

    //金额换算btc数量
    private void amountMoney(String money) {
        if (!isHaveUpperLimit()) {
            return;
        }
        if (!TextUtils.isEmpty(money)) {
            BigDecimal btcMoney = new BigDecimal(money);
            BigDecimal price = new BigDecimal(viewHolder.et_unit_price.getText().toString());
            btcMoney = btcMoney.divide(price, 8, BigDecimal.ROUND_DOWN);
            String end = btcMoney.toString();
            if (!money.equals(end)) {
                viewHolder.et_btc.removeTextChangedListener(btcTextWatcher);
                viewHolder.et_btc.setText(end);
                viewHolder.et_btc.addTextChangedListener(btcTextWatcher);
            } else {
                viewHolder.et_btc.removeTextChangedListener(btcTextWatcher);
                viewHolder.et_btc.setText("");
                viewHolder.et_btc.addTextChangedListener(btcTextWatcher);
            }
        } else {
            viewHolder.et_btc.removeTextChangedListener(btcTextWatcher);
            viewHolder.et_btc.setText("");
            viewHolder.et_btc.addTextChangedListener(btcTextWatcher);
        }
    }

    private Boolean isHaveUpperLimit() {
        if (TextUtils.isEmpty(viewHolder.et_unit_price.getText().toString())) {
            if (!TextUtils.isEmpty(viewHolder.et_amount.getText().toString())) {
                viewHolder.et_amount.getText().clear();
            }
            if (!TextUtils.isEmpty(viewHolder.et_btc.getText().toString())) {
                viewHolder.et_btc.getText().clear();
            }
            ToastUtil.show("请输入单价，我们会根据单价进行换算");
            return false;
        }
        return true;
    }

    //btc数量换算金额
    private void amountBtc(String btc) {
        if (!isHaveUpperLimit()) {
            return;
        }
        if (!TextUtils.isEmpty(btc)) {
            BigDecimal btcDouble = new BigDecimal(btc);
            BigDecimal price = new BigDecimal(viewHolder.et_unit_price.getText().toString());
            btcDouble = btcDouble.multiply(price).setScale(8, BigDecimal.ROUND_DOWN);
            String end = btcDouble.toString();
            if (!btc.equals(end)) {
                viewHolder.et_amount.removeTextChangedListener(amountTextWatcher);
                viewHolder.et_amount.setText(end);
                viewHolder.et_amount.addTextChangedListener(amountTextWatcher);
            } else {
                viewHolder.et_amount.removeTextChangedListener(amountTextWatcher);
                viewHolder.et_amount.setText("");
                viewHolder.et_amount.addTextChangedListener(amountTextWatcher);
            }
        } else {
            viewHolder.et_amount.removeTextChangedListener(amountTextWatcher);
            viewHolder.et_amount.setText("");
            viewHolder.et_amount.addTextChangedListener(amountTextWatcher);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_advertising_buy_and_sell;
    }

    public void initCommonSell() {
        isSafe = false;
        initSell();
        initAllView();
    }

    public void initSafeSell() {
        initSell();
        initAllView();
    }

    public void initCommonBuy() {
        isSafe = false;
        initAllView();
    }

    public void initSafeBuy() {
        initAllView();
    }

    private void initAllView() {
        viewHolder.lin_starting_buy.setVisibility(!isBuy && !isSafe ? View.GONE : View.VISIBLE);
        viewHolder.lin_sell.setVisibility(isBuy ? View.GONE : View.VISIBLE);
        viewHolder.lin_address.setVisibility(isBuy && isSafe ? View.VISIBLE : View.GONE);
        viewHolder.lin_time.setVisibility(!isBuy && !isSafe ? View.GONE : View.VISIBLE);
        viewHolder.tv_commit.setText("发布广告");
        viewHolder.tv_floor.setText(isBuy ? "价格上限" : "价格下限");
    }

    private void initSell() {
        isBuy = false;
        viewHolder.tv_amount.setText("卖\u0020 出\u0020 量");
        viewHolder.et_amount.setHint("卖出金额");
        viewHolder.et_btc.setHint("卖出数量");
        viewHolder.tv_starting_buy.setText("单笔最低");
    }

    public void beforeSaveAd(BeforeSaveAd before) {
        beforeSaveAd = before;
        if (TextUtils.isEmpty(beforeSaveAd.getMarketPrice())) {
            //没有浮动报价模式，固定报价
            viewHolder.tv_toggle.setChecked(true);
            viewHolder.et_unit_price.setText("");
        } else {
            viewHolder.et_unit_price.setText(getFloatMarketPrice());
        }
        premium = new BigDecimal(beforeSaveAd.getFloatPercent()).multiply(new BigDecimal("100")); //Double.parseDouble(beforeSaveAd.getFloatPercent()) * 100;
        viewHolder.et_premium.setText(premium.toString() + "");
    }

    private String getFloatMarketPrice() {
        BigDecimal marketPrice = new BigDecimal(beforeSaveAd.getMarketPrice());
        BigDecimal premium = new BigDecimal(beforeSaveAd.getFloatPercent());
        return marketPrice.multiply(premium.add(new BigDecimal("1"))).toString();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tv_add:
                    if (!UiHeplUtils.isNumeric(viewHolder.et_premium.getText().toString())) {
                        return;
                    }
                    premium = (new BigDecimal(viewHolder.et_premium.getText().toString()).add(new BigDecimal(changeNum)).setScale(2, RoundingMode.DOWN));//Double.parseDouble(viewHolder.et_premium.getText().toString());
                    viewHolder.et_premium.setText(premium.toString() + "");
                    break;
                case R.id.tv_subtract:
                    if (!UiHeplUtils.isNumeric(viewHolder.et_premium.getText().toString())) {
                        return;
                    }
                    premium = (new BigDecimal(viewHolder.et_premium.getText().toString()).subtract(new BigDecimal(changeNum)).setScale(2, RoundingMode.DOWN));
                    viewHolder.et_premium.setText(premium.toString() + "");
                    break;
                case R.id.tv_time:
                    initTimeDialog();
                    break;
            }
        }
    };


    View.OnClickListener onHelperClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tv_quote_helper:
                    if (viewHolder.tv_toggle.isChecked()) {
                        if (isBuy) {
                            HelperTostDialog.getInstence().showToastDialog(viewHolder.rootView.getContext(), R.string.str_quote_fixed_buy_helper);
                        } else {
                            HelperTostDialog.getInstence().showToastDialog(viewHolder.rootView.getContext(), R.string.str_quote_fixed_sell_helper);
                        }
                    } else {
                        HelperTostDialog.getInstence().showToastDialog(viewHolder.rootView.getContext(), R.string.str_quote_float_helper);
                    }
                    break;
                case R.id.tv_premium_helper:
                    HelperTostDialog.getInstence().showToastDialog(viewHolder.rootView.getContext(), R.string.str_premium_helper);
                    break;
                case R.id.tv_currency_helper:
                    if (viewHolder.tv_toggle.isChecked()) {
                        HelperTostDialog.getInstence().showToastDialog(viewHolder.rootView.getContext(), R.string.str_currency_float_helper);
                    } else {
                        HelperTostDialog.getInstence().showToastDialog(viewHolder.rootView.getContext(), R.string.str_currency_fixed_helper);
                    }
                    break;
                case R.id.tv_upper_limit_helper:
                    if (isBuy) {
                        HelperTostDialog.getInstence().showToastDialog(viewHolder.rootView.getContext(), R.string.str_upper_limit_buy_helper);
                    } else {
                        HelperTostDialog.getInstence().showToastDialog(viewHolder.rootView.getContext(), R.string.str_upper_limit_sell_helper);

                    }
                    break;
                case R.id.tv_buy_helper:
                    if (isBuy) {
                        HelperTostDialog.getInstence().showToastDialog(viewHolder.rootView.getContext(), R.string.str_buy_buy_helper);
                    } else {
                        HelperTostDialog.getInstence().showToastDialog(viewHolder.rootView.getContext(), R.string.str_buy_sell_helper);
                    }
                    break;
                case R.id.tv_starting_buy_helper:
                    if (isBuy) {
                        HelperTostDialog.getInstence().showToastDialog(viewHolder.rootView.getContext(), R.string.str_starting_buy_buy_helper);
                    } else {
                        HelperTostDialog.getInstence().showToastDialog(viewHolder.rootView.getContext(), R.string.str_starting_buy_sell_helper);
                    }
                    break;
                case R.id.tv_account_helper:
                    HelperTostDialog.getInstence().showToastDialog(viewHolder.rootView.getContext(), R.string.str_account_helper);
                    break;
                case R.id.tv_address_helper:
                    HelperTostDialog.getInstence().showToastDialog(viewHolder.rootView.getContext(), R.string.str_address_helper);
                    break;
                case R.id.tv_time_helper:
                    HelperTostDialog.getInstence().showToastDialog(viewHolder.rootView.getContext(), R.string.str_time_helper);
                    break;
            }
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

    public static class ViewHolder {
        public View rootView;
        public TextView tv_type;
        public IconFontTextview tv_quote_helper;
        public CheckBox tv_toggle;
        public ImageView tv_subtract;
        public EditText et_premium;
        public ImageView tv_add;
        public IconFontTextview tv_premium_helper;
        public LinearLayout lin_premium;
        public EditText et_unit_price;
        public TextView tv_currency;
        public IconFontTextview tv_currency_helper;
        public EditText et_upper_limit_price;
        public TextView tv_upper_limit_currency;
        public IconFontTextview tv_upper_limit_helper;
        public LinearLayout lin_upper_limit;
        public TextView tv_amount;
        public EditText et_amount;
        public EditText et_btc;
        public IconFontTextview tv_buy_helper;
        public TextView tv_starting_buy;
        public EditText et_starting_buy;
        public IconFontTextview tv_starting_buy_helper;
        public LinearLayout lin_starting_buy;
        public IconFontTextview tv_account_helper;
        public RecyclerView rv_address;
        public LinearLayout lin_sell;
        public TextView tv_addressr;
        public IconFontTextview tv_address_helper;
        public LinearLayout lin_address;
        public TextView tv_time;
        public IconFontTextview tv_time_helper;
        public LinearLayout lin_time;
        public LinearLayout lin_buy;
        public EditText et_remark;
        public TextView tv_commit;
        public TextView tv_administer_address;
        public TextView tv_floor;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_type = (TextView) rootView.findViewById(R.id.tv_type);
            this.tv_quote_helper = (IconFontTextview) rootView.findViewById(R.id.tv_quote_helper);
            this.tv_toggle = (CheckBox) rootView.findViewById(R.id.tv_toggle);
            this.tv_subtract = (ImageView) rootView.findViewById(R.id.tv_subtract);
            this.et_premium = (EditText) rootView.findViewById(R.id.et_premium);
            this.tv_add = (ImageView) rootView.findViewById(R.id.tv_add);
            this.tv_premium_helper = (IconFontTextview) rootView.findViewById(R.id.tv_premium_helper);
            this.lin_premium = (LinearLayout) rootView.findViewById(R.id.lin_premium);
            this.et_unit_price = (EditText) rootView.findViewById(R.id.et_unit_price);
            this.tv_currency = (TextView) rootView.findViewById(R.id.tv_currency);
            this.tv_currency_helper = (IconFontTextview) rootView.findViewById(R.id.tv_currency_helper);
            this.et_upper_limit_price = (EditText) rootView.findViewById(R.id.et_upper_limit_price);
            this.tv_upper_limit_currency = (TextView) rootView.findViewById(R.id.tv_upper_limit_currency);
            this.tv_upper_limit_helper = (IconFontTextview) rootView.findViewById(R.id.tv_upper_limit_helper);
            this.lin_upper_limit = (LinearLayout) rootView.findViewById(R.id.lin_upper_limit);
            this.tv_amount = (TextView) rootView.findViewById(R.id.tv_amount);
            this.et_amount = (EditText) rootView.findViewById(R.id.et_amount);
            this.et_btc = (EditText) rootView.findViewById(R.id.et_btc);
            this.tv_buy_helper = (IconFontTextview) rootView.findViewById(R.id.tv_buy_helper);
            this.tv_starting_buy = (TextView) rootView.findViewById(R.id.tv_starting_buy);
            this.et_starting_buy = (EditText) rootView.findViewById(R.id.et_starting_buy);
            this.tv_starting_buy_helper = (IconFontTextview) rootView.findViewById(R.id.tv_starting_buy_helper);
            this.lin_starting_buy = (LinearLayout) rootView.findViewById(R.id.lin_starting_buy);
            this.tv_account_helper = (IconFontTextview) rootView.findViewById(R.id.tv_account_helper);
            this.rv_address = (RecyclerView) rootView.findViewById(R.id.rv_address);
            this.lin_sell = (LinearLayout) rootView.findViewById(R.id.lin_sell);
            this.tv_addressr = (TextView) rootView.findViewById(R.id.tv_addressr);
            this.tv_address_helper = (IconFontTextview) rootView.findViewById(R.id.tv_address_helper);
            this.lin_address = (LinearLayout) rootView.findViewById(R.id.lin_address);
            this.tv_time = (TextView) rootView.findViewById(R.id.tv_time);
            this.tv_time_helper = (IconFontTextview) rootView.findViewById(R.id.tv_time_helper);
            this.lin_time = (LinearLayout) rootView.findViewById(R.id.lin_time);
            this.lin_buy = (LinearLayout) rootView.findViewById(R.id.lin_buy);
            this.et_remark = (EditText) rootView.findViewById(R.id.et_remark);
            this.tv_commit = (TextView) rootView.findViewById(R.id.tv_commit);
            this.tv_administer_address = (TextView) rootView.findViewById(R.id.tv_administer_address);
            this.tv_floor = (TextView) rootView.findViewById(R.id.tv_floor);
        }

    }
}

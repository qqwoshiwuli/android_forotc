package com.gqfbtc.mvp.delegate;


import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.view.DashView;
import com.gqfbtc.R;
import com.gqfbtc.dialog.HelperTostDialog;
import com.gqfbtc.entity.bean.CheckFrozen;
import com.gqfbtc.entity.bean.HandlingCharge;
import com.gqfbtc.mvp.activity.ChooseBuyBtcModeActivity;
import com.gqfbtc.widget.SingleLineZoomTextView;

public class ChooseBuyBtcModeDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
        setCommitEnableView(viewHolder.tv_anquan_commit, viewHolder.tv_putong_commit);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_buy_btc;
    }

    View.OnClickListener helpToast;

    public void initAdvertising(final String type) {
        viewHolder.lin_top_toast.setVisibility(View.GONE);
        viewHolder.lin_hand_anquan_toast.setVisibility(View.VISIBLE);
        viewHolder.lin_hand_putong_toast.setVisibility(View.VISIBLE);
        viewHolder.tv_anquan_more.setVisibility(View.VISIBLE);
        viewHolder.tv_putong_more.setVisibility(View.VISIBLE);
        helpToast = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.lin_hand_anquan_toast) {
                    HelperTostDialog.getInstence().showToastDialog(viewHolder.rootView.getContext(), R.string.str_hand_money);
                } else if (view.getId() == R.id.lin_hand_putong_toast) {
                    HelperTostDialog.getInstence().showToastDialog(viewHolder.rootView.getContext(), R.string.str_hand_money);
                } else if (view.getId() == R.id.tv_anquan_more) {
                    if (ChooseBuyBtcModeActivity.type_buy.equals(type)) {
                        HelperTostDialog.getInstence().showToastDialog(viewHolder.rootView.getContext(), R.string.str_advertising_buy_safe_help);
                    } else {
                        HelperTostDialog.getInstence().showToastDialog(viewHolder.rootView.getContext(), R.string.str_advertising_sale_safe_help);
                    }
                } else if (view.getId() == R.id.tv_putong_more) {
                    if (ChooseBuyBtcModeActivity.type_buy.equals(type)) {
                        HelperTostDialog.getInstence().showToastDialog(viewHolder.rootView.getContext(), R.string.str_advertising_buy_help);
                    } else {
                        HelperTostDialog.getInstence().showToastDialog(viewHolder.rootView.getContext(), R.string.str_advertising_sale_help);
                    }
                }
            }
        };
        viewHolder.lin_hand_anquan_toast.setOnClickListener(helpToast);
        viewHolder.lin_hand_putong_toast.setOnClickListener(helpToast);
        viewHolder.tv_anquan_more.setOnClickListener(helpToast);
        viewHolder.tv_putong_more.setOnClickListener(helpToast);
        if (ChooseBuyBtcModeActivity.type_buy.equals(type)) {
            viewHolder.tv_putong_toast.setText(CommonUtils.getString(R.string.str_advertising_buy));
            viewHolder.tv_anquan_toast.setText(CommonUtils.getString(R.string.str_advertising_buy_safa));
            viewHolder.tv_tutong_title.setText(CommonUtils.getString(R.string.str_advertising_buy_title));
            viewHolder.tv_anquan_title.setText(CommonUtils.getString(R.string.str_advertising_buy_safa_title));
        } else {
            viewHolder.tv_putong_toast.setText(CommonUtils.getString(R.string.str_advertising_sale));
            viewHolder.tv_anquan_toast.setText(CommonUtils.getString(R.string.str_advertising_sale_safa));
            viewHolder.tv_tutong_title.setText(CommonUtils.getString(R.string.str_advertising_sale_title));
            viewHolder.tv_anquan_title.setText(CommonUtils.getString(R.string.str_advertising_sale_safa_title));
        }

    }

    public void initTransact(HandlingCharge handlingCharge) {
        viewHolder.lin_top_toast.setVisibility(View.VISIBLE);
        viewHolder.tv_total_price.setText(handlingCharge.getAmountVo());
        viewHolder.tv_unit_price.setText(handlingCharge.getPriceVo());
        viewHolder.tv_toast.setText("本次交易需支付手续费：" + handlingCharge.getPountAgeRateVo() + "%");
        viewHolder.tv_handling_charge.setText("约" + handlingCharge.getUserPoundageVo());
    }

    public void initToast(CheckFrozen checkFrozen) {
        viewHolder.tv_putong_toast.setText(Html.fromHtml(checkFrozen.getNormalModel()));
        viewHolder.tv_anquan_toast.setText(Html.fromHtml(checkFrozen.getSafeModel()));
    }

    public static class ViewHolder {
        public View rootView;
        public SingleLineZoomTextView tv_total_price;
        public SingleLineZoomTextView tv_unit_price;
        public TextView tv_toast;
        public TextView tv_handling_charge;
        public TextView tv_time;
        public LinearLayout lin_top_toast;
        public TextView tv_tutong_title;
        public TextView tv_anquan_title;
        public TextView tv_putong_commit;
        public TextView tv_anquan_commit;
        public TextView tv_hand_putong_toast;
        public LinearLayout lin_hand_putong_toast;
        public TextView tv_hand_anquan_toast;
        public LinearLayout lin_hand_anquan_toast;
        public TextView tv_putong_toast;
        public TextView tv_anquan_toast;
        public TextView tv_putong_more;
        public TextView tv_anquan_more;
        public LinearLayout lin_mode;
        public DashView view_line;
        public TextView tv_warning;
        public LinearLayout lin_warning;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_total_price = (SingleLineZoomTextView) rootView.findViewById(R.id.tv_total_price);
            this.tv_unit_price = (SingleLineZoomTextView) rootView.findViewById(R.id.tv_unit_price);
            this.tv_toast = (TextView) rootView.findViewById(R.id.tv_toast);
            this.tv_handling_charge = (TextView) rootView.findViewById(R.id.tv_handling_charge);
            this.tv_time = (TextView) rootView.findViewById(R.id.tv_time);
            this.lin_top_toast = (LinearLayout) rootView.findViewById(R.id.lin_top_toast);
            this.tv_tutong_title = (TextView) rootView.findViewById(R.id.tv_tutong_title);
            this.tv_anquan_title = (TextView) rootView.findViewById(R.id.tv_anquan_title);
            this.tv_putong_commit = (TextView) rootView.findViewById(R.id.tv_putong_commit);
            this.tv_anquan_commit = (TextView) rootView.findViewById(R.id.tv_anquan_commit);
            this.tv_hand_putong_toast = (TextView) rootView.findViewById(R.id.tv_hand_putong_toast);
            this.lin_hand_putong_toast = (LinearLayout) rootView.findViewById(R.id.lin_hand_putong_toast);
            this.tv_hand_anquan_toast = (TextView) rootView.findViewById(R.id.tv_hand_anquan_toast);
            this.lin_hand_anquan_toast = (LinearLayout) rootView.findViewById(R.id.lin_hand_anquan_toast);
            this.tv_putong_toast = (TextView) rootView.findViewById(R.id.tv_putong_toast);
            this.tv_anquan_toast = (TextView) rootView.findViewById(R.id.tv_anquan_toast);
            this.tv_putong_more = (TextView) rootView.findViewById(R.id.tv_putong_more);
            this.tv_anquan_more = (TextView) rootView.findViewById(R.id.tv_anquan_more);
            this.lin_mode = (LinearLayout) rootView.findViewById(R.id.lin_mode);
            this.view_line = (DashView) rootView.findViewById(R.id.view_line);
            this.tv_warning = (TextView) rootView.findViewById(R.id.tv_warning);
            this.lin_warning = (LinearLayout) rootView.findViewById(R.id.lin_warning);
        }

    }
}

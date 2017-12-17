package com.gqfbtc.mvp.delegate;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.view.IconFontTextview;
import com.gqfbtc.R;
import com.gqfbtc.adapter.SelectGuarantorAdapter;
import com.gqfbtc.entity.bean.AdvertisingDetails;
import com.kyleduo.switchbutton.SwitchButton;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 郭青枫 on 2017/12/15 0015.
 */

public class AdvertisingDelegate extends BaseActivityPullDelegate {

    public RecyclerView rv_guarantor;
    public SelectGuarantorAdapter selectGuarantorAdapter;
    public SwitchButton checkbox;
    public LinearLayout lin_guarantor;
    public CircleImageView ic_pic;
    public TextView tv_user_name;
    public ImageView iv_jisu;
    public ImageView iv_dahu;
    public ImageView iv_youzhi;
    public TextView tv_score;
    public TextView tv_transact_num;
    public TextView tv_tv_transact_money;
    public TextView tv_unit_price;
    public TextView tv_limit;
    public TextView tv_msg;
    public EditText et_forecast_cny;
    public EditText et_forecast_btc;
    public LinearLayout lin_buy;
    public TextView tv_no_message;
    public TextView tv_toast;
    public LinearLayout lin_guarantee;
    public LinearLayout lin_default;
    public TextView tv_bigdeals_unit_price;
    public TextView tv_bigdeals_type;
    public TextView tv_bigdeals_num;
    public LinearLayout lin_big_deals;
    public EditText et_bigdeals_cny;
    public IconFontTextview tv_bigdeals_cny_helper;
    public LinearLayout lin_bigdeals_input;
    public LinearLayout lin_default_input;
    public View bottomView;
    public LinearLayout lin_level;
    public LinearLayout lin_num;
    public TextView tv_transact_time;
    public LinearLayout lin_time;
    public TextView tv_transact_probability;
    public LinearLayout lin_probability;
    public TextView tv_bigdeals_unit_price_title;
    public TextView tv_bigdeals_type_title;
    public TextView tv_bigdeals_num_title;
    public TextView tv_intervening_helper;
    public TextView tv_bigdeals_toast;

    public View initHeader() {
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
        this.lin_default = (LinearLayout) view.findViewById(R.id.lin_default);
        this.tv_bigdeals_unit_price = (TextView) view.findViewById(R.id.tv_bigdeals_unit_price);
        this.tv_bigdeals_type = (TextView) view.findViewById(R.id.tv_bigdeals_type);
        this.tv_bigdeals_num = (TextView) view.findViewById(R.id.tv_bigdeals_num);
        this.lin_big_deals = (LinearLayout) view.findViewById(R.id.lin_big_deals);
        this.et_bigdeals_cny = (EditText) view.findViewById(R.id.et_bigdeals_cny);
        this.tv_bigdeals_cny_helper = (IconFontTextview) view.findViewById(R.id.tv_bigdeals_cny_helper);
        this.lin_bigdeals_input = (LinearLayout) view.findViewById(R.id.lin_bigdeals_input);
        this.lin_default_input = (LinearLayout) view.findViewById(R.id.lin_default_input);
        this.lin_level = (LinearLayout) view.findViewById(R.id.lin_level);
        this.lin_num = (LinearLayout) view.findViewById(R.id.lin_num);
        this.tv_transact_time = (TextView) view.findViewById(R.id.tv_transact_time);
        this.lin_time = (LinearLayout) view.findViewById(R.id.lin_time);
        this.tv_transact_probability = (TextView) view.findViewById(R.id.tv_transact_probability);
        this.tv_bigdeals_unit_price_title = (TextView) view.findViewById(R.id.tv_bigdeals_unit_price_title);
        this.tv_bigdeals_type_title = (TextView) view.findViewById(R.id.tv_bigdeals_type_title);
        this.tv_bigdeals_num_title = (TextView) view.findViewById(R.id.tv_bigdeals_num_title);
        this.lin_probability = (LinearLayout) view.findViewById(R.id.lin_probability);
        this.tv_intervening_helper = (TextView) view.findViewById(R.id.tv_intervening_helper);
        this.tv_bigdeals_toast = (TextView) view.findViewById(R.id.tv_bigdeals_toast);


        return view;
    }

    public void initIntervening(AdvertisingDetails a) {
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
    }


    public void initBottomView(View.OnClickListener tv_commit_linsener, View.OnClickListener lin_message_linsener) {
        bottomView = ((LayoutInflater) viewHolder.rootView.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_buy_bottom, null, false);
        bottomView.findViewById(R.id.lin_message).setOnClickListener(lin_message_linsener);
        viewHolder.lin_pull.addView(bottomView);
        ((TextView) bottomView.findViewById(R.id.tv_commit)).setOnClickListener(tv_commit_linsener);
    }

    public void isHaveMessage(boolean isHaveMessage) {
        tv_no_message.setVisibility(isHaveMessage ? View.GONE : View.VISIBLE);
        tv_no_message.setText(CommonUtils.getString(R.string.str_nodata_message));
    }


}

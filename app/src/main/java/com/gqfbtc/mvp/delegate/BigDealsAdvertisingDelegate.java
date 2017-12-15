package com.gqfbtc.mvp.delegate;

import android.text.TextUtils;
import android.view.View;

import com.gqfbtc.Utils.glide.GlideUtils;
import com.gqfbtc.entity.bean.AdvertisingDetails;

/**
 * Created by 郭青枫 on 2017/12/15 0015.
 */

public class BigDealsAdvertisingDelegate extends AdvertisingDelegate {

    public View initHeader(AdvertisingDetails a) {
        View view = initHeader();
        //页面结构设置
        lin_default.setVisibility(View.GONE);
        lin_default_input.setVisibility(View.GONE);
        tv_tv_transact_money.setVisibility(View.GONE);
        lin_level.setVisibility(View.GONE);

        //信息绑定
        GlideUtils.loadImage(a.getAvatar(), ic_pic);
        tv_user_name.setText(a.getNickName());
        tv_transact_num.setText(a.getDealCountStr());
        tv_transact_time.setText(a.getRespondTime());
        tv_transact_probability.setText(a.getRespondChance());

        tv_unit_price.setText(a.getPriceStr());
        tv_bigdeals_num.setText(TextUtils.isEmpty(a.getDealRange()) ? "无" : a.getDealRange());
        tv_bigdeals_type.setText(a.getDealTypeStr());
        tv_msg.setText(a.getRemark());


        if (a.isSale()) {
            et_bigdeals_cny.setHint("预计购买数量");
        } else {
            et_bigdeals_cny.setHint("预计出售数量");
        }

        initIntervening(a);

        return view;
    }

}

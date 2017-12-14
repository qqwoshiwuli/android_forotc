package com.gqfbtc.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gqfbtc.R;
import com.gqfbtc.Utils.glide.GlideUtils;
import com.gqfbtc.entity.bean.Intermediary;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 郭青枫 on 2017/10/25.
 */

public class SelectGuarantorAdapter extends CommonAdapter<Intermediary> {


    private AppCompatImageView iv_pic;
    private CircleImageView ic_header;
    private TextView tv_name;

    int selectPositon = -1;
    private TextView tv_num;
    private TextView tv_guarantee_num;
    private TextView tv_guarantee_btc;
    private ImageView iv_alpay;
    private ImageView iv_bank;

    public int getSelectPositon() {
        return selectPositon;
    }

    public void setSelectPositon(int selectPositon) {
        int oldPosition = this.selectPositon;
        if (oldPosition != -1) {
            this.notifyItemChanged(oldPosition);
        }
        this.selectPositon = selectPositon;
        if (selectPositon != -1) {
            this.notifyItemChanged(selectPositon);
        }
    }

    public SelectGuarantorAdapter(Context context, List<Intermediary> datas) {
        super(context, R.layout.adapter_select_guarantor, datas);
    }

    @Override
    protected void convert(ViewHolder holder, Intermediary s, final int position) {
        iv_pic = holder.getView(R.id.iv_pic);
        ic_header = holder.getView(R.id.ic_header);
        tv_name = holder.getView(R.id.tv_name);
        tv_num = holder.getView(R.id.tv_num);
        iv_alpay = holder.getView(R.id.iv_alpay);
        iv_bank = holder.getView(R.id.iv_bank);
        tv_guarantee_num = holder.getView(R.id.tv_guarantee_num);
        tv_guarantee_btc = holder.getView(R.id.tv_guarantee_btc);
        iv_pic.setBackgroundResource(selectPositon == position ? R.drawable.ic_choose_on : R.drawable.ic_choose_off);
        GlideUtils.loadImage(s.getAvatar(), ic_header);
        tv_name.setText(s.getName());
        iv_alpay.setVisibility(s.isAlipay() ? View.VISIBLE : View.GONE);
        iv_bank.setVisibility(s.isCard() ? View.VISIBLE : View.GONE);
        tv_num.setText(s.getGrade() + "分");
        tv_guarantee_num.setText("已担保" + s.getDealCount() + "次");
        tv_guarantee_btc.setText("担保过" + s.getDealQuantity());
    }

}

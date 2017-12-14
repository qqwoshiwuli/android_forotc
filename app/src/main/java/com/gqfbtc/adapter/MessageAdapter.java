package com.gqfbtc.adapter;

import android.content.Context;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.gqfbtc.R;
import com.gqfbtc.Utils.glide.GlideUtils;
import com.gqfbtc.entity.bean.AdvertisingMessage;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.text.SimpleDateFormat;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 郭青枫 on 2017/10/23.
 */

public class MessageAdapter extends CommonAdapter<AdvertisingMessage> {

    private TextView tv_title;
    private CircleImageView ic_pic;
    private TextView tv_name;
    private TextView tv_time;
    private TextView tv_content;

    public MessageAdapter(Context context, List<AdvertisingMessage> datas) {
        super(context, R.layout.adapter_message, datas);
    }

    @Override
    protected void convert(ViewHolder holder, AdvertisingMessage s, final int position) {
        tv_title = holder.getView(R.id.tv_title);
        ic_pic = holder.getView(R.id.ic_pic);
        tv_name = holder.getView(R.id.tv_name);
        tv_time = holder.getView(R.id.tv_time);
        tv_content = holder.getView(R.id.tv_content);
        holder.setIsRecyclable(false);
        tv_name.setText(s.getNickName());
        GlideUtils.loadImage(s.getAvatar(), ic_pic);
        tv_content.setText(s.getContent());
        tv_time.setText(TimeUtils.millis2String(s.getCreateTime(), new SimpleDateFormat("yyyy-MM-dd")));
    }
}

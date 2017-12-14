package com.gqfbtc.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.view.IconFontTextview;
import com.gqfbtc.R;
import com.gqfbtc.entity.SetMenuEntity;
import com.kyleduo.switchbutton.SwitchButton;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/8/23.
 */

public class SetListAdapter extends CommonAdapter<SetMenuEntity> {


    private View line_view;
    private IconFontTextview tv_pic;
    private TextView title_txt;
    private TextView sutitle_txt;
    private SwitchButton checkbox;
    private CircleImageView user_header_img;
    private TextView content_txt;
    private IconFontTextview tv_right_img;
    private LinearLayout root_view;
    private View view;
    private LinearLayout lin_root;

    public SetListAdapter(Context context, List<SetMenuEntity> setMenuEntities) {
        super(context, R.layout.layout_set_item, setMenuEntities);
    }

    public void setData(List<SetMenuEntity> setMenuEntities) {
        mDatas.clear();
        mDatas.addAll(setMenuEntities);
        //notifyDataSetChanged();
    }

    @Override
    protected void convert(ViewHolder holder, SetMenuEntity setMenuEntity, final int position) {
        line_view = holder.getView(R.id.line_view);
        tv_pic = holder.getView(R.id.tv_pic);
        title_txt = holder.getView(R.id.title_txt);
        sutitle_txt = holder.getView(R.id.sutitle_txt);
        checkbox = holder.getView(R.id.checkbox);
        user_header_img = holder.getView(R.id.user_header_img);
        content_txt = holder.getView(R.id.content_txt);
        tv_right_img = holder.getView(R.id.tv_right_img);
        view = holder.getView(R.id.view);
        lin_root = holder.getView(R.id.lin_root);

        tv_pic.setVisibility(setMenuEntity.getLeftImgId() != 0 ? View.VISIBLE : View.GONE);
        line_view.setVisibility(setMenuEntity.isHaveLine() ? View.VISIBLE : View.GONE);
        view.setVisibility(!setMenuEntity.isHaveLine() ? View.VISIBLE : View.GONE);
        content_txt.setVisibility(setMenuEntity.isHavaContent() ? View.VISIBLE : View.GONE);
        tv_right_img.setVisibility(setMenuEntity.isHaveRightImg() ? View.VISIBLE : View.GONE);
        checkbox.setVisibility(setMenuEntity.isHavaCheck() ? View.VISIBLE : View.GONE);
        user_header_img.setVisibility(setMenuEntity.isHaveImg() ? View.VISIBLE : View.GONE);

        if (setMenuEntity.getLeftImgId() != 0)
            tv_pic.setText(CommonUtils.getString(setMenuEntity.getLeftImgId()));
        if (!TextUtils.isEmpty(setMenuEntity.getTitle()))
            title_txt.setText(setMenuEntity.getTitle());
        if (!TextUtils.isEmpty(setMenuEntity.getContent()))
            content_txt.setText(setMenuEntity.getContent());
        if (!TextUtils.isEmpty(setMenuEntity.getUserImgPath()))
            Glide.with(mContext)
                    .load(setMenuEntity.getUserImgPath())
                    .into(user_header_img);

        if (setMenuEntity.isHavaContent()) {
            if (TextUtils.isEmpty(setMenuEntity.getContent())) {
                content_txt.setText("请完善");
            } else {
                content_txt.setText(setMenuEntity.getContent());
            }
        } else {
            content_txt.setText("");
        }

        if (setMenuEntity.isHavaCheck()) {
            checkbox.setChecked(setMenuEntity.isCheck());
            checkbox.setEnabled(true);
            checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    String s;
                    if (b)
                        s = "选中";
                    else
                        s = "未选";
                    if (checkClick != null) {
                        checkClick.checkBoxClick(position);
                    }
                }
            });
            //            checkbox.setOnClickListener(new View.OnClickListener() {
            //                @Override
            //                public void onClick(View view) {
            //                    if (checkClick != null) {
            //                        checkClick.checkBoxClick(position);
            //                    }
            //                    //((CheckedTextView) view).setChecked(!((CheckedTextView) view).isChecked());
            //                    ((SwitchButton) view).toggle();
            //                    //enableCheck();
            //                }
            //            });
        }


        if (!TextUtils.isEmpty(setMenuEntity.getSutitle())) {
            title_txt.setPadding(0, mContext.getResources().getDimensionPixelSize(R.dimen.trans_10px), 0,
                    mContext.getResources().getDimensionPixelSize(R.dimen.trans_5px)
            );
            sutitle_txt.setVisibility(View.VISIBLE);
            sutitle_txt.setText(setMenuEntity.getSutitle());
        } else {
            sutitle_txt.setVisibility(View.GONE);
            title_txt.setPadding(0, mContext.getResources().getDimensionPixelSize(R.dimen.trans_25px), 0,
                    mContext.getResources().getDimensionPixelSize(R.dimen.trans_25px)
            );
        }

        if (setMenuEntity.getContentColorId() != 0) {
            content_txt.setTextColor(mContext.getResources().getColor(setMenuEntity.getContentColorId()));
        }
        lin_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkClick != null) {
                    checkClick.onClick(position);
                }
            }
        });
    }

    CheckClick checkClick;

    public void setCheckClick(CheckClick checkClick) {
        this.checkClick = checkClick;
    }

    public interface CheckClick {
        public void checkBoxClick(int position);

        public void onClick(int position);
    }


}

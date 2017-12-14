package com.gqfbtc.mvp.delegate;


import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.gqfbtc.R;

public class ChangeUserSetDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_user_set;
    }

    public void initEmail() {
        viewHolder.lin_content2.setVisibility(View.GONE);
        viewHolder.tv_title1.setText("邮箱用于接收最新的交易进展。如需修改，请在下面输入新的邮箱地址。");
        viewHolder.tv_label1.setText("邮箱");
        viewHolder.et_input1.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        viewHolder.et_input1.setHint("请输入邮箱");
    }

    public void initPassword() {
        viewHolder.et_input1.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        viewHolder.et_input2.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        viewHolder.et_input1.setHint("6-20位数字或字母");
        viewHolder.et_input2.setHint("6-20位数字或字母");
    }


    public static class ViewHolder {
        public View rootView;
        public TextView tv_title1;
        public TextView tv_label1;
        public EditText et_input1;
        public LinearLayout lin_content1;
        public TextView tv_title2;
        public TextView tv_label2;
        public EditText et_input2;
        public LinearLayout lin_content2;
        public TextView tv_commit;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_title1 = (TextView) rootView.findViewById(R.id.tv_title1);
            this.tv_label1 = (TextView) rootView.findViewById(R.id.tv_label1);
            this.et_input1 = (EditText) rootView.findViewById(R.id.et_input1);
            this.lin_content1 = (LinearLayout) rootView.findViewById(R.id.lin_content1);
            this.tv_title2 = (TextView) rootView.findViewById(R.id.tv_title2);
            this.tv_label2 = (TextView) rootView.findViewById(R.id.tv_label2);
            this.et_input2 = (EditText) rootView.findViewById(R.id.et_input2);
            this.lin_content2 = (LinearLayout) rootView.findViewById(R.id.lin_content2);
            this.tv_commit = (TextView) rootView.findViewById(R.id.tv_commit);
        }

    }
}

package com.gqfbtc.mvp.delegate;


import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.gqfbtc.R;

public class AddCollectionAddressDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
        setCommitEnableView(viewHolder.tv_commit);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_collection_address;
    }

    public void initIntervening() {
        viewHolder.lin_choosetype.setVisibility(View.VISIBLE);

    }

    public void initUser() {
        viewHolder.lin_choosetype.setVisibility(View.GONE);
    }

    public void initAplay() {
        viewHolder.et_content1.setInputType(InputType.TYPE_CLASS_TEXT);
        viewHolder.et_content2.setInputType(InputType.TYPE_CLASS_TEXT);
        viewHolder.et_content3.setInputType(InputType.TYPE_CLASS_TEXT);
        viewHolder.et_content4.setInputType(InputType.TYPE_CLASS_TEXT);
        viewHolder.lin_content1.setVisibility(View.GONE);
        viewHolder.lin_content2.setVisibility(View.GONE);
        viewHolder.lin_content3.setVisibility(View.VISIBLE);
        viewHolder.lin_content4.setVisibility(View.VISIBLE);
        viewHolder.tv_title1.setText("银行");
        viewHolder.tv_title2.setText("开户支行（选填）");
        viewHolder.tv_title3.setText("开户人");
        viewHolder.tv_title4.setText("支付宝账号");
        viewHolder.et_content1.setHint("例如:中国建设银行");
        viewHolder.et_content2.setHint("开户网点名称");
        viewHolder.et_content3.setHint("开户人姓名");
        viewHolder.et_content4.setHint("请输入正确的支付宝账号");
        viewHolder.et_content1.setText("");
        viewHolder.et_content2.setText("");
        viewHolder.et_content3.setText("");
        viewHolder.et_content4.setText("");

    }


    public void initBank() {
        viewHolder.et_content1.setInputType(InputType.TYPE_CLASS_TEXT);
        viewHolder.et_content2.setInputType(InputType.TYPE_CLASS_TEXT);
        viewHolder.et_content3.setInputType(InputType.TYPE_CLASS_TEXT);
        viewHolder.et_content4.setInputType(InputType.TYPE_CLASS_NUMBER);
        viewHolder.lin_content1.setVisibility(View.VISIBLE);
        viewHolder.lin_content2.setVisibility(View.VISIBLE);
        viewHolder.lin_content3.setVisibility(View.VISIBLE);
        viewHolder.lin_content4.setVisibility(View.VISIBLE);
        viewHolder.tv_title1.setText("银行");
        viewHolder.tv_title2.setText("开户支行（选填）");
        viewHolder.tv_title3.setText("开户人");
        viewHolder.tv_title4.setText("收款账号");
        viewHolder.et_content1.setHint("例如:中国建设银行");
        viewHolder.et_content2.setHint("开户网点名称");
        viewHolder.et_content3.setHint("开户人姓名");
        viewHolder.et_content4.setHint("请输入正确的银行卡账号");
        viewHolder.et_content1.setText("");
        viewHolder.et_content2.setText("");
        viewHolder.et_content3.setText("");
        viewHolder.et_content4.setText("");
    }

    public static class ViewHolder {
        public View rootView;
        public EditText et_payment_mode;
        public LinearLayout lin_choosetype;
        public TextView tv_title1;
        public EditText et_content1;
        public LinearLayout lin_content1;
        public TextView tv_title2;
        public EditText et_content2;
        public LinearLayout lin_content2;
        public TextView tv_title3;
        public EditText et_content3;
        public LinearLayout lin_content3;
        public TextView tv_title4;
        public EditText et_content4;
        public LinearLayout lin_content4;
        public TextView tv_commit;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.et_payment_mode = (EditText) rootView.findViewById(R.id.et_payment_mode);
            this.lin_choosetype = (LinearLayout) rootView.findViewById(R.id.lin_choosetype);
            this.tv_title1 = (TextView) rootView.findViewById(R.id.tv_title1);
            this.et_content1 = (EditText) rootView.findViewById(R.id.et_content1);
            this.lin_content1 = (LinearLayout) rootView.findViewById(R.id.lin_content1);
            this.tv_title2 = (TextView) rootView.findViewById(R.id.tv_title2);
            this.et_content2 = (EditText) rootView.findViewById(R.id.et_content2);
            this.lin_content2 = (LinearLayout) rootView.findViewById(R.id.lin_content2);
            this.tv_title3 = (TextView) rootView.findViewById(R.id.tv_title3);
            this.et_content3 = (EditText) rootView.findViewById(R.id.et_content3);
            this.lin_content3 = (LinearLayout) rootView.findViewById(R.id.lin_content3);
            this.tv_title4 = (TextView) rootView.findViewById(R.id.tv_title4);
            this.et_content4 = (EditText) rootView.findViewById(R.id.et_content4);
            this.lin_content4 = (LinearLayout) rootView.findViewById(R.id.lin_content4);
            this.tv_commit = (TextView) rootView.findViewById(R.id.tv_commit);
        }

    }
}

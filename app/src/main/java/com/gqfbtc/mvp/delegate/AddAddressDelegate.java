package com.gqfbtc.mvp.delegate;


import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.gqfbtc.R;

public class AddAddressDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
        setCommitEnableView(viewHolder.tv_commit);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_address;
    }

    public void initEth() {
        viewHolder.tv_title.setText("ETH地址");
    }

    public void initBtc() {
        viewHolder.tv_title.setText("BTC地址");
        viewHolder.tv_commit.setText("添加BTC地址");
    }

    public static class ViewHolder {
        public View rootView;
        public TextView tv_title;
        public EditText et_address;
        public TextView tv_remark;
        public EditText et_remark;
        public TextView tv_commit;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.et_address = (EditText) rootView.findViewById(R.id.et_address);
            this.tv_remark = (TextView) rootView.findViewById(R.id.tv_remark);
            this.et_remark = (EditText) rootView.findViewById(R.id.et_remark);
            this.tv_commit = (TextView) rootView.findViewById(R.id.tv_commit);
        }

    }
}

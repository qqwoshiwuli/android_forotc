package com.gqfbtc.mvp.delegate;


import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.gqfbtc.R;
import com.gqfbtc.widget.RatingBar;

public class TransactAppraiseDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
        setCommitEnableView(viewHolder.tv_commit);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_transact_appraise;
    }


    public static class ViewHolder {
        public View rootView;
        public TextView tv_toast;
        public TextView tv_first;
        public RatingBar rb_seller;
        public EditText et_seller;
        public TextView tv_second;
        public RatingBar rb_guarantor;
        public EditText et_guarantor;
        public TextView tv_commit;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_toast = (TextView) rootView.findViewById(R.id.tv_toast);
            this.tv_first = (TextView) rootView.findViewById(R.id.tv_first);
            this.rb_seller = (RatingBar) rootView.findViewById(R.id.rb_seller);
            this.et_seller = (EditText) rootView.findViewById(R.id.et_seller);
            this.tv_second = (TextView) rootView.findViewById(R.id.tv_second);
            this.rb_guarantor = (RatingBar) rootView.findViewById(R.id.rb_guarantor);
            this.et_guarantor = (EditText) rootView.findViewById(R.id.et_guarantor);
            this.tv_commit = (TextView) rootView.findViewById(R.id.tv_commit);
        }

    }
}

package com.gqfbtc.mvp.delegate;


import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.gqfbtc.R;

public class SetDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_set;
    }


    public static class ViewHolder {
        public View rootView;
        public RecyclerView rv_set;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.rv_set = (RecyclerView) rootView.findViewById(R.id.rv_set);
        }

    }
}

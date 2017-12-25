package com.gqfbtc.mvp.fragment;

import com.fivefivelike.mybaselibrary.base.BaseDataBindFragment;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.gqfbtc.R;
import com.gqfbtc.mvp.databinder.MajorsBinder;
import com.gqfbtc.mvp.delegate.MajorsDelegate;

public class MajorsFrgment extends BaseDataBindFragment<MajorsDelegate, MajorsBinder> {


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("交易").setSubTitle("切至普通版"));
        viewDelegate.getmToolbarSubTitle().setTextColor(getResources().getColor(R.color.color_blue));
    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        super.onServiceError(data, info, status, requestCode);
        switch (requestCode) {
        }
    }

    @Override
    protected Class<MajorsDelegate> getDelegateClass() {
        return MajorsDelegate.class;
    }

    @Override
    public MajorsBinder getDataBinder(MajorsDelegate viewDelegate) {
        return new MajorsBinder(viewDelegate);
    }

}

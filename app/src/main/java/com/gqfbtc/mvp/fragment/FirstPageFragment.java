package com.gqfbtc.mvp.fragment;

import com.fivefivelike.mybaselibrary.base.BaseDataBindFragment;
import com.gqfbtc.mvp.databinder.FirstPageBinder;
import com.gqfbtc.mvp.delegate.FirstPageDelegate;

/**
 * Created by 郭青枫 on 2017/11/28 0028.
 */

public class FirstPageFragment extends BaseDataBindFragment<FirstPageDelegate, FirstPageBinder> {
    @Override
    public FirstPageBinder getDataBinder(FirstPageDelegate viewDelegate) {
        return new FirstPageBinder(viewDelegate);
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {

    }

    @Override
    protected Class<FirstPageDelegate> getDelegateClass() {
        return FirstPageDelegate.class;
    }
}

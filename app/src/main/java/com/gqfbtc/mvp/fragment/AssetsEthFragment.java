package com.gqfbtc.mvp.fragment;

import com.fivefivelike.mybaselibrary.base.BaseDataBindFragment;
import com.gqfbtc.mvp.databinder.AssetsEthBinder;
import com.gqfbtc.mvp.delegate.AssetsEthDelegate;

/**
 * Created by 郭青枫 on 2017/10/21.
 */

public class AssetsEthFragment extends BaseDataBindFragment<AssetsEthDelegate, AssetsEthBinder> {
    @Override
    public AssetsEthBinder getDataBinder(AssetsEthDelegate viewDelegate) {
        return new AssetsEthBinder(viewDelegate);
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {

    }

    @Override
    protected Class<AssetsEthDelegate> getDelegateClass() {
        return AssetsEthDelegate.class;
    }

}

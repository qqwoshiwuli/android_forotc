package com.gqfbtc.mvp.databinder;

import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.gqfbtc.mvp.delegate.MyAddressDelegate;

/**
 * Created by 郭青枫 on 2017/10/14.
 */

public class MyAddressBinder extends BaseDataBind<MyAddressDelegate> {
    public MyAddressBinder(MyAddressDelegate viewDelegate) {
        super(viewDelegate);
    }
}
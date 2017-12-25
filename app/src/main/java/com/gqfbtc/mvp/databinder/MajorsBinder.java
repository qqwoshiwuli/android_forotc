package com.gqfbtc.mvp.databinder;

import com.gqfbtc.mvp.delegate.MajorsDelegate;
import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;

import io.reactivex.disposables.Disposable;

public class MajorsBinder extends BaseDataBind<MajorsDelegate> {

    public MajorsBinder(MajorsDelegate viewDelegate) {
        super(viewDelegate);
    }


}
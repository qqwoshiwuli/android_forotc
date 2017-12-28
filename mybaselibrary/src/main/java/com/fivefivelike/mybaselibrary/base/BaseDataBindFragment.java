package com.fivefivelike.mybaselibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.fivefivelike.mybaselibrary.http.ServiceDataCallback;
import com.fivefivelike.mybaselibrary.mvp.databind.IDataBind;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;

import io.reactivex.disposables.Disposable;


/**
 * Created by 郭青枫 on 2017/7/3.
 */

public abstract class BaseDataBindFragment<T extends BaseDelegate, D extends IDataBind> extends BaseFragment<T> implements RequestCallback, ServiceDataCallback, DefaultClickLinsener {
    protected D binder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binder = getDataBinder(viewDelegate);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public abstract D getDataBinder(T viewDelegate);

    @Override
    public void onDestroy() {
        cancelpost();
        super.onDestroy();
    }

    /**
     * 添加订阅
     *
     * @param disposable
     */
    public void addRequest(Disposable disposable) {
        if (binder != null) {
            binder.addRequest(disposable);
        }
    }

    /**
     * 取消订阅
     */
    private void cancelpost() {
        if (binder != null) {
            binder.cancelpost();
        }
    }

    @Override
    public void success(int requestCode, String jsonData) {
        if (binder != null) {
            binder.success(getActivity(), this, this, requestCode, jsonData);
        }
    }


    @Override
    public void error(int requestCode, Throwable exThrowable) {
        if (binder != null) {
            binder.showError(exThrowable);
        }
    }

    @Override
    public void onDataSuccess(String data, String info, int status, int requestCode) {
        onServiceSuccess(data, info, status, requestCode);
    }

    @Override
    public void onDataError(String data, String info, int status, int requestCode) {
        onServiceError(data, info, status, requestCode);
    }


    protected void onServiceError(String data, String info, int status, int requestCode) {
        if (binder.isMissToken(status)) {
            binder.loginAgain(getActivity());
        }
    }

    protected abstract void onServiceSuccess(String data, String info, int status, int requestCode);

    @Override
    public void onClick(View view, int position, Object item) {
        if (binder != null) {
            binder.onDialogBtnClick(getActivity(), view, position, item);
        }
    }

}

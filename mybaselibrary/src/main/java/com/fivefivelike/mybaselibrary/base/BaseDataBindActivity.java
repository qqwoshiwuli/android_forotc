package com.fivefivelike.mybaselibrary.base;

import android.os.Bundle;
import android.view.View;

import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.fivefivelike.mybaselibrary.http.ServiceDataCallback;
import com.fivefivelike.mybaselibrary.mvp.databind.IDataBind;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;

import io.reactivex.disposables.Disposable;


/**
 * Created by 郭青枫 on 2017/7/3.
 */

public abstract class BaseDataBindActivity<T extends BaseDelegate, D extends IDataBind> extends BaseActivity<T> implements RequestCallback,ServiceDataCallback, DefaultClickLinsener {
    protected D binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binder = getDataBinder(viewDelegate);
        super.onCreate(savedInstanceState);
    }


    public abstract D getDataBinder(T viewDelegate);

    @Override
    protected void onDestroy() {
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
            binder.success(this, this, this, requestCode, jsonData);
        }
        //        String info;
        //        int status;
        //        String data;
        //        KLog.i(this.getClass().getName(), "请求数据: " + jsonData);
        //
        //        try {
        //            JSONObject object = new JSONObject(jsonData);
        //            info = object.getString("msg");
        //            status = object.getInt("code");
        //            data = object.getString("data");
        //
        //            if (status == 0000) {
        //                onServiceSuccess(data, info, status, requestCode);
        //            } else {
        //                onServiceError(data, info, status, requestCode);
        //            }
        //            String dialog = GsonUtil.getInstance().getValue(jsonData, ResultDialog.DIALOG_KEY, String.class);
        //
        //            if (TextUtils.isEmpty(dialog) && status != 0000) {
        //                ToastUtil.show(info);
        //            }
        //            if (!TextUtils.isEmpty(dialog)) {
        //                ResultDialogEntity resultDialogEntity = ResultDialog.getInstence().ShowResultDialog(this, dialog, this);
        //                if (TextUtils.isEmpty(resultDialogEntity.getType())) {
        //                    ToastUtil.show(info);
        //                }
        //            }
        //        } catch (JSONException e) {
        //            e.printStackTrace();
        //            error(requestCode, e);
        //        }
    }

    @Override
    public void onDataSuccess(String data, String info, int status, int requestCode) {
        onServiceSuccess(data, info, status, requestCode);
    }
    @Override
    public void onDataError(String data, String info, int status, int requestCode) {
        onServiceError(data, info, status, requestCode);
    }

    @Override
    public void error(int requestCode, Throwable exThrowable) {
        if (binder != null) {
            binder.showError(exThrowable);
        }
    }

    protected void onServiceError(String data, String info, int status, int requestCode) {
        if (binder.isMissToken(status)) {
            binder.loginAgain(this, loginCls);
        }
    }

    protected abstract void onServiceSuccess(String data, String info, int status, int requestCode);

    @Override
    public void onClick(View view, int position, Object item) {
        if (binder != null) {
            binder.onDialogBtnClick(this, view, position, item);
        }
    }

}


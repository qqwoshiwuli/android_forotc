package com.fivefivelike.mybaselibrary.base;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.fivefivelike.mybaselibrary.entity.ResultDialogEntity;
import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.fivefivelike.mybaselibrary.mvp.databind.IDataBind;
import com.fivefivelike.mybaselibrary.utils.ActUtil;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.fivefivelike.mybaselibrary.utils.logger.KLog;
import com.fivefivelike.mybaselibrary.view.dialog.ResultDialog;
import com.yanzhenjie.nohttp.error.NetworkError;
import com.yanzhenjie.nohttp.error.NotFoundCacheError;
import com.yanzhenjie.nohttp.error.TimeoutError;
import com.yanzhenjie.nohttp.error.URLError;
import com.yanzhenjie.nohttp.error.UnKnownHostError;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;


/**
 * Created by 郭青枫 on 2017/7/3.
 */

public abstract class BaseDataBindActivity<T extends BaseDelegate, D extends IDataBind> extends BaseActivity<T> implements RequestCallback, DefaultClickLinsener {
    protected List<Disposable> disposableList;
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
        if (disposableList == null) {
            disposableList = new ArrayList<>();
        }
        disposableList.add(disposable);
    }

    /**
     * 取消订阅
     */
    private void cancelpost() {
        if (disposableList != null) {
            for (Disposable disposable : disposableList) {
                if (disposable != null) {
                    disposable.dispose();
                }
            }
        }
    }

    @Override
    public void success(int requestCode, String jsonData) {
        String info;
        int status;
        String data;
        KLog.i(this.getClass().getName(), "请求数据: " + jsonData);
        if (viewDelegate != null) {
            viewDelegate.commitEnableView(true);
        }
        try {
            JSONObject object = new JSONObject(jsonData);
            info = object.getString("msg");
            status = object.getInt("code");
            data = object.getString("data");

            if (status == 0000) {
                onServiceSuccess(data, info, status, requestCode);
            } else {
                onServiceError(data, info, status, requestCode);
            }
            String dialog = GsonUtil.getInstance().getValue(jsonData, ResultDialog.DIALOG_KEY, String.class);
            if (TextUtils.isEmpty(dialog) && status != 0000) {
                ToastUtil.show(info);
            }
            if (!TextUtils.isEmpty(dialog)) {
                ResultDialogEntity resultDialogEntity = ResultDialog.getInstence().ShowResultDialog(this, dialog, this);
                if(TextUtils.isEmpty(resultDialogEntity.getType())){
                    ToastUtil.show(info);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            error(requestCode, e);
        }
    }

    @Override
    public void error(int requestCode, Throwable exThrowable) {
        exThrowable.printStackTrace();
        // 提示异常信息。
        if (exThrowable instanceof NetworkError) {// 网络不好
            ToastUtil.show("网络不好");
        } else if (exThrowable instanceof TimeoutError) {// 请求超时
            ToastUtil.show("请求超时");
        } else if (exThrowable instanceof UnKnownHostError) {// 找不到服务器
            ToastUtil.show("找不到服务器");
        } else if (exThrowable instanceof URLError) {// 找不到服务器
            ToastUtil.show("找不到服务器");
        } else if (exThrowable instanceof NotFoundCacheError) {
            ToastUtil.show("没有缓存");
            //这个异常只会在仅仅查找缓存时没有找到缓存时返回
        } else if (exThrowable instanceof ProtocolException) {
            ToastUtil.show("系统不支持");
        } else if (exThrowable instanceof JSONException) {
            ToastUtil.show("数据格式错误");
        } else {
            ToastUtil.show("未知错误");
        }
    }

    protected void onServiceError(String data, String info, int status, int requestCode) {
        missToken(status);
    }

    private void missToken(int status) {
        if (status == 4444 || status == 4445) {
            Intent intent = new Intent(this, loginCls);
            ActUtil.getInstance().killAllActivity(this);
            startActivity(intent);
            this.finish();
        }
    }

    protected abstract void onServiceSuccess(String data, String info, int status, int requestCode);

    @Override
    public void onClick(View view, int position, Object item) {
        if (position == ResultDialog.CONFIRM_POSITION) {
            EventBus.getDefault().post(((ResultDialogEntity) item));
        } else if (position == ResultDialog.CANNEL_POSITION) {
            if (((ResultDialogEntity) item).isCancelAndClose()) {
                onBackPressed();
            }
        }
    }

}


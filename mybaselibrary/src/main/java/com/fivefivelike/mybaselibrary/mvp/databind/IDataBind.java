package com.fivefivelike.mybaselibrary.mvp.databind;

import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.fivefivelike.mybaselibrary.mvp.view.IDelegate;

import io.reactivex.disposables.Disposable;

/**
 * Created by 郭青枫 on 2017/7/7.
 */

public interface IDataBind<T extends IDelegate> {

    void addRequest(Disposable disposable);

    void cancelpost();

    boolean isMissToken(int status);

    void loginAgain(FragmentActivity activity, Class login);

    void showError(Throwable exThrowable);

    void onDialogBtnClick(FragmentActivity activity, View view, int position, Object item);

}

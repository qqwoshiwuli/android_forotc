package com.fivefivelike.mybaselibrary.mvp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 郭青枫 on 2017/7/3.
 */

public abstract class IDelegateImpl implements IDelegate {
    protected final SparseArray<View> mViews = new SparseArray<View>();

    protected View rootView;

    @Override
    public void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int rootLayoutId = getLayoutId();
        rootView = inflater.inflate(rootLayoutId, container, false);
    }

    protected abstract int getLayoutId();


    @Override
    public View getRootView() {
        return rootView;
    }

    private <T extends View> T bindView(int id) {
        T view = (T) mViews.get(id);
        if (view == null) {
            view = (T) rootView.findViewById(id);
            mViews.put(id, view);
        }
        return view;
    }

    public <T extends View> T getViewById(int id) {
        return (T) bindView(id);
    }

    public void setOnClickListener(View.OnClickListener listener, int... ids) {
        if (ids == null) {
            return;
        }
        for (int id : ids) {
            getViewById(id).setOnClickListener(listener);
        }
    }

    public <T extends AppCompatActivity> T getActivity() {
        return (T) rootView.getContext();
    }

}

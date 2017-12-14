package com.gqfbtc.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by 郭青枫 on 2017/11/29 0029.
 */

public class DispatchFrameLayout extends FrameLayout {
    public DispatchFrameLayout(@NonNull Context context) {
        super(context);
    }

    public DispatchFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DispatchFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        getParent().getParent().requestDisallowInterceptTouchEvent(true);
        getParent().getParent().getParent().requestDisallowInterceptTouchEvent(true);
        getParent().getParent().getParent().getParent().requestDisallowInterceptTouchEvent(true);
        getParent().getParent().getParent().getParent().getParent().requestDisallowInterceptTouchEvent(true);
        getParent().getParent().getParent().getParent().getParent().getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }




}

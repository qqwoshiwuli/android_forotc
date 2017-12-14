package com.gqfbtc.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by 郭青枫 on 2017/11/29 0029.
 */

public class WaitTransactSwipeRefreshLayout extends SwipeRefreshLayout {

    int height;

    public void setHeight(int height) {
        this.height = height;
    }

    public WaitTransactSwipeRefreshLayout(@NonNull Context context) {
        super(context);
    }

    public WaitTransactSwipeRefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    float startX;
    float startY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                startX = ev.getX();
                startY = ev.getY();
                if (startY > height) {
                    return false;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}

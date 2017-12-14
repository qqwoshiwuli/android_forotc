package com.gqfbtc.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.fivefivelike.mybaselibrary.utils.AndroidUtil;

/**
 * Created by 郭青枫 on 2017/11/20.
 */

public class MaxHeightLinearLayoutMAnager extends LinearLayoutManager {

    Context context;


    int maxHeight;

    public MaxHeightLinearLayoutMAnager(Context context) {
        super(context);
        this.context = context;
        maxHeight = AndroidUtil.getScreenW(context, true) / 3;
    }

    public MaxHeightLinearLayoutMAnager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        this.context = context;
    }

    public MaxHeightLinearLayoutMAnager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        if (heightSpec > maxHeight) {
            heightSpec = maxHeight;
        }
        super.onMeasure(recycler, state, widthSpec, heightSpec);
    }
}

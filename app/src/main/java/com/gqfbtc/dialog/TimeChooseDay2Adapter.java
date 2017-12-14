package com.gqfbtc.dialog;

import com.wheelview.WheelAdapter;

/**
 * Created by 郭青枫 on 2017/10/28.
 */

public class TimeChooseDay2Adapter implements WheelAdapter {
    String[] dayTitle = {"每日", "次日"};

    @Override
    public int getItemsCount() {
        return 2;
    }

    @Override
    public String getItem(int index) {

        return dayTitle[index];
    }

    @Override
    public int getMaximumLength() {
        return 4;
    }
}

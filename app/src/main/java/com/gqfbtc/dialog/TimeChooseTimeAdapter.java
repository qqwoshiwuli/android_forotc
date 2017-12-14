package com.gqfbtc.dialog;

import com.wheelview.WheelAdapter;

import java.util.List;

/**
 * Created by 郭青枫 on 2017/10/28.
 */

public class TimeChooseTimeAdapter implements WheelAdapter {

    private List<String> list;

    public TimeChooseTimeAdapter(List<String> list) {
        super();
        this.list = list;
    }

    @Override
    public int getItemsCount() {
        return list.size();
    }

    @Override
    public String getItem(int index) {

        return list.get(index);
    }

    @Override
    public int getMaximumLength() {
        return 6;
    }
}

package com.gqfbtc.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.view.dialog.BaseDialog;
import com.gqfbtc.R;
import com.wheelview.OnWheelChangedListener;
import com.wheelview.WheelView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 郭青枫 on 2017/10/28.
 */

public class TimeChooseDialog extends BaseDialog implements OnWheelChangedListener {

    List<String> day1List;
    List<String> day2List;

    String chooseDay1;
    String chooseDay2;
    String chooseTime1;
    String chooseTime2;

    List<String> timeList1;
    List<String> timeList2;

    private TextView title;
    private WheelView day1;
    private WheelView time1;
    private WheelView day2;
    private WheelView time2;
    private TextView tv_commit;

    public TimeChooseDialog(Context context, OnTimeChooseLinsener onTimeChooseLinsener) {
        super(context);
        this.onTimeChooseLinsener = onTimeChooseLinsener;
    }

    @Override
    public void onChanged(WheelView wheel, int oldIndex, int newIndex) {
        if (wheel == day1) {
            chooseDay1 = day1List.get(newIndex);
            updataTime2();
        } else if (wheel == time1) {
            chooseTime1 = timeList1.get(newIndex);
            updataTime2();
        } else if (wheel == day2) {
            chooseDay2 = day2List.get(newIndex);
            updataTime1();
        } else if (wheel == time2) {
            chooseTime2 = timeList1.get(newIndex);
            updataTime1();
        }
    }

    public TimeChooseDialog setDefaultTime(String chooseday1, String choosetime1, String chooseday2, String choosetime2) {
        if (
                !TextUtils.isEmpty(chooseday1) &&
                        !TextUtils.isEmpty(choosetime1) &&
                        !TextUtils.isEmpty(chooseday2) &&
                        !TextUtils.isEmpty(choosetime2)
                ) {
            day1.setCurrentIndex(day1List.indexOf(chooseday1));
            day2.setCurrentIndex(day2List.indexOf(chooseday2));
            time1.setCurrentIndex(timeList1.indexOf(choosetime1));
            time2.setCurrentIndex(timeList2.indexOf(choosetime2));
        }
        return this;
    }

    private void updataTime1() {
        //联动左边
        if (chooseDay1.equals(chooseDay2)) {
            if (day1List.get(0).equals(chooseDay1)) {
                //都是今天
                //判断时间之差是否在0小时以下
                int index1 = timeList1.indexOf(chooseTime1);
                int index2 = timeList2.indexOf(chooseTime2);
                if (index1 > index2 - 1) {
                    time1.setCurrentIndex(index2 - 1);
                }
                if (index2 == 0) {
                    time2.setCurrentIndex(1);
                }
            } else {
                //都是次日
                //判断时间之差是否在0小时以下
                int index1 = timeList1.indexOf(chooseTime1);
                int index2 = timeList2.indexOf(chooseTime2);
                if (index1 > index2) {
                    time1.setCurrentIndex(index2);
                }
            }
        } else {
            if (day1List.get(0).equals(chooseDay1)) {
                //每日 次日
                //判断时间之差是否在24小时以上
                //                int index1 = timeList1.indexOf(chooseTime1);
                //                int index2 = timeList2.indexOf(chooseTime2);
                //                if (index1 < index2) {
                //                    time1.setCurrentIndex(index2);
                //                }
            } else {
                //次日 每日
                //改变 day2为次日
                day1.setCurrentIndex(0);
            }
        }
    }

    private void updataTime2() {
        //联动右边
        if (chooseDay1.equals(chooseDay2)) {
            if (day1List.get(0).equals(chooseDay1)) {
                //都是今天
                //判断时间之差是否在0小时以下
                int index1 = timeList1.indexOf(chooseTime1);
                int index2 = timeList2.indexOf(chooseTime2);
                if (index1 != 47) {
                    if (index1 > index2 - 1) {
                        time2.setCurrentIndex(index1 + 1);
                    }
                } else {
                    if (index1 > index2 - 1) {
                        day2.setCurrentIndex(1);
                        time2.setCurrentIndex(0);
                    }
                }
            } else {
                //都是次日
                //判断时间之差是否在0小时以下
                int index1 = timeList1.indexOf(chooseTime1);
                int index2 = timeList2.indexOf(chooseTime2);
                if (index1 > index2) {
                    time2.setCurrentIndex(index1);
                }
            }
        } else {
            if (day1List.get(0).equals(chooseDay1)) {
                //                //每日 次日
                //                //判断时间之差是否在24小时以上
                //                int index1 = timeList1.indexOf(chooseTime1);
                //                int index2 = timeList2.indexOf(chooseTime2);
                //                if (index1 < index2) {
                //                    time2.setCurrentIndex(index1);
                //                }
            } else {
                //次日 每日
                //改变 day2为次日
                day2.setCurrentIndex(1);
            }
        }
    }


    @Override
    protected int getLayout() {
        return R.layout.dialog_time_choose;
    }

    @Override
    protected void startInit() {
        getWindow().setGravity(Gravity.CENTER);
        setWindowNoPadding();

        day1 = (WheelView) findViewById(R.id.day1);
        time1 = (WheelView) findViewById(R.id.time1);
        day2 = (WheelView) findViewById(R.id.day2);
        time2 = (WheelView) findViewById(R.id.time2);
        title = (TextView) findViewById(R.id.title);
        tv_commit = (TextView) findViewById(R.id.tv_commit);

        int textsize = mContext.getResources().getDimensionPixelSize(R.dimen.trans_28px);
        day1.setTextSize(textsize);
        time1.setTextSize(textsize);
        day2.setTextSize(textsize);
        time2.setTextSize(textsize);

        day1.setVisibleItems(5);
        time1.setVisibleItems(5);
        day2.setVisibleItems(5);
        time2.setVisibleItems(5);

        day1.setOnWheelChangedListener(this);
        time1.setOnWheelChangedListener(this);
        day2.setOnWheelChangedListener(this);
        time2.setOnWheelChangedListener(this);
        setTimes();

        day1.setAdapter(new TimeChooseDay1Adapter());
        day2.setAdapter(new TimeChooseDay2Adapter());
        time1.setAdapter(new TimeChooseTimeAdapter(timeList1));
        time2.setAdapter(new TimeChooseTimeAdapter(timeList2));

        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTimeChooseLinsener.setOnTimeChooseListener(chooseDay1, chooseTime1, chooseDay2, chooseTime2);
                dismiss();
            }
        });

        chooseDay1 = day1List.get(0);
        chooseDay2 = day2List.get(0);
        chooseTime1 = timeList1.get(0);
        chooseTime2 = timeList2.get(1);
        time2.setCurrentIndex(1);

    }

    private void setTimes() {
        String[] timeTitle = {
                "00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30",
                "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
                "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30",
                "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30"
        };
        String[] day2Title = {"每日", "次日"};
        String[] day1Title = {"每日"};
        timeList1 = new ArrayList<>();
        timeList2 = new ArrayList<>();
        for (int i = 0; i < timeTitle.length; i++) {
            timeList1.add(timeTitle[i]);
            timeList2.add(timeTitle[i]);
        }

        day1List = Arrays.asList(day1Title);
        day2List = Arrays.asList(day2Title);

        // timeList1 = Arrays.asList(timeTitle);
        //timeList2 = Arrays.asList(timeTitle);
    }

    OnTimeChooseLinsener onTimeChooseLinsener;

    public void setOnTimeChooseLinsener(OnTimeChooseLinsener onTimeChooseLinsener) {
        this.onTimeChooseLinsener = onTimeChooseLinsener;
    }

    public interface OnTimeChooseLinsener {
        void setOnTimeChooseListener(String day1, String time1, String day2, String time2);
    }

}

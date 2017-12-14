package com.gqfbtc.dialog;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;

import com.circledialog.CircleDialog;
import com.circledialog.callback.ConfigText;
import com.circledialog.params.TextParams;
import com.gqfbtc.R;

/**
 * Created by 郭青枫 on 2017/11/5.
 */

public class HelperTostDialog {

    private static class helper {
        private static HelperTostDialog helperTostDialog = new HelperTostDialog();
    }

    public static HelperTostDialog getInstence() {
        return helper.helperTostDialog;
    }

    private HelperTostDialog() {

    }

    public void showToastDialog(Context context, int strId) {
        String toast = context.getResources().getString(strId);
        showDialog((FragmentActivity) context, toast);
    }


    private void showDialog(FragmentActivity activity, String title) {
        final int textSize = activity.getResources().getDimensionPixelSize(R.dimen.text_trans_28px);
        new CircleDialog.Builder(activity)
                .setText(title)
                //.setTitle(title)
                .setWidth(0.7f)
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .configText(new ConfigText() {
                    @Override
                    public void onConfig(TextParams params) {
                        params.gravity = Gravity.LEFT;
                        params.padding = new int[]{50, 50, 50, 50};
                        params.textSize = textSize;
                    }
                }).show();
    }


}

package com.fivefivelike.mybaselibrary.base;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.circledialog.CircleDialog;
import com.fivefivelike.mybaselibrary.R;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.mvp.presenter.ActivityPresenter;
import com.fivefivelike.mybaselibrary.utils.ActUtil;
import com.githang.statusbar.StatusBarCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 郭青枫 on 2017/7/7.
 */

public abstract class BaseActivity<T extends BaseDelegate> extends ActivityPresenter<T> implements View.OnClickListener, CircleDialog.CircleDialogLinsener {
    public static Class loginCls;
    public static Class helpCls;
    public static List<String> doubleClickActList = new ArrayList<>();
    public Activity mContext;
    private long exitTime = 0;

    public static BaseAppLinsener baseAppLinsener;

    private int WindowManagerLayoutParams = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN;

    public void setWindowManagerLayoutParams(int windowManagerLayoutParams) {
        WindowManagerLayoutParams = windowManagerLayoutParams;
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.toolbar_img) {
                clickRightIv();
            }
            if (id == R.id.toolbar_img1) {
                clickRightIv1();
            } else if (id == R.id.toolbar_img2) {
                clickRightIv2();
            } else if (id == R.id.toolbar_subtitle) {
                clickRightTv();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        viewDelegate.setCircleDialogLinsener(this);
        super.onCreate(savedInstanceState);
        mContext = this;
        ActUtil.getInstance().addActivity(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white), true);
            } else {
                StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.font_grey), false);
            }

            if (viewDelegate.isNoStatusBarFlag()) {
                addNoStatusBarFlag();
            } else {
                clearNoStatusBarFlag();
            }
        }

    }

    public void onCancel(DialogInterface dialog) {
        onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActUtil.getInstance().removeActivitiyFromStack(this);//将页面从栈中移除
    }

    @Override
    public void onBackPressed() {
        if (doubleClickActList.contains(getClass().getName())) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                ActUtil.getInstance().AppExit(this);
            }
            return;
        }
        super.onBackPressed();
    }

    /**
     * 去掉状态栏
     */
    protected void addNoStatusBarFlag() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    /**
     * 显示状态栏
     */
    protected void clearNoStatusBarFlag() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    protected void initToolbar(ToolbarBuilder toolbarBuilder) {
        if (viewDelegate != null) {
            if (onClickListener != null) {
                viewDelegate.initToolBar(this, onClickListener, toolbarBuilder);
            }
        }
    }


    @Override
    public void onClick(View v) {

    }

    protected void clickRightIv() {
    }

    protected void clickRightIv1() {
    }

    protected void clickRightIv2() {
    }

    protected void clickRightTv() {
        if ("帮助".equals(viewDelegate.getmToolbarSubTitle().getText().toString())) {
            //gotoActivity(helpCls).startAct();
            baseAppLinsener.startCustomerService(mContext);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (WindowManagerLayoutParams != 0) {
            getWindow().setSoftInputMode(WindowManagerLayoutParams);
        }
    }
}

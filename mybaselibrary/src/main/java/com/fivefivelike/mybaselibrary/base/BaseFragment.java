package com.fivefivelike.mybaselibrary.base;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fivefivelike.mybaselibrary.R;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.mvp.presenter.FragmentPresenter;

/**
 * Created by 郭青枫 on 2017/7/7.
 */

public abstract class BaseFragment<T extends BaseDelegate> extends FragmentPresenter<T> implements View.OnClickListener {


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
    /**
     * 初始化标题
     * @param toolbarBuilder
     */
    protected void initToolbar(ToolbarBuilder toolbarBuilder) {
        viewDelegate.initToolBar((AppCompatActivity) getActivity(), onClickListener, toolbarBuilder);
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
    }

}

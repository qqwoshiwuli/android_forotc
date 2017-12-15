package com.gqfbtc.mvp.fragment;

import android.support.v4.app.Fragment;

import com.fivefivelike.mybaselibrary.base.BaseFragment;
import com.gqfbtc.R;
import com.gqfbtc.mvp.delegate.FragmentPageDelegate;

import java.util.ArrayList;

/**
 * Created by 郭青枫 on 2017/10/27.
 */

public class ShelvesAdvertisingFragment extends BaseFragment<FragmentPageDelegate> {

    private String[] mTitles = {"BTC", "链克"};
    ArrayList<Fragment> orderFragments;
    ShelvesAdvertisingBtcFragment shelvesAdvertisingBtcFragment;
    ShelvesAdvertisingUcxFragment shelvesAdvertisingUcxFragment;

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initFragment();
    }

    private void initFragment() {
        orderFragments = new ArrayList<>();
        shelvesAdvertisingBtcFragment = new ShelvesAdvertisingBtcFragment();
        shelvesAdvertisingUcxFragment = new ShelvesAdvertisingUcxFragment();
        orderFragments.add(shelvesAdvertisingBtcFragment);
        orderFragments.add(shelvesAdvertisingUcxFragment);
        viewDelegate.viewHolder.tl_2.setmIndicatorId(R.drawable.shape_blue_max_radiu);
        viewDelegate.viewHolder.tl_2.setViewPager(viewDelegate.viewHolder.vp_page,
                mTitles, this, orderFragments);
    }

    protected Class<FragmentPageDelegate> getDelegateClass() {
        return FragmentPageDelegate.class;
    }

}
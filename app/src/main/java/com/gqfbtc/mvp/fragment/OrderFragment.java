package com.gqfbtc.mvp.fragment;

import android.support.v4.app.Fragment;

import com.fivefivelike.mybaselibrary.base.BaseFragment;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.gqfbtc.R;
import com.gqfbtc.base.Application;
import com.gqfbtc.entity.event.TransactEvent;
import com.gqfbtc.mvp.delegate.ActivityPageDelegate;

import java.util.ArrayList;

/**
 * Created by 郭青枫 on 2017/10/16.
 */

public class OrderFragment extends BaseFragment<ActivityPageDelegate> {

    private String[] mTitles = {"BTC", "链克"};
    OrderBtcFragment orderBtcFragment;
    OrderUcxFragment orderUcxFragment;
    ArrayList<Fragment> orderFragments;

    public void addTransactEvent(TransactEvent transactEvent) {
        if (orderBtcFragment != null) {
            orderBtcFragment.addTransactEvent(transactEvent);
        }
        if (orderUcxFragment != null) {
            orderBtcFragment.addTransactEvent(transactEvent);
        }
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle(CommonUtils.getString(R.string.str_title_orderfragment)).setSubTitle(CommonUtils.getString(R.string.str_subtitle_customer_service)).setShowBack(false));
        initFragment();
    }

    private void initFragment() {
        orderFragments = new ArrayList<>();
        orderBtcFragment = new OrderBtcFragment();
        orderUcxFragment = new OrderUcxFragment();
        orderFragments.add(orderBtcFragment);
        orderFragments.add(orderUcxFragment);
        viewDelegate.viewHolder.tl_2.setmIndicatorId(R.drawable.shape_blue_max_radiu);
        viewDelegate.viewHolder.tl_2.setViewPager(viewDelegate.viewHolder.vp_page,
                mTitles, this, orderFragments);
    }

    protected void clickRightTv() {
        super.clickRightTv();
        Application.getInstance().startCustomerService(getActivity());
    }

    protected Class<ActivityPageDelegate> getDelegateClass() {
        return ActivityPageDelegate.class;
    }

}

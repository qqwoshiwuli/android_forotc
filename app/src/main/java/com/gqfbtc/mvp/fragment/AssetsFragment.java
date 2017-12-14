package com.gqfbtc.mvp.fragment;

import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseFragment;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.gqfbtc.R;
import com.gqfbtc.base.Application;
import com.gqfbtc.entity.TabEntity;
import com.gqfbtc.mvp.delegate.AssetsDelegate;

import java.util.ArrayList;

/**
 * Created by 郭青枫 on 2017/10/16.
 */

public class AssetsFragment extends BaseFragment<AssetsDelegate> {

    private String[] mTitles = {"BTC", "ETH"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("我的资产").setShowBack(false).setSubTitle("客服"));
        // viewDelegate.getmToolbarTitle().setVisibility(View.GONE);
        initTitle();
    }

    @Override
    protected void clickRightTv() {
        super.clickRightTv();
         Application.getInstance().startCustomerService(getActivity());
    }

    private void initTitle() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], 0, 0));
        }
        viewDelegate.viewHolder.tl_2.setIconVisible(false);
        viewDelegate.viewHolder.tl_2.setmIndicatorId(R.drawable.shape_blue_max_radiu);
        viewDelegate.viewHolder.tl_2.setTabData(mTabEntities);
        viewDelegate.viewHolder.tl_2.setVisibility(View.GONE);
        initFragment();
        viewDelegate.viewHolder.tl_2.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewDelegate.showFragment(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private void initFragment() {
        viewDelegate.initAddFragment(R.id.fl_root, getChildFragmentManager());
        viewDelegate.addFragment(new AssetsBtcFragment());
        viewDelegate.addFragment(new AssetsEthFragment());
        viewDelegate.showFragment(0);
    }

    @Override
    protected Class<AssetsDelegate> getDelegateClass() {
        return AssetsDelegate.class;
    }
}

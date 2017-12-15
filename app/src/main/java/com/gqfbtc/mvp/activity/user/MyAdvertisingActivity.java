package com.gqfbtc.mvp.activity.user;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.gqfbtc.R;
import com.gqfbtc.adapter.InnerPagerAdapter;
import com.gqfbtc.entity.TabEntity;
import com.gqfbtc.mvp.delegate.MyAdvertisingDelegate;
import com.gqfbtc.mvp.fragment.ProgressAdvertisingFragment;
import com.gqfbtc.mvp.fragment.ShelvesAdvertisingFragment;

import java.util.ArrayList;


public class MyAdvertisingActivity extends BaseActivity<MyAdvertisingDelegate> {

    private String[] mTitles = {"进行中", "已下架"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> fragments;

    @Override
    protected Class<MyAdvertisingDelegate> getDelegateClass() {
        return MyAdvertisingDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle(" ").setSubTitle("帮助"));
        viewDelegate.getmToolbarTitle().setVisibility(View.GONE);
        initTitle();
    }

    private void initTitle() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], 0, 0));
        }
        fragments = new ArrayList<>();
        fragments.add(new ProgressAdvertisingFragment());
        fragments.add(new ShelvesAdvertisingFragment());
        viewDelegate.viewHolder.tl_2.setIconVisible(false);
        viewDelegate.viewHolder.tl_2.setmIndicatorId(R.drawable.shape_blue_max_radiu);
        viewDelegate.viewHolder.tl_2.setTabData(mTabEntities);
        viewDelegate.viewHolder.vp_advertising.setAdapter(new InnerPagerAdapter(getSupportFragmentManager(), fragments, mTitles));
        viewDelegate.viewHolder.vp_advertising.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewDelegate.viewHolder.tl_2.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewDelegate.viewHolder.tl_2.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewDelegate.showFragment(position);
                viewDelegate.viewHolder.vp_advertising.setCurrentItem(position, true);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        initVp();
    }

    private void initVp() {

    }

}

package com.gqfbtc.mvp.activity.user;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.gqfbtc.R;
import com.gqfbtc.adapter.SlidWithFragmentPagerAdapter;
import com.gqfbtc.entity.TabEntity;
import com.gqfbtc.mvp.databinder.MyAddressBinder;
import com.gqfbtc.mvp.delegate.MyAddressDelegate;
import com.gqfbtc.mvp.fragment.EthAndBtcAddressFragment;

import java.util.ArrayList;


public class MyAddressActivity extends BaseDataBindActivity<MyAddressDelegate, MyAddressBinder> implements EthAndBtcAddressFragment.Linsener {

    private String[] mTitles = {"BTC"};//, "ETH"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected Class<MyAddressDelegate> getDelegateClass() {
        return MyAddressDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("收币地址").setSubTitle("帮助"));
        //viewDelegate.getmToolbarTitle().setVisibility(View.GONE);
        initTitle();
        viewDelegate.viewHolder.tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddAddressActivity.startAct(MyAddressActivity.this,
                        viewDelegate.viewHolder.vp_address.getCurrentItem(),
                        0x123
                );
            }
        });
        viewDelegate.viewHolder.tv_commit.setText("添加提币地址");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 0x123) {
                ((EthAndBtcAddressFragment) fragments.get(viewDelegate.viewHolder.vp_address.getCurrentItem())).onRefresh();
            }
        }
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
                viewDelegate.viewHolder.vp_address.setCurrentItem(position, true);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private void initFragment() {
        for (int i = 0; i < mTitles.length; i++) {
            fragments.add(EthAndBtcAddressFragment.newInstance(i + 1));
        }
        SlidWithFragmentPagerAdapter slidWithFragmentPagerAdapter = new SlidWithFragmentPagerAdapter(getSupportFragmentManager(), mTitles, fragments);
        viewDelegate.viewHolder.vp_address.setAdapter(slidWithFragmentPagerAdapter);
        viewDelegate.viewHolder.vp_address.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
    }

    @Override
    public MyAddressBinder getDataBinder(MyAddressDelegate viewDelegate) {
        return new MyAddressBinder(viewDelegate);
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {

    }

    @Override
    public void setResultOk() {
        setResult(RESULT_OK);
    }
}

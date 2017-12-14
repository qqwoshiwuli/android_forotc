package com.gqfbtc.mvp.fragment;

import android.support.v4.widget.SwipeRefreshLayout;

import com.fivefivelike.mybaselibrary.base.BaseDataBindFragment;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.gqfbtc.R;
import com.gqfbtc.entity.TabEntity;
import com.gqfbtc.entity.bean.AssetInformation;
import com.gqfbtc.mvp.databinder.AssetsBtcBinder;
import com.gqfbtc.mvp.delegate.AssetsBtcDelegate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * Created by 郭青枫 on 2017/10/21.
 */

public class AssetsBtcFragment extends BaseDataBindFragment<AssetsBtcDelegate, AssetsBtcBinder> {
    private String[] mTitles = {"充值", "提现"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    AssetInformation assetInformation;

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        tablayout();
        viewDelegate.viewHolder.swipeRefreshLayout.setRefreshing(true);
        viewDelegate.viewHolder.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                addRequest(binder.acc(AssetsBtcFragment.this));
                if (assetsBtcTopupFragment != null && assetsBtcWithdrawalFragment != null) {
                    if (assetsBtcTopupFragment.getTv_btc_address() != null) {
                        assetsBtcTopupFragment.onRefresh();
                    }
                    if (assetsBtcWithdrawalFragment.getRate() != null) {
                        assetsBtcWithdrawalFragment.onRefresh();
                    }
                }
            }
        });
    }

    private void tablayout() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], 0, 0));
        }
        viewDelegate.viewHolder.tl_2.setIconVisible(false);
        viewDelegate.viewHolder.tl_2.setmIndicatorId(R.drawable.shape_blue_max_radiu);
        viewDelegate.viewHolder.tl_2.setTabData(mTabEntities);
        viewDelegate.viewHolder.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewDelegate.viewHolder.swipeRefreshLayout.setRefreshing(false);
            }
        });
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

    private void initView() {
        if (assetInformation.getAmount().equals(new BigDecimal("0"))) {
            viewDelegate.viewHolder.tv_all_money.setText("0.00000000");
        } else {
            viewDelegate.viewHolder.tv_all_money.setText(assetInformation.getAmount().setScale(8, RoundingMode.DOWN).toString());
        }
        viewDelegate.viewHolder.tv_canuse.setText(assetInformation.getAmount().subtract(assetInformation.getFrozenAmount()).setScale(6, RoundingMode.DOWN).toString());
        viewDelegate.viewHolder.tv_unuse.setText(assetInformation.getFrozenAmount().setScale(6, RoundingMode.DOWN).toString());
    }

    AssetsBtcTopupFragment assetsBtcTopupFragment;
    AssetsBtcWithdrawalFragment assetsBtcWithdrawalFragment;

    public void initFragment() {
        viewDelegate.initAddFragment(R.id.fl_root, getChildFragmentManager());
        assetsBtcTopupFragment = new AssetsBtcTopupFragment();
        assetsBtcTopupFragment.setTopupAddress(assetInformation.getAccCode());
        assetsBtcWithdrawalFragment = new AssetsBtcWithdrawalFragment();
        viewDelegate.addFragment(assetsBtcTopupFragment);
        viewDelegate.addFragment(assetsBtcWithdrawalFragment);
        viewDelegate.showFragment(0);
    }

    @Override
    public AssetsBtcBinder getDataBinder(AssetsBtcDelegate viewDelegate) {
        return new AssetsBtcBinder(viewDelegate);
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                assetInformation = GsonUtil.getInstance().toObj(data, AssetInformation.class);
                initView();
                viewDelegate.viewHolder.swipeRefreshLayout.setRefreshing(false);
                if (assetsBtcTopupFragment == null && assetsBtcWithdrawalFragment == null) {
                    initFragment();
                } else {
                    assetsBtcTopupFragment.setTopupAddress(assetInformation.getAccCode());
                    assetsBtcWithdrawalFragment.setWithdrawFee(assetInformation.getWithdrawFee());
                }
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        addRequest(binder.acc(this));
    }

    @Override
    protected void onServiceError(String data, String info, int status, int requestCode) {
        super.onServiceError(data, info, status, requestCode);
        viewDelegate.viewHolder.swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected Class<AssetsBtcDelegate> getDelegateClass() {
        return AssetsBtcDelegate.class;
    }


}

package com.gqfbtc.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by 郭青枫 on 2017/9/26.
 */

public class SlidWithFragmentPagerAdapter<T extends Fragment> extends FragmentPagerAdapter {

    private String[] mTitles;
    private ArrayList<T> mFragments;


    public SlidWithFragmentPagerAdapter(FragmentManager fm, String[] mTitles, ArrayList<T> mFragments) {
        super(fm);
        this.mTitles=mTitles;
        this.mFragments=mFragments;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }
}
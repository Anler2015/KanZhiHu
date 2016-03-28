package com.gejiahui.kanzhihu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import java.util.List;

/**
 * Created by gejiahui on 2016/3/23.
 */
public class TabFragmentAdapter extends FragmentStatePagerAdapter {

    private List<String> mTitles;
    private List<Fragment> mFragments;

    public TabFragmentAdapter(FragmentManager fm, List<String> titles, List<Fragment> fragments) {
        super(fm);
        mTitles = titles;
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mTitles.size();

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}

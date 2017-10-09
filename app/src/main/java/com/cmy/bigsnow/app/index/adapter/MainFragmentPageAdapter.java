package com.cmy.bigsnow.app.index.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * The type Main fragment page adapter.
 *
 * @Author : mengyuan.cheng
 * @Version : 2017/7/17
 * @E-mail : mengyuan.cheng.mier@gmail.com
 * @Description :
 */
public class MainFragmentPageAdapter extends FragmentPagerAdapter {
    private ArrayList<String> titleList;
    private ArrayList<Fragment> fragmentList;

    /**
     * Instantiates a new Main fragment page adapter.
     *
     * @param fm           the fm
     * @param titleList    the title list
     * @param fragmentList the fragment list
     */
    public MainFragmentPageAdapter(FragmentManager fm,
                                   ArrayList<String> titleList,
                                   ArrayList<Fragment> fragmentList) {
        super(fm);
        this.titleList = titleList;
        this.fragmentList = fragmentList;
    }

    /**
     * Instantiates a new Main fragment page adapter.
     *
     * @param fm the fm
     */
    public MainFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (!titleList.isEmpty()) {
            return titleList.get(position);
        } else {
            return null;
        }
    }
}

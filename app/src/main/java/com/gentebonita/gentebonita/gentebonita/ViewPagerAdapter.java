package com.gentebonita.gentebonita.gentebonita;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Vinicius on 16/06/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments;
    public ViewPagerAdapter(FragmentManager fm,List<Fragment> f) {
        super(fm);
        this.fragments = f;
    }

    @Override
    public Fragment getItem(int arg0) {

        return fragments.get(arg0);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return fragments.size();
    }
}
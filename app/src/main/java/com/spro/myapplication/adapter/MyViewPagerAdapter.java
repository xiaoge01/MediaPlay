package com.spro.myapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.spro.myapplication.fragment.MyViewPagerFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/23.
 */

public class MyViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<String> namelist;
    public int position=-1;

    public MyViewPagerAdapter(FragmentManager fm, ArrayList<String> namelist) {
        super(fm);
        this.namelist = namelist;
    }


    @Override
    public int getCount() {
        return namelist.size();
    }


    @Override
    public Fragment getItem(int position) {


        return new MyViewPagerFragment(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return namelist.get(position);
    }
}

package com.abasscodes.myapplication;

import android.app.Activity;
import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by C4Q on 11/18/16.
 */
public class TabAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener{

    private final List<Fragment> fragments = new ArrayList<>();
    private final List<String> fragmentTitles = new ArrayList<>();
    private Callback callback;


    public TabAdapter(AppCompatActivity activity){
        super(activity.getSupportFragmentManager());
        try{
            this.callback = (Callback) activity;
        }catch (ClassCastException cce){
            throw new ClassCastException("Activity must implement TabAdapter.Callback");
        }
        callback.onTabFocus(0);
    }

    private TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void addFragment(Fragment fragment, String title) {
        fragments.add(fragment);
        fragmentTitles.add(title);
    }

    public void addFragment(int index, Fragment fragment, String title) {
        fragments.add(index, fragment);
        fragmentTitles.add(index, title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitles.get(position);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        callback.onTabFocus(position);
    }

    @Override
    public void onPageSelected(int position) {
        callback.onTabFocus(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    public interface Callback{
        void onTabFocus(int position);
    }
}

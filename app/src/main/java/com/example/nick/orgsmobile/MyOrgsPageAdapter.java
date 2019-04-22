package com.example.nick.orgsmobile;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyOrgsPageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public MyOrgsPageAdapter(FragmentManager fragmentManager, int numOfTabs) {
        super(fragmentManager);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MyOrgsDefault();
            case 1:
                return new MyOrgsApplyRecognition();
            case 2:
                return new MyOrgsActivityForms();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}

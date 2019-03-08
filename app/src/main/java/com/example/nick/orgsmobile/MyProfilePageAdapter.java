package com.example.nick.orgsmobile;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyProfilePageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public MyProfilePageAdapter(FragmentManager fragmentManager, int numOfTabs) {
        super(fragmentManager);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MyProfileFragmentPersonal();
            case 1:
                return new MyProfileFragmentContact();
            case 2:
                return new MyProfileFragmentFamily();
            case 3:
                return new MyProfileFragmentAdditional();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}

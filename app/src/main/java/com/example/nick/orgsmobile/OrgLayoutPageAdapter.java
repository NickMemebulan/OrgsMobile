package com.example.nick.orgsmobile;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class OrgLayoutPageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public OrgLayoutPageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new OrgLayoutFragmentPublic();
            case 1:
                return new OrgLayoutFragmentPrivate();
            case 2:
                return new OrgLayoutFragmentManage();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}

package com.example.nick.orgsmobile;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyOrgsActivity extends DrawerAndToolbarClass{

    private TabLayout tabLayout;
    private TabItem tabMyOrgs;
    private TabItem tabApplyRecog;
    private TabItem tabActForms;
    private ViewPager MyOrgsViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_orgs_activity);

        displayDrawer();

        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabMyOrgs = (TabItem)findViewById(R.id.tabMyOrgs);
        tabApplyRecog = (TabItem)findViewById(R.id.tabApplyRecog);
        tabActForms = (TabItem)findViewById(R.id.tabActForms);
        MyOrgsViewPager = (ViewPager)findViewById(R.id.myOrgsViewPager);

        MyOrgsPageAdapter pageAdapter = new MyOrgsPageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        MyOrgsViewPager.setAdapter(pageAdapter);
        MyOrgsViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                MyOrgsViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}

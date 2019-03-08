package com.example.nick.orgsmobile;

import android.content.Intent;
import android.support.annotation.NonNull;
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
import android.widget.ImageView;
import android.widget.Toast;

public class OrgLayoutActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private ImageView NavOpener;
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle toggler;
    private NavigationView navigationView;
    private TabLayout tabLayout;
    private TabItem orgPublicTab;
    private TabItem orgPrivateTab;
    private TabItem manageOrgTab;
    private ViewPager orgLayoutViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.org_layout_activity);

        NavOpener = (ImageView)findViewById(R.id.navOpener);

        mDrawer = (DrawerLayout)findViewById(R.id.drawer_layout);

        toggler = new ActionBarDrawerToggle(
                this, mDrawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggler);
        toggler.syncState();

        navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        NavOpener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawer.openDrawer(GravityCompat.START);
            }
        });

        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        orgPublicTab = (TabItem)findViewById(R.id.orgPublicTab);
        orgPrivateTab = (TabItem)findViewById(R.id.orgPrivateTab);
        manageOrgTab = (TabItem)findViewById(R.id.manageOrgTab);
        orgLayoutViewPager = (ViewPager)findViewById(R.id.orgLayoutViewPager);

        OrgLayoutPageAdapter pageAdapter = new OrgLayoutPageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        orgLayoutViewPager.setAdapter(pageAdapter);
        orgLayoutViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.navHome) {
            NavUtils.navigateUpFromSameTask(this);
        } else if (id == R.id.navMyOrgs){
            Intent myOrgsIntent = new Intent(OrgLayoutActivity.this, MyOrgsActivity.class);
            startActivity(myOrgsIntent);
        } else if (id == R.id.navRecognizedOrgs) {
            Intent recognizedOrgsIntent = new Intent(OrgLayoutActivity.this, RecognizedOrgsActivity.class);
            startActivity(recognizedOrgsIntent);
        } else if (id == R.id.navApplyForRecognition) {
            Intent applyForRecognitionIntent = new Intent(OrgLayoutActivity.this, OrgRecognitionActivity.class);
            startActivity(applyForRecognitionIntent);
        } else if (id == R.id.navRecognitionGuidelines) {
            Toast.makeText(OrgLayoutActivity.this, "" + item.getTitle(), Toast.LENGTH_SHORT).show();
            return true;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

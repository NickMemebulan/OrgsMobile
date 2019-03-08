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
import android.widget.ImageView;
import android.widget.Toast;

public class MyProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private ImageView NavOpener;
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle toggler;
    private NavigationView navigationView;
    private TabLayout tabLayout;
    private TabItem tabPersonal;
    private TabItem tabContact;
    private TabItem tabFamily;
    private TabItem tabAdditional;
    private ViewPager MyProfileViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_profile_activity);

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
        tabPersonal = (TabItem)findViewById(R.id.tabPersonal);
        tabContact = (TabItem)findViewById(R.id.tabContact);
        tabFamily = (TabItem)findViewById(R.id.tabFamily);
        tabAdditional = (TabItem)findViewById(R.id.tabAdditional);
        MyProfileViewPager = (ViewPager)findViewById(R.id.myProfileViewPager);

        MyProfilePageAdapter pageAdapter = new  MyProfilePageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        MyProfileViewPager.setAdapter(pageAdapter);
        MyProfileViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.navHome) {
            NavUtils.navigateUpFromSameTask(this);
        } else if (id == R.id.navMyOrgs){
            Intent myOrgsIntent = new Intent(MyProfileActivity.this, MyOrgsActivity.class);
            startActivity(myOrgsIntent);
        } else if (id == R.id.navRecognizedOrgs) {
            Intent recognizedOrgsIntent = new Intent(MyProfileActivity.this, RecognizedOrgsActivity.class);
            startActivity(recognizedOrgsIntent);
        } else if (id == R.id.navApplyForRecognition) {
            Intent applyForRecognitionIntent = new Intent(MyProfileActivity.this, OrgRecognitionActivity.class);
            startActivity(applyForRecognitionIntent);
        } else if (id == R.id.navRecognitionGuidelines) {
            Toast.makeText(MyProfileActivity.this, "" + item.getTitle(), Toast.LENGTH_SHORT).show();
            return true;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

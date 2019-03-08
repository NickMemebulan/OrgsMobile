package com.example.nick.orgsmobile;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GravityCompat;
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

public class MyOrgsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private ImageView NavOpener;
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle toggler;
    private NavigationView navigationView;

    private Button MyOrgs;
    private Button ApplyRecog;
    private Button ActForms;

    private TextView MyOrgsDesc;
    private TextView ApplyRecogDesc;
    private TextView ActFormsDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_orgs_activity);

        MyOrgs = (Button)findViewById(R.id.myOrgsButton);
        ApplyRecog = (Button)findViewById(R.id.applyRecogButton);
        ActForms = (Button)findViewById(R.id.actFormsButton);

        MyOrgsDesc = (TextView)findViewById(R.id.myOrgsDesc);
        ApplyRecogDesc = (TextView)findViewById(R.id.applyRecogDesc);
        ActFormsDesc = (TextView)findViewById(R.id.actFormsDesc);

        MyOrgs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyOrgsDesc.setVisibility(View.VISIBLE);
                ApplyRecogDesc.setVisibility(View.INVISIBLE);
                ActFormsDesc.setVisibility(View.INVISIBLE);
            }
        });

        ApplyRecog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyOrgsDesc.setVisibility(View.INVISIBLE);
                ApplyRecogDesc.setVisibility(View.VISIBLE);
                ActFormsDesc.setVisibility(View.INVISIBLE);
            }
        });

        ActForms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyOrgsDesc.setVisibility(View.INVISIBLE);
                ApplyRecogDesc.setVisibility(View.INVISIBLE);
                ActFormsDesc.setVisibility(View.VISIBLE);
            }
        });

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

        if (id == R.id.navMyProfile) {
            Intent myProfileIntent = new Intent(MyOrgsActivity.this, MyProfileActivity.class);
            startActivity(myProfileIntent);
        } else if (id == R.id.navHome){
            NavUtils.navigateUpFromSameTask(this);
        } else if (id == R.id.navRecognizedOrgs) {
            Intent recognizedOrgsIntent = new Intent(MyOrgsActivity.this, RecognizedOrgsActivity.class);
            startActivity(recognizedOrgsIntent);
        } else if (id == R.id.navApplyForRecognition) {
            Intent applyForRecognitionIntent = new Intent(MyOrgsActivity.this, OrgRecognitionActivity.class);
            startActivity(applyForRecognitionIntent);
        } else if (id == R.id.navRecognitionGuidelines) {
            Toast.makeText(MyOrgsActivity.this, "" + item.getTitle(), Toast.LENGTH_SHORT).show();
            return true;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

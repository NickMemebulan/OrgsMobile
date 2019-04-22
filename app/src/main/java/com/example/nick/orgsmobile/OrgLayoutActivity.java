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
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class OrgLayoutActivity extends DrawerAndToolbarClass{

    private TabLayout tabLayout;
    private TabItem orgPublicTab;
    private TabItem orgPrivateTab;
    private TabItem manageOrgTab;
    private ViewPagerDisabler orgLayoutViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.org_layout_activity);

        displayDrawer();

        /*tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        orgPublicTab = (TabItem)findViewById(R.id.orgPublicTab);
        orgPrivateTab = (TabItem)findViewById(R.id.orgPrivateTab);
        manageOrgTab = (TabItem)findViewById(R.id.manageOrgTab);
        orgLayoutViewPager = findViewById(R.id.orgLayoutViewPager);

        OrgLayoutPageAdapter pageAdapter = new OrgLayoutPageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        orgLayoutViewPager.setAdapter(pageAdapter);
        orgLayoutViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                orgLayoutViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/

        String orgid = getIntent().getStringExtra("orgid");
        getOrgProfile(orgid);

    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        String orgid = getIntent().getStringExtra("orgid");
        getOrgProfile(orgid);
    }

    protected void getOrgProfile(final String orgid){
        final JSONObject[] json = new JSONObject[1];
        final String[] pointperson = new String[1];
        String url = "https://uplbosa.org/apitest/org/profile";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest myJsonRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);
                        try {
                            json[0] = new JSONObject(response);
                            pointperson[0] = json[0].get("pointperson_id").toString();
                            /*Log.d("PointPerson", pointperson);
                            Log.d("UserID",AccountLoggedIn.getUserID());*/



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.d("Error.Response", error.toString());
                        Toast.makeText(OrgLayoutActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();

                    }
                }
        ){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("token","4p1t3st");
                params.put("orgid", orgid);
                return params;
            }

            @Override
            protected void onFinish() {
                if(AccountLoggedIn.getUserID()!=null && pointperson[0].equals(AccountLoggedIn.getUserID())){
                    tabLayout.setVisibility(View.VISIBLE);
                    orgLayoutViewPager.setSwipeDisabler(true);
                }
                ActiveOrganizationActivity.setActiveOrg(json[0]);

                CreateFragments();


            }
        };
        queue.add(myJsonRequest);
    }

    public void CreateFragments(){
        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        orgPublicTab = (TabItem)findViewById(R.id.orgPublicTab);
        orgPrivateTab = (TabItem)findViewById(R.id.orgPrivateTab);
        manageOrgTab = (TabItem)findViewById(R.id.manageOrgTab);
        orgLayoutViewPager = findViewById(R.id.orgLayoutViewPager);

        OrgLayoutPageAdapter pageAdapter = new OrgLayoutPageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        orgLayoutViewPager.setAdapter(pageAdapter);
        orgLayoutViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                orgLayoutViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /*@Override
    protected void onResume() {
        super.onResume();
        String orgid = null;
        if(ActiveOrganizationActivity.getActiveOrg() != null){
            try {
                orgid = ActiveOrganizationActivity.getActiveOrg().get("orgid").toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            getOrgProfile(orgid);
        }
    }*/
}

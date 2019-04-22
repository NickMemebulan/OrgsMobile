package com.example.nick.orgsmobile;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.w3c.dom.Text;

public class TemplateEventLayout extends DrawerAndToolbarClass {

    OrganizationEvents event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template_event_layout);

        displayDrawer();
        event = (OrganizationEvents) getIntent().getSerializableExtra("event");

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        final ViewPager eventViewPager = findViewById(R.id.eventViewPager);

        EventPageAdapter pageAdapter = new EventPageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        eventViewPager.setAdapter(pageAdapter);
        eventViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                eventViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public OrganizationEvents getEvent(){
        return event;
    }
}

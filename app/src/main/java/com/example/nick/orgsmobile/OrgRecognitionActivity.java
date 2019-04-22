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
import android.widget.Toast;

public class OrgRecognitionActivity extends DrawerAndToolbarClass{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.org_recognition_activity);

        displayDrawer();

        Button registerOrgButton = findViewById(R.id.registerOrgButton);
        registerOrgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OrgRecognitionActivity.this, "Not yet Implemented", Toast.LENGTH_LONG).show();
            }
        });

        Button applyRecognitionButton = findViewById(R.id.applyForRecognition);
        applyRecognitionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OrgRecognitionActivity.this, "Not yet Implemented", Toast.LENGTH_LONG).show();
            }
        });
    }
}

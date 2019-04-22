package com.example.nick.orgsmobile;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends DrawerAndToolbarClass{

    private RecyclerView homeRecyclerView;
    private RecyclerView.Adapter homeAdapter;
    private RecyclerView.LayoutManager homeLayoutManager;
    private int numberOfSampleEvents = 10;
    private ArrayList<OrganizationEvents> organizationEvents = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //System.out.println("Successful launch!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        displayDrawer();

        OrganizationEvents engmeet = new OrganizationEvents(
                "Engineering Meet 2019",
                "UP ERG - LB",
                "April 8, 2019, 7:00am – April 23, 2019, 9:00pm",
                "SU Mural",
                "To promote socio-cultural consciousness through the organization's craft-- photography.",
                R.drawable.uplb_erg_eng_meet_2019
        );
        organizationEvents.add(engmeet);
        OrganizationEvents mulat = new OrganizationEvents(
                "MULAT: A Photography Exhibit",
                "UP PHOTOS",
                "April 8, 2019, 7:00am – April 23, 2019, 9:00pm",
                "SU Mural",
                "To promote socio-cultural consciousness through the organization's craft-- photography.",
                R.drawable.up_photos_mulat_2019
        );
        organizationEvents.add(mulat);
        OrganizationEvents labulabo = new OrganizationEvents(
                "LabuLabo 2019",
                "GRANGE",
                "Monday, April 22⋅7:00 – 9:00pm",
                "Electrical Engineering Auditorium",
                "To showcase the talent of the members and associate relevant issues through the production and quiz contest",
                R.drawable.uplb_grange_labu_labo_2019
        );
        organizationEvents.add(labulabo);
        OrganizationEvents ekonsehan = new OrganizationEvents(
                "EKONSEHAN",
                "ECONSOC",
                "Thursday, April 25⋅7:00 – 9:00pm",
                "IWEPLH",
                "To promote academic awareness among students",
                R.drawable.econsoc_ekonsehan_2019
        );
        organizationEvents.add(ekonsehan);

        homeRecyclerView = (RecyclerView) findViewById(R.id.homeRecyclerView);
        homeRecyclerView.setHasFixedSize(true);
        homeLayoutManager = new LinearLayoutManager(this);
        homeRecyclerView.setLayoutManager(homeLayoutManager);
        int numberOfOrgEvents = organizationEvents.size();
        homeAdapter = new RecyclerEventAdapter(numberOfOrgEvents, organizationEvents);
        homeRecyclerView.setAdapter(homeAdapter);
    }
}

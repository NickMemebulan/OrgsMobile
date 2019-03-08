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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private ImageView NavOpener;
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle toggler;
    private NavigationView navigationView;

    private ImageView navHeaderIcon;
    private TextView navHeaderNickName;
    private Button navSignInButton;

    private RecyclerView homeRecyclerView;
    private RecyclerView.Adapter homeAdapter;
    private RecyclerView.LayoutManager homeLayoutManager;
    private int numberOfSampleEvents = 30;

    private GoogleSignInClient mGoogleSignInClient;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 111; // google sign in request code


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Successful launch!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        homeRecyclerView = (RecyclerView) findViewById(R.id.homeRecyclerView);
        homeRecyclerView.setHasFixedSize(true);

        homeLayoutManager = new LinearLayoutManager(this);
        homeRecyclerView.setLayoutManager(homeLayoutManager);

        homeAdapter = new RecyclerEventAdapter(numberOfSampleEvents);
        homeRecyclerView.setAdapter(homeAdapter);

        NavOpener = (ImageView)findViewById(R.id.navOpener);

        mDrawer = (DrawerLayout)findViewById(R.id.drawer_layout);

        toggler = new ActionBarDrawerToggle(
                this, mDrawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggler);
        toggler.syncState();

        navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

       View navView = navigationView.getHeaderView(0);

        navHeaderIcon = (ImageView) navView.findViewById(R.id.nav_header_icon);
        navHeaderNickName = (TextView) navView.findViewById(R.id.nav_header_nickname);
        navSignInButton = (Button) navView.findViewById(R.id.nav_sign_in_button);

        NavOpener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawer.openDrawer(GravityCompat.START);
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    @Override
    protected void onStart(){
        System.out.println("Successful start!");
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        getProfileInformation(account);
    }

    public void customGoogleSignIn(View ivew){
        doSignInSignOut();
    }

    public void doSignInSignOut(){
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        if (account == null)
            doGoogleSignIn();
        else
            doGoogleSignOut();
    }

    public void doGoogleSignIn(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try{
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            getProfileInformation(account);

            Toast.makeText(this, "Google Sign In Successful.", Toast.LENGTH_SHORT).show();

        }catch (ApiException e){
            Log.e(TAG, "signInResult:failed code = " + e.getStatusCode());

            Toast.makeText(this, "Failed to do Sign In : " + e.getStatusCode(), Toast.LENGTH_SHORT).show();

            getProfileInformation(null);
        }
    }

    private void getProfileInformation(GoogleSignInAccount acct) {
        //if account is not null fetch the information
        if (acct != null) {

            //user display name
            String personName = acct.getDisplayName();

            //user first name
            String personGivenName = acct.getGivenName();

            //user last name
            String personFamilyName = acct.getFamilyName();

            //user email id
            String personEmail = acct.getEmail();

            //user unique id
            String personId = acct.getId();

            //user profile pic
            Uri personPhoto = acct.getPhotoUrl();

            //show the user details
            navHeaderNickName.setText(personGivenName);

            //show the user profile pic
            //Picasso.with(this).load(personPhoto).fit().placeholder(R.mipmap.ic_launcher_round).into(userProfileImageView);

            //change the text of Custom Sign in button to sign out
            navSignInButton.setText(getResources().getString(R.string.sign_out));

            //show the label and image view
            //userDetailLabel.setVisibility(View.VISIBLE);
            //userProfileImageView.setVisibility(View.VISIBLE);

        } else {

            //if account is null change the text back to Sign In and hide the label and image view
            navSignInButton.setText(R.string.sign_in);
            navHeaderNickName.setText(R.string.nickname);
            //userProfileImageView.setVisibility(View.GONE);

        }
    }

    private void doGoogleSignOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(MainActivity.this, "Google Sign Out done.", Toast.LENGTH_SHORT).show();
                        revokeAccess();
                    }
                });
    }

    private void revokeAccess() {
        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(MainActivity.this, "Google access revoked.", Toast.LENGTH_SHORT).show();
                        getProfileInformation(null);
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
            Intent myProfileIntent = new Intent(MainActivity.this, MyProfileActivity.class);
            startActivity(myProfileIntent);
        } else if (id == R.id.navMyOrgs){
            Intent myOrgsIntent = new Intent(MainActivity.this, MyOrgsActivity.class);
            startActivity(myOrgsIntent);
        } else if (id == R.id.navRecognizedOrgs) {
            Intent recognizedOrgsIntent = new Intent(MainActivity.this, RecognizedOrgsActivity.class);
            startActivity(recognizedOrgsIntent);
        } else if (id == R.id.navApplyForRecognition) {
            Intent applyForRecognitionIntent = new Intent(MainActivity.this, OrgRecognitionActivity.class);
            startActivity(applyForRecognitionIntent);
        } else if (id == R.id.navRecognitionGuidelines) {
            /*Toast.makeText(MainActivity.this, "" + item.getTitle(), Toast.LENGTH_SHORT).show();
            return true;*/
            Intent orgLayoutActivityIntent = new Intent(MainActivity.this, OrgLayoutActivity.class);
            startActivity(orgLayoutActivityIntent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

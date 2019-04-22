package com.example.nick.orgsmobile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DrawerAndToolbarClass extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    private ImageView navHeaderIcon;
    private TextView navHeaderNickName;
    private Button navSignInButton;
    MenuItem myProfile;
    MenuItem myOrgs;
    MenuItem myQRCode;


    private GoogleSignInClient mGoogleSignInClient;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 111; // google sign in request code

    /*void displayToolbar() {
        Toolbar toolbar = (Toolbar)findViewById(R.id.trial_toolbar);
        setSupportActionBar(toolbar);
    }*/

    void displayDrawer() {
        /*Toolbar toolbar = (Toolbar)findViewById(R.id.trial_toolbar);
        setSupportActionBar(toolbar);*/

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        ImageView NavOpener = (ImageView)findViewById(R.id.navOpener);
        NavOpener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View navView = navigationView.getHeaderView(0);
        navHeaderIcon = (ImageView) navView.findViewById(R.id.nav_header_icon);
        navHeaderNickName = (TextView) navView.findViewById(R.id.nav_header_nickname);
        navSignInButton = (Button) navView.findViewById(R.id.nav_sign_in_button);

        Menu menu = navigationView.getMenu();
        myProfile = menu.findItem(R.id.navMyProfile);
        myOrgs = menu.findItem(R.id.navMyOrgs);
        myQRCode = menu.findItem(R.id.navMyQRCode);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        //getProfileInformation(account);
        /*TODO: Fix this code*/
        if(account!=null){
            performLogin(account.getEmail());
        }
    }

    public void customGoogleSignIn(View view){
        doSignInSignOut();
    }

    public void doSignInSignOut(){
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        if (account == null)
            doGoogleSignIn();
        else
            doGoogleSignOut("Sign out successful.");
    }

    public void doGoogleSignIn(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void doGoogleSignOut(final String toastMessage) {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(DrawerAndToolbarClass.this, toastMessage, Toast.LENGTH_SHORT).show();
                        navSignInButton.setText(getResources().getString(R.string.sign_in));
                        myProfile.setVisible(false);
                        myOrgs.setVisible(false);
                        myQRCode.setVisible(false);
                        navHeaderNickName.setVisibility(View.GONE);
                        navHeaderIcon.setVisibility(View.GONE);
                        AccountLoggedIn.clearAccountLoggedIn();
                        restartApp();
                    }
                });
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


        }catch (ApiException e){
            Log.e(TAG, "signInResult:failed code = " + e.getStatusCode());

            Toast.makeText(this, "Failed to do Sign In", Toast.LENGTH_SHORT).show();

            getProfileInformation(null);
        }
    }

    private void getProfileInformation(GoogleSignInAccount acct) {
        //if account is not null fetch the information
        if (acct != null) {

            if(!isUPMail(acct.getEmail())){
                doGoogleSignOut("Please use your UP Mail to Sign In.");
            }
            else{
                Toast.makeText(DrawerAndToolbarClass.this, "Sign In Successful.", Toast.LENGTH_SHORT).show();
                performLogin(acct.getEmail());
            }

        } else {

            navSignInButton.setText(R.string.sign_in);

        }
    }

    private boolean isUPMail(String gmail){
        String regex = ".+(\\@up\\.edu\\.ph)$";
        if(gmail.matches(regex)){
            return true;
        }else{
            return false;
        }
    }

    private void performLogin(final String upMail){
        String url = "https://uplbosa.org/apitest/student/profile";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest myJsonRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.d("Response", response);
                        try {
                            JSONObject json = new JSONObject(response);
                            navHeaderNickName.setText(json.get("username").toString());
                            setStudentProfile(json);
                        } catch (JSONException e) {
                            doGoogleSignOut("No OSAM Profile, Proceed to Student Union Building 2F, Room 7");
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(DrawerAndToolbarClass.this, "Please connect to Internet", Toast.LENGTH_LONG).show();
                        Log.d("Error.Response", error.toString());
                    }
                }
        ){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("token","4p1t3st");
                params.put("student_no", "");
                params.put("upmail", upMail);
                return params;
            }

            @Override
            protected void onFinish() {
                navSignInButton.setText(getResources().getString(R.string.sign_out));
                navHeaderNickName.setVisibility(View.VISIBLE);
                navHeaderIcon.setVisibility(View.VISIBLE);
                myProfile.setVisible(true);
                myOrgs.setVisible(true);
                myQRCode.setVisible(true);
                getNavHeaderIcon(AccountLoggedIn.getStudentNo());

                //Toast.makeText(DrawerAndToolbarClass.this, "Sign In Successful.", Toast.LENGTH_SHORT).show();
            }
        };

        myJsonRequest.setRetryPolicy(new DefaultRetryPolicy(10000, 1, 1.0f));
        queue.add(myJsonRequest);
    }

    private void getNavHeaderIcon(final String student_no){
        final Bitmap[] decoded = new Bitmap[1];
        String url = "https://uplbosa.org/apitest/student/photo";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest myJsonRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.d("Response", response);
                        String pureBase64Encoded = response.substring(response.indexOf(",") + 1);
                        byte[] decodeString = Base64.decode(pureBase64Encoded, Base64.DEFAULT);
                        AccountLoggedIn.setStudentPhotoDecodeString(decodeString);
                        decoded[0] = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.d("Error.Response", error.toString());
                    }
                }
        ){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("token","4p1t3st");
                params.put("student_no", student_no);
                //params.put("upmail", "bisimbulan@up.edu.ph");
                return params;
            }

            @Override
            protected void onFinish() {
                navHeaderIcon.setImageBitmap(decoded[0]);
            }
        };
        queue.add(myJsonRequest);
    }

    private void setStudentProfile(JSONObject json) throws JSONException {
        AccountLoggedIn.setUserID(json.get("userid").toString());
        Log.d("Logged in user id", AccountLoggedIn.getUserID());
        /*Set Profile Header*/
        AccountLoggedIn.setName(json.get("name").toString());
        AccountLoggedIn.setStudentNo(json.get("student_no").toString());
        AccountLoggedIn.setCourse(json.get("course").toString());
        AccountLoggedIn.setCollege(json.get("college").toString());
        /*Set Profile Personal*/
        AccountLoggedIn.setNickname(json.get("nickname").toString());
        AccountLoggedIn.setTalents(json.get("talents").toString());
        AccountLoggedIn.setReligion(json.get("religion").toString());
        AccountLoggedIn.setNationality(json.get("nationality").toString());
        AccountLoggedIn.setBirthplace(json.get("birthplace").toString());
        AccountLoggedIn.setMarital(json.get("marital_status").toString());
        AccountLoggedIn.setSex(json.get("sex").toString());
        AccountLoggedIn.setBlood(json.get("bloodtype").toString());
        /*Set Profile Contact;*/
        AccountLoggedIn.setCollAdd(json.get("address_college").toString());
        AccountLoggedIn.setPermAdd(json.get("address_permanent").toString());
        AccountLoggedIn.setRegion(json.get("region").toString());
        AccountLoggedIn.setMobDevice(json.get("mobile_device").toString());
        AccountLoggedIn.setMobNum(json.get("mobile_no").toString());
        AccountLoggedIn.setTelNum(json.get("landline").toString());
        AccountLoggedIn.setContactPersonName(json.get("ice").toString());
        AccountLoggedIn.setContactPersonNum(json.get("ice_no").toString());
        AccountLoggedIn.setEmailAdd(json.get("email").toString());
        AccountLoggedIn.setFbUserId(json.get("facebookid").toString());
        AccountLoggedIn.setTwitterUser(json.get("twitterid").toString());
        /*Set Profile Family*/
        AccountLoggedIn.setMotherName(json.get("mother_name").toString());
        AccountLoggedIn.setMotherEduc(json.get("mother_educ").toString());
        AccountLoggedIn.setMotherWork(json.get("mother_work").toString());
        AccountLoggedIn.setMotherBday(json.get("mother_bday").toString());
        AccountLoggedIn.setFatherName(json.get("father_name").toString());
        AccountLoggedIn.setFatherEduc(json.get("father_educ").toString());
        AccountLoggedIn.setFatherWork(json.get("father_work").toString());
        AccountLoggedIn.setFatherBday(json.get("father_bday").toString());
        AccountLoggedIn.setNumSiblings(json.get("siblings_total").toString());
        /*Set Profile Additional*/
        AccountLoggedIn.setPrevCollege(json.get("prev_college").toString());
        AccountLoggedIn.setPrevHighSchool(json.get("prev_highschool").toString());
        AccountLoggedIn.setPrevElementary(json.get("prev_elementary").toString());
        AccountLoggedIn.setPassport(json.get("passport").toString());
        AccountLoggedIn.setPassportExp(json.get("passport_exp").toString());
        AccountLoggedIn.setVisa(json.get("visa").toString());
        AccountLoggedIn.setVisaExp(json.get("visa_exp").toString());
        AccountLoggedIn.setEmployer(json.get("employer").toString());
        AccountLoggedIn.setJobTitle(json.get("job_title").toString());
        /*Set My Orgs*/
        getMyOrgs(AccountLoggedIn.getStudentNo());
    }

    private void getMyOrgs(final String userid){
        String url = "https://uplbosa.org/apitest/student/orgs";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest myJsonRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.d("Get My Orgs", response);
                        try {
                            JSONArray myOrgsArray =new JSONArray(response);
                            AccountLoggedIn.setMyOrgs(myOrgsArray);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.d("Error.Response", error.toString());
                    }
                }
        ){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("token","4p1t3st");
                params.put("student_no", userid);
                return params;
            }
        };
        queue.add(myJsonRequest);
    }


    private void restartApp(){
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    private void revokeAccess() {
        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(DrawerAndToolbarClass.this, "Google access revoked.", Toast.LENGTH_SHORT).show();
                        //getProfileInformation(null);
                        navSignInButton.setText(getResources().getString(R.string.sign_in));
                        navHeaderNickName.setText(getResources().getString(R.string.nickname));
                    }
                });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.navHome) {
            System.out.println(getClass().getSimpleName());
            if(getClass().getSimpleName().equals("MainActivity")){
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
            NavUtils.navigateUpFromSameTask(this);
        } else if (id == R.id.navMyProfile) {
            Intent myProfileIntent = new Intent(this, MyProfileActivity.class);
            startActivity(myProfileIntent);
        } else if (id == R.id.navMyOrgs){
            Intent myOrgsIntent = new Intent(this, MyOrgsActivity.class);
            startActivity(myOrgsIntent);
        } else if (id == R.id.navMyQRCode){
            Intent myQRCodeIntent = new Intent(this, MyQRCodeActivity.class);
            startActivity(myQRCodeIntent);
        }else if (id == R.id.navRecognizedOrgs) {
            Intent recognizedOrgsIntent = new Intent(this, RecognizedOrgsActivity.class);
            startActivity(recognizedOrgsIntent);
        } else if (id == R.id.navApplyForRecognition) {
            Intent applyForRecognitionIntent = new Intent(this, OrgRecognitionActivity.class);
            startActivity(applyForRecognitionIntent);
        } else if (id == R.id.navRecognitionGuidelines) {
            /*Toast.makeText(MainActivity.this, "" + item.getTitle(), Toast.LENGTH_SHORT).show();
            return true;*/
            Intent orgLayoutActivityIntent = new Intent(this, OrgLayoutActivity.class);
            orgLayoutActivityIntent.putExtra("orgid","60");
            startActivity(orgLayoutActivityIntent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

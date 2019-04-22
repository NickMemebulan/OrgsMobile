package com.example.nick.orgsmobile;

import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import java.util.Map;

public class ManageUpdateOrgProfile extends DrawerAndToolbarClass {

    EditText shortName;
    EditText otherNickNames;
    EditText orgType;
    EditText collegeAssignment;
    EditText dateEstablished;
    EditText secRegistration;
    EditText orgEmail;
    EditText mailingAddress;
    EditText tambayanAddress;
    EditText gaSchedule;
    EditText websiteLink;
    EditText youtubeLink;
    EditText facebookUsername;
    EditText twitterUsername;
    EditText mission;
    EditText vision;
    EditText objectives;
    EditText briefDescription;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_update_org_profile);

        displayDrawer();

        shortName = findViewById(R.id.updateShortName);
        otherNickNames = findViewById(R.id.updateOtherNicknames);
        orgType = findViewById(R.id.updateOrgType);
        collegeAssignment = findViewById(R.id.updateCollegeAssignment);
        dateEstablished = findViewById(R.id.updateDateEstablished);
        secRegistration = findViewById(R.id.updateSecRegistration);
        orgEmail = findViewById(R.id.updateOrgEmail);
        mailingAddress = findViewById(R.id.updateMailingAddress);
        tambayanAddress = findViewById(R.id.updateTambayanAddress);
        gaSchedule = findViewById(R.id.updateGASched);
        websiteLink = findViewById(R.id.updateWebsite);
        youtubeLink = findViewById(R.id.updateYoutube);
        facebookUsername = findViewById(R.id.updateFacebook);
        twitterUsername = findViewById(R.id.updateTwitter);
        mission = findViewById(R.id.updateMission);
        vision = findViewById(R.id.updateVision);
        objectives = findViewById(R.id.updateObjectives);
        briefDescription = findViewById(R.id.updateBriefDescription);
        saveButton = findViewById(R.id.updateSaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    updateOrgProfile();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        try {
            initializeUpdateOrgProfile();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void initializeUpdateOrgProfile() throws JSONException {
        JSONObject activeOrg = ActiveOrganizationActivity.getActiveOrg();

        shortName.setText(activeOrg.get("acronym").toString());
        otherNickNames.setText(activeOrg.get("nicknames").toString());
        orgType.setText(activeOrg.get("type").toString());
        collegeAssignment.setText(activeOrg.get("college").toString());
        dateEstablished.setText("Organization established on " + activeOrg.get("date_established").toString());
        secRegistration.setText(activeOrg.get("sec").toString());
        orgEmail.setText(activeOrg.get("email").toString());
        mailingAddress.setText(activeOrg.get("address").toString());
        tambayanAddress.setText(activeOrg.get("tambayan").toString());
        gaSchedule.setText(activeOrg.get("gasched").toString());
        websiteLink.setText(activeOrg.get("website").toString());
        youtubeLink.setText(activeOrg.get("youtube").toString());
        facebookUsername.setText(activeOrg.get("facebook").toString());
        twitterUsername.setText(activeOrg.get("twitter").toString());
        mission.setText(activeOrg.get("mission").toString());
        vision.setText(activeOrg.get("vision").toString());
        objectives.setText(activeOrg.get("objectives").toString());
        briefDescription.setText(activeOrg.get("description").toString());
    }

    public void updateOrgProfile() throws JSONException {
        JSONObject activeOrg = ActiveOrganizationActivity.getActiveOrg();
        final String orgid = activeOrg.get("orgid").toString();
        String url = "https://uplbosa.org/apitest/org/update";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest myJsonRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);
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
                params.put("orgid", orgid);
                params.put("acronym",shortName.getText().toString());
                params.put("nicknames",otherNickNames.getText().toString());
                params.put("type",orgType.getText().toString());
                params.put("college", collegeAssignment.getText().toString());
                params.put("date_established", dateEstablished.getText().toString());
                params.put("sec", secRegistration.getText().toString());
                params.put("email",orgEmail.getText().toString());
                params.put("address",mailingAddress.getText().toString());
                params.put("tambayan",tambayanAddress.getText().toString());
                params.put("gasched", gaSchedule.getText().toString());
                params.put("website",websiteLink.getText().toString());
                params.put("youtube",youtubeLink.getText().toString());
                params.put("facebook",facebookUsername.getText().toString());
                params.put("twitter",twitterUsername.getText().toString());
                params.put("mission",mission.getText().toString());
                params.put("vision",vision.getText().toString());
                params.put("objectives",objectives.getText().toString());
                params.put("description",briefDescription.getText().toString());
                return params;
            }

            @Override
            protected void onFinish() {
                super.onFinish();
                Toast.makeText(ManageUpdateOrgProfile.this, "Organization Profile Updated Successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        };
        queue.add(myJsonRequest);
    }
}

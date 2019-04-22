package com.example.nick.orgsmobile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyProfileFragmentContact extends Fragment {

    static View myContactView;
    static EditText collegeAddress;
    static EditText permanentAddress;
    static EditText region;
    static EditText mobileDevice;
    static EditText mobileNumber;
    static EditText telNumber;
    static EditText contactPersonName;
    static EditText contactPersonNumber;
    static EditText emailAddress;
    static EditText facebookUserId;
    static EditText twitterUsername;


    public MyProfileFragmentContact() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myContactView =  inflater.inflate(R.layout.my_profile_contact, container, false);

        collegeAddress = myContactView.findViewById(R.id.contactCollegeAddress);
        permanentAddress = myContactView.findViewById(R.id.contactPermanentAddress);
        region = myContactView.findViewById(R.id.contactRegion);
        mobileDevice = myContactView.findViewById(R.id.contactMobileDevice);
        mobileNumber = myContactView.findViewById(R.id.contactMobileNumber);
        telNumber = myContactView.findViewById(R.id.contactTelNumber);
        contactPersonName = myContactView.findViewById(R.id.contactConPersonName);
        contactPersonNumber = myContactView.findViewById(R.id.contactMobileNumber);
        emailAddress = myContactView.findViewById(R.id.contactEmailAdd);
        facebookUserId = myContactView.findViewById(R.id.contactFbUserId);
        twitterUsername = myContactView.findViewById(R.id.contactTwitterUser);

        setContactFields();

        return myContactView;
    }

    public void setContactFields(){
        collegeAddress.setText(AccountLoggedIn.getCollAdd());
        permanentAddress.setText(AccountLoggedIn.getPermAdd());
        region.setText(AccountLoggedIn.getRegion());
        mobileDevice.setText(AccountLoggedIn.getMobDevice());
        mobileNumber.setText(AccountLoggedIn.getMobNum());
        telNumber.setText(AccountLoggedIn.getTelNum());
        contactPersonName.setText(AccountLoggedIn.getConntactPersonName());
        contactPersonNumber.setText(AccountLoggedIn.getContactPersonNum());
        emailAddress.setText(AccountLoggedIn.getEmailAdd());
        facebookUserId.setText(AccountLoggedIn.getFbUserId());
        twitterUsername.setText(AccountLoggedIn.getTwitterUserId());
    }

    public static void updateContactFields(){
        String url = "https://uplbosa.org/apitest/student/update";
        RequestQueue queue = Volley.newRequestQueue(myContactView.getContext());
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
                params.put("student_no",AccountLoggedIn.getStudentNo());
                params.put("address_college",collegeAddress.getText().toString());
                params.put("address_permanent",permanentAddress.getText().toString());
                params.put("region",region.getText().toString());
                params.put("mobile_device",mobileDevice.getText().toString());
                params.put("mobile_no",mobileNumber.getText().toString());
                params.put("landline",telNumber.getText().toString());
                params.put("ice",contactPersonName.getText().toString());
                params.put("ice_no",contactPersonNumber.getText().toString());
                params.put("email",emailAddress.getText().toString());
                params.put("facebookid",facebookUserId.getText().toString());
                params.put("twitterid",twitterUsername.getText().toString());
                return params;
            }

            @Override
            protected void onFinish() {
                super.onFinish();
                Log.d("Contact", "Complete");
            }
        };
        queue.add(myJsonRequest);
    }

}

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
public class MyProfileFragmentAdditional extends Fragment {

    static View myAdditionalView;
    static EditText prevCollege;
    static EditText prevHighSchool;
    static EditText prevElementary;
    static EditText passport;
    static EditText passportExp;
    static EditText visa;
    static EditText visaExp;
    static EditText employer;
    static EditText jobTitle;


    public MyProfileFragmentAdditional() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myAdditionalView = inflater.inflate(R.layout.my_profile_additional, container, false);

        prevCollege = myAdditionalView.findViewById(R.id.prevCollege);
        prevHighSchool = myAdditionalView.findViewById(R.id.prevHighSchool);
        prevElementary = myAdditionalView.findViewById(R.id.prevElementary);
        passport = myAdditionalView.findViewById(R.id.passport);
        passportExp = myAdditionalView.findViewById(R.id.passportExp);
        visa = myAdditionalView.findViewById(R.id.visa);
        visaExp = myAdditionalView.findViewById(R.id.visaExp);
        employer = myAdditionalView.findViewById(R.id.employer);
        jobTitle = myAdditionalView.findViewById(R.id.jobTitle);

        setAdditionalFields();

        return myAdditionalView;
    }

    public void setAdditionalFields(){
        prevCollege.setText(AccountLoggedIn.getPrevCollege());
        prevHighSchool.setText(AccountLoggedIn.getPrevHighSchool());
        prevElementary.setText(AccountLoggedIn.getPrevElementary());
        passport.setText(AccountLoggedIn.getPassport());
        passportExp.setText(AccountLoggedIn.getPassportExp());
        visa.setText(AccountLoggedIn.getVisa());
        visaExp.setText(AccountLoggedIn.getVisaExp());
        employer.setText(AccountLoggedIn.getEmployer());
        jobTitle.setText(AccountLoggedIn.getJobTitle());
    }


    public static void updateAdditionalFields(){
        String url = "https://uplbosa.org/apitest/student/update";
        RequestQueue queue = Volley.newRequestQueue(myAdditionalView.getContext());
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
                params.put("prev_college",prevCollege.getText().toString());
                params.put("prev_highschool",prevHighSchool.getText().toString());
                params.put("prev_elementary",prevElementary.getText().toString());
                params.put("passport",passport.getText().toString());
                params.put("passport_exp",passportExp.getText().toString());
                params.put("visa",visa.getText().toString());
                params.put("visa_exp",visaExp.getText().toString());
                params.put("employer",employer.getText().toString());
                params.put("job_title",jobTitle.getText().toString());
                return params;
            }

            @Override
            protected void onFinish() {
                super.onFinish();
                Log.d("Contact", "Hundred Percent");
            }
        };
        queue.add(myJsonRequest);
    }
}

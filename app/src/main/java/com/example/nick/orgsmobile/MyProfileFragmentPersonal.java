package com.example.nick.orgsmobile;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
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
public class MyProfileFragmentPersonal extends Fragment {

    static View myPersonalView;
    static EditText etNickname;
    static EditText etTalents;
    static EditText etReligion;
    static EditText etNationality;
    static EditText etBirthplace;
    static EditText etMarital;
    static EditText etSex;
    static EditText etBlood;

    public MyProfileFragmentPersonal() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        myPersonalView =  inflater.inflate(R.layout.my_profile_personal, container, false);

        etNickname = myPersonalView.findViewById(R.id.personalNickname);
        etTalents = myPersonalView.findViewById(R.id.personalTalents);
        etReligion = myPersonalView.findViewById(R.id.personalReligion);
        etNationality = myPersonalView.findViewById(R.id.personalNationality);
        etBirthplace = myPersonalView.findViewById(R.id.personalBirthplace);
        etMarital = myPersonalView.findViewById(R.id.personalMarital);
        etSex = myPersonalView.findViewById(R.id.personalSex);
        etBlood = myPersonalView.findViewById(R.id.personalBlood);

        setPersonalFields();

        return myPersonalView;
    }

    public static void setPersonalFields(){
        etNickname.setText(AccountLoggedIn.getNickname());
        etTalents.setText(AccountLoggedIn.getTalents());
        etReligion.setText(AccountLoggedIn.getReligion());
        etNationality.setText(AccountLoggedIn.getNationality());
        etBirthplace.setText(AccountLoggedIn.getBirthplace());
        etMarital.setText(AccountLoggedIn.getMarital());
        etSex.setText(AccountLoggedIn.getSex());
        etBlood.setText(AccountLoggedIn.getBlood());
        //Log.d("Nickname", etNickname.getText().toString());
    }

    public static void updatePersonalFields(){
        String url = "https://uplbosa.org/apitest/student/update";
        RequestQueue queue = Volley.newRequestQueue(myPersonalView.getContext());
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
                params.put("student_no", AccountLoggedIn.getStudentNo());
                params.put("nickname",etNickname.getText().toString());
                params.put("talents",etTalents.getText().toString());
                params.put("religion",etReligion.getText().toString());
                params.put("nationality",etNationality.getText().toString());
                params.put("birthplace",etBirthplace.getText().toString());
                params.put("marital_status",etMarital.getText().toString());
                params.put("sex",etSex.getText().toString());
                params.put("bloodtype",etBlood.getText().toString());
                return params;
            }

            @Override
            protected void onFinish() {
                super.onFinish();
                Log.d("Personal", "Updated");
            }
        };
        myJsonRequest.setRetryPolicy(new DefaultRetryPolicy(10000, 1, 1.0f));
        queue.add(myJsonRequest);
    }

}

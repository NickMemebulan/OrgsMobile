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
public class  MyProfileFragmentFamily extends Fragment {

    static View myFamilyView;
    static EditText motherName;
    static EditText motherEduc;
    static EditText motherWork;
    static EditText motherBday;
    static EditText fatherName;
    static EditText fatherEduc;
    static EditText fatherWork;
    static EditText fatherBday;
    static EditText totalSiblings;

    public MyProfileFragmentFamily() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFamilyView =  inflater.inflate(R.layout.my_profile_family, container, false);
        motherName = myFamilyView.findViewById(R.id.motherName);
        motherEduc = myFamilyView.findViewById(R.id.motherEduc);
        motherWork = myFamilyView.findViewById(R.id.motherWork);
        motherBday = myFamilyView.findViewById(R.id.motherBday);
        fatherName = myFamilyView.findViewById(R.id.fatherName);
        fatherEduc = myFamilyView.findViewById(R.id.fatherEduc);
        fatherWork = myFamilyView.findViewById(R.id.fatherWork);
        fatherBday = myFamilyView.findViewById(R.id.fatherBday);
        totalSiblings = myFamilyView.findViewById(R.id.totalSiblings);

        setFamilyFields();

        return myFamilyView;
    }

    public void setFamilyFields(){
        motherName.setText(AccountLoggedIn.getMotherName());
        motherEduc.setText(AccountLoggedIn.getMotherEduc());
        motherWork.setText(AccountLoggedIn.getMotherWork());
        motherBday.setText(AccountLoggedIn.getMotherBday());
        fatherName.setText(AccountLoggedIn.getFatherName());
        fatherEduc.setText(AccountLoggedIn.getFatherEduc());
        fatherWork.setText(AccountLoggedIn.getFatherWork());
        fatherBday.setText(AccountLoggedIn.getFatherBday());
        totalSiblings.setText(AccountLoggedIn.getNumSiblings());
    }

    public static void updateFamilyFields(){
        String url = "https://uplbosa.org/apitest/student/update";
        RequestQueue queue = Volley.newRequestQueue(myFamilyView.getContext());
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
                params.put("mother_name",motherName.getText().toString());
                params.put("mother_educ",motherEduc.getText().toString());
                params.put("mother_work",motherWork.getText().toString());
                params.put("mother_bday",motherBday.getText().toString());
                params.put("father_name",fatherName.getText().toString());
                params.put("father_educ",fatherEduc.getText().toString());
                params.put("father_work",fatherWork.getText().toString());
                params.put("father_bday",fatherBday.getText().toString());
                params.put("siblings_total",totalSiblings.getText().toString());
                return params;
            }

            @Override
            protected void onFinish() {
                super.onFinish();
                Log.d("Family", "Done");
            }
        };
        queue.add(myJsonRequest);
    }

}

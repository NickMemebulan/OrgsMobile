package com.example.nick.orgsmobile;

import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LoginActivity extends DrawerAndToolbarClass {

    Button connectButton;
    TextView upMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        displayDrawer();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        connectButton = (Button)findViewById(R.id.connectButton);

        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                samplePOSTRequest();
            }
        });

        upMail = findViewById(R.id.upMailEditText);


    }

    public void samplePOSTRequest(){
        String url = "https://uplbosa.org/apitest/student/profile";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest myJsonRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);
                        try {
                            JSONObject json = new JSONObject(response);
                            Iterator<String> keys = json.keys();

                            while(keys.hasNext()){
                                String key = keys.next();
                                Log.d("Key :", key);
                                upMail.setText(json.get("username").toString());
                            }
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
                params.put("student_no", "2014-00013");
                return params;
            }
        };
        queue.add(myJsonRequest);
    }
}

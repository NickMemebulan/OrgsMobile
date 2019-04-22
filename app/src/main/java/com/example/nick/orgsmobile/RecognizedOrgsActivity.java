package com.example.nick.orgsmobile;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RecognizedOrgsActivity extends DrawerAndToolbarClass{

    LinearLayout collegeBased;
    LinearLayout typeBased;
    Button groupBy;

    TextView univBasedOrgs;
    TextView caBasedOrgs;
    TextView casBasedOrgs;
    TextView cdcBasedOrgs;
    TextView ceatBasedOrgs;
    TextView cemBasedOrgs;
    TextView cfnrBasedOrgs;
    TextView cheBasedOrgs;
    TextView cvmBasedOrgs;
    TextView gsBasedOrgs;

    ArrayList<OrgTitleAndId> univBased = new ArrayList<>();
    ArrayList<OrgTitleAndId> caBased = new ArrayList<>();
    ArrayList<OrgTitleAndId> casBased = new ArrayList<>();
    ArrayList<OrgTitleAndId> cdcBased = new ArrayList<>();
    ArrayList<OrgTitleAndId> ceatBased = new ArrayList<>();
    ArrayList<OrgTitleAndId> cemBased = new ArrayList<>();
    ArrayList<OrgTitleAndId> cfnrBased = new ArrayList<>();
    ArrayList<OrgTitleAndId> cheBased = new ArrayList<>();
    ArrayList<OrgTitleAndId> cvmBased = new ArrayList<>();
    ArrayList<OrgTitleAndId> gsBased = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recognized_org_activity);

        collegeBased = findViewById(R.id.college_based);
        typeBased = findViewById(R.id.type_based);
        groupBy = findViewById(R.id.groupByButton);

        groupBy.setOnClickListener(new View.OnClickListener(){
            public void onClick(View button){
                groupBy.setBackgroundDrawable(getResources().getDrawable(R.drawable.square));
                groupBy.setTextColor(getResources().getColor(R.color.colorBlack));
                PopupMenu popup = new PopupMenu(RecognizedOrgsActivity.this, button);
                popup.setOnDismissListener(new PopupMenu.OnDismissListener() {
                    @Override
                    public void onDismiss(PopupMenu menu) {
                        groupBy.setBackgroundDrawable(getResources().getDrawable(R.drawable.maroon_square));
                        groupBy.setTextColor(getResources().getColor(R.color.colorDirtyWhite));
                    }
                });
                popup.getMenuInflater().inflate(R.menu.group_by_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.groupByCollege:
                                collegeBased.setVisibility(View.VISIBLE);
                                typeBased.setVisibility(View.GONE);
                                return true;
                            case R.id.groupByType:
                                collegeBased.setVisibility(View.GONE);
                                typeBased.setVisibility(View.VISIBLE);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popup.show();
            }
        });

        displayDrawer();

        getOrganizations();

        univBasedOrgs = findViewById(R.id.univBasedOrgs);
        caBasedOrgs = findViewById(R.id.caBasedOrgs);
        casBasedOrgs = findViewById(R.id.casBasedOrgs);
        cdcBasedOrgs = findViewById(R.id.cdcBasedOrgs);
        ceatBasedOrgs = findViewById(R.id.ceatBasedOrgs);
        cemBasedOrgs = findViewById(R.id.cemBasedOrgs);
        cfnrBasedOrgs = findViewById(R.id.cfnrBasedOrgs);
        cheBasedOrgs = findViewById(R.id.cheBasedOrgs);
        cvmBasedOrgs = findViewById(R.id.cvmBasedOrgs);
        gsBasedOrgs = findViewById(R.id.gsBasedOrgs);
    }


    public void getOrganizations(){
        String url = "https://uplbosa.org/apitest/org/search";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest myJsonRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);
                        try {
                            //JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = new JSONArray(response);
                            for(int i = 0; i < jsonArray.length(); i++){
                                JSONObject xplrObject = jsonArray.getJSONObject(i);
                                Log.d("JSON "+ i, xplrObject.toString());
                                String orgIteratorTitle = xplrObject.get("name").toString();
                                String orgIteratorId = xplrObject.get("orgid").toString();
                                OrgTitleAndId orgIterator = new OrgTitleAndId(orgIteratorTitle, orgIteratorId);
                                String orgIteratorCollege = xplrObject.get("college").toString();
                                if(orgIteratorCollege.equals("CA")){
                                    caBased.add(orgIterator);
                                }else if(orgIteratorCollege.equals("CAS")){
                                    casBased.add(orgIterator);
                                }else if(orgIteratorCollege.equals("CDC")){
                                    cdcBased.add(orgIterator);
                                }else if(orgIteratorCollege.equals("CEAT")){
                                    ceatBased.add(orgIterator);
                                }else if(orgIteratorCollege.equals("CEM")){
                                    cemBased.add(orgIterator);
                                }else if(orgIteratorCollege.equals("CFNR")){
                                    cfnrBased.add(orgIterator);
                                }else if(orgIteratorCollege.equals("CHE")){
                                    cheBased.add(orgIterator);
                                }else if(orgIteratorCollege.equals("CVM")){
                                    cvmBased.add(orgIterator);
                                }else if(orgIteratorCollege.equals("GS")){
                                    gsBased.add(orgIterator);
                                }else if(orgIteratorCollege.equals("")){
                                    univBased.add(orgIterator);
                                }

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
                return params;
            }

            @Override
            protected void onFinish() {
                setTextView(caBasedOrgs, caBased);
                setTextView(casBasedOrgs, casBased);
                setTextView(cdcBasedOrgs, cdcBased);
                setTextView(ceatBasedOrgs, ceatBased);
                setTextView(cemBasedOrgs, cemBased);
                setTextView(cfnrBasedOrgs, cfnrBased);
                setTextView(cheBasedOrgs, cheBased);
                setTextView(cvmBasedOrgs, cvmBased);
                setTextView(gsBasedOrgs, gsBased);
                setTextView(univBasedOrgs, univBased);
            }
        };

        queue.add(myJsonRequest);
    }

    public void setTextView(final TextView textview, final ArrayList<OrgTitleAndId> orgsArray){
        textview.setOnClickListener(new View.OnClickListener(){
            public void onClick(View button){
                textview.setBackgroundDrawable(getResources().getDrawable(R.drawable.maroon_square));
                textview.setTextColor(getResources().getColor(R.color.colorDirtyWhite));
                PopupMenu popup = new PopupMenu(RecognizedOrgsActivity.this, button);
                addMenuOptions(popup.getMenu(), orgsArray);
                popup.setOnDismissListener(new PopupMenu.OnDismissListener() {
                    @Override
                    public void onDismiss(PopupMenu menu) {
                        textview.setBackgroundDrawable(getResources().getDrawable(R.drawable.square));
                        textview.setTextColor(getResources().getColor(R.color.colorBlack));
                    }
                });
                popup.getMenuInflater().inflate(R.menu.blank_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Intent orgLayoutActivityIntent = new Intent(RecognizedOrgsActivity.this, OrgLayoutActivity.class);
                        orgLayoutActivityIntent.putExtra("orgid", String.valueOf(item.getItemId()));
                        startActivity(orgLayoutActivityIntent);
                        /*Toast.makeText(RecognizedOrgsActivity.this, String.valueOf(item.getItemId()), Toast.LENGTH_SHORT).show();
                        return false;*/
                        return true;
                    }
                });
                popup.show();
            }
        });
    }

    public void addMenuOptions(Menu menu, ArrayList<OrgTitleAndId> orgsArray){
        for(int i = 0; i < orgsArray.size(); i++){
            int orgid = Integer.valueOf(orgsArray.get(i).getOrgId());
            String orgtitle = orgsArray.get(i).getOrgTitle();
            menu.add(0, orgid, 0, orgtitle);
        }

    };
}

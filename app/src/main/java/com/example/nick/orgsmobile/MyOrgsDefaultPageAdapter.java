package com.example.nick.orgsmobile;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MyOrgsDefaultPageAdapter extends RecyclerView.Adapter<MyOrgsDefaultPageAdapter.MyOrgsViewHolder> {
    @NonNull

    private static int createdViewHolder;
    private int myOrgsCount;
    private JSONArray myOrgs;

    public MyOrgsDefaultPageAdapter(int myOrgsCount, JSONArray myOrgs){
        this.myOrgsCount = myOrgsCount;
        this.myOrgs = myOrgs;
        createdViewHolder = 0;
    }

    @Override
    public MyOrgsDefaultPageAdapter.MyOrgsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        int layoutForMyOrgsViewHolder = R.layout.my_orgs_recycler_view;
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutForMyOrgsViewHolder, viewGroup, shouldAttachToParentImmediately);
        MyOrgsDefaultPageAdapter.MyOrgsViewHolder myOrgsViewHolder = new MyOrgsDefaultPageAdapter.MyOrgsViewHolder(view);

        createdViewHolder++;
        System.out.println("Created Home Event View Holder: " + createdViewHolder);

        return myOrgsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrgsDefaultPageAdapter.MyOrgsViewHolder myOrgsViewHolder, int i) {
        String orgID = "";
        String orgDesignation = "";
        try {
            JSONObject currentMyOrg = myOrgs.getJSONObject(i);
            Log.d("Current Org", currentMyOrg.toString());
            //String orgName = currentMyOrg.get("name").toString();
            orgID = currentMyOrg.get("org_id").toString();
            orgDesignation = currentMyOrg.get("designation").toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        myOrgsViewHolder.myOrgName.setText("Organization Name Holder: " + i);
        myOrgsViewHolder.myOrgID.setText(orgID);
        myOrgsViewHolder.myOrgDesignation.setText(orgDesignation);

        final String finalOrgID = orgID;
        myOrgsViewHolder.myOrgName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent orgLayoutActivityIntent = new Intent(view.getContext(), OrgLayoutActivity.class);
                orgLayoutActivityIntent.putExtra("orgid", finalOrgID);
                view.getContext().startActivity(orgLayoutActivityIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myOrgsCount;
    }

    public static class MyOrgsViewHolder extends RecyclerView.ViewHolder{

        TextView myOrgName;
        TextView myOrgID;
        TextView myOrgDesignation;

        public MyOrgsViewHolder(@NonNull View itemView) {
            super(itemView);
            myOrgName = itemView.findViewById(R.id.myOrganizationName);
            myOrgID = itemView.findViewById(R.id.myOrganizationID);
            myOrgDesignation = itemView.findViewById(R.id.myOrganizationDesignation);
            /*EventPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent eventTemplateIntent = new Intent(view.getContext(), TemplateEventLayout.class);
                    view.getContext().startActivity(eventTemplateIntent);
                }
            });*/
        }
    }
}

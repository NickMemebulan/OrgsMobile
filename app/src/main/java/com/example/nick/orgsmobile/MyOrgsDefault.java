package com.example.nick.orgsmobile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrgsDefault extends Fragment {

    View myOrgsDefaultView;
    int numberOfSampleOrgs = 5;

    RecyclerView myOrgsRecyclerView;
    private RecyclerView.Adapter myOrgsAdapter;
    private RecyclerView.LayoutManager myOrgsLayoutManager;


    public MyOrgsDefault() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        myOrgsDefaultView = inflater.inflate(R.layout.my_orgs_default, container, false);

        myOrgsRecyclerView = myOrgsDefaultView.findViewById(R.id.myOrgsRecyclerView);
        myOrgsRecyclerView.setHasFixedSize(true);
        myOrgsLayoutManager = new LinearLayoutManager(getActivity());
        myOrgsRecyclerView.setLayoutManager(myOrgsLayoutManager);
        int myOrgsCount = AccountLoggedIn.getMyOrgs().length();
        Log.d("Created","My Orgs Default View");
        Log.d("My Orgs Count", String.valueOf(myOrgsCount));
        JSONArray myOrgs = AccountLoggedIn.getMyOrgs();
        myOrgsAdapter = new MyOrgsDefaultPageAdapter(myOrgsCount, myOrgs);
        myOrgsRecyclerView.setAdapter(myOrgsAdapter);

        return myOrgsDefaultView;
    }

}

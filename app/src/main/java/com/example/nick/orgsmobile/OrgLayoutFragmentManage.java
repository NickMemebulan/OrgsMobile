package com.example.nick.orgsmobile;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrgLayoutFragmentManage extends Fragment {

    TextView createActivityForm;
    TextView updateOrganizationProfile;


    public OrgLayoutFragmentManage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.org_layout_manage, container, false);

        createActivityForm = (TextView) view.findViewById(R.id.createActivityForm);
        createActivityForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createActivityFormIntent = new Intent(view.getContext(), ManageCreateActivityForm.class);
                view.getContext().startActivity(createActivityFormIntent);
            }
        });
        updateOrganizationProfile = view.findViewById(R.id.updateOrganizationProfile);
        updateOrganizationProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateOrgProfileIntent = new Intent(view.getContext(), ManageUpdateOrgProfile.class);
                view.getContext().startActivity(updateOrgProfileIntent);
            }
        });

        return view;
    }

}

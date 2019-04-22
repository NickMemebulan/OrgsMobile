package com.example.nick.orgsmobile;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
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

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrgLayoutFragmentPublic extends Fragment {

    View activeOrgView;
    ImageView orgLogo;
    TextView orgName;
    TextView orgEstablished;
    TextView orgDescription;
    TextView orgWebsite;
    TextView orgTambayan;

    public OrgLayoutFragmentPublic() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        activeOrgView = inflater.inflate(R.layout.org_layout_public, container, false);

        orgLogo = activeOrgView.findViewById(R.id.orgLogo);
        orgName = activeOrgView.findViewById(R.id.orgName);
        orgEstablished = activeOrgView.findViewById(R.id.orgEstablished);
        orgDescription = activeOrgView.findViewById(R.id.orgDescription);
        orgWebsite = activeOrgView.findViewById(R.id.orgWebsite);
        orgTambayan = activeOrgView.findViewById(R.id.orgTambayan);

        //printActiveOrgName();

        try {
            setOrgPublicProfile();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return activeOrgView;
    }

    /*public void printActiveOrgName(){
        Log.d("getOrgName",AccountLoggedIn.getUserID());

    }
*/
    public void setOrgPublicProfile() throws JSONException {
        JSONObject mActiveOrg = ActiveOrganizationActivity.getActiveOrg();
        setOrgLogo(mActiveOrg.get("orgid").toString());
        orgName.setText(ActiveOrganizationActivity.getOrgName());
        orgEstablished.setText(mActiveOrg.get("date_established").toString());
        orgDescription.setText(mActiveOrg.get("description").toString());
        orgWebsite.setText(mActiveOrg.get("website").toString());
        orgTambayan.setText(mActiveOrg.get("tambayan").toString());
    }

    public void setOrgLogo(final String orgid){
        final Bitmap[] decoded = new Bitmap[1];
        String url = "https://uplbosa.org/apitest/org/logo";
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest myJsonRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);
                        String pureBase64Encoded = response.substring(response.indexOf(",") + 1);
                        byte[] decodeString = Base64.decode(pureBase64Encoded, Base64.DEFAULT);
                        decoded[0] = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);

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
                return params;
            }

            @Override
            protected void onFinish() {
                orgLogo.setImageBitmap(decoded[0]);
            }
        };
        queue.add(myJsonRequest);
    }

    /*@Override
    public void onResume() {
        super.onResume();
        orgName.setText(ActiveOrganizationActivity.getOrgName());
    }*/
}

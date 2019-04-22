package com.example.nick.orgsmobile;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class ActiveOrganizationActivity {
    static JSONObject mActiveOrg;
    static String orgName;


    public static void setActiveOrg(JSONObject activeOrg){
        mActiveOrg = activeOrg;
        try {
            orgName = mActiveOrg.get("name").toString();
            Log.d("Active Org", activeOrg.get("name").toString());
            Log.d("mActive Org",mActiveOrg.get("name").toString());
            Log.d("this is is pancit",getOrgName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void setOrgName(String orgNameNew){
        orgName = orgNameNew;
    }

    public static JSONObject getActiveOrg(){
        return mActiveOrg;
    }

    public static String getOrgName(){
        return orgName;
    }
}

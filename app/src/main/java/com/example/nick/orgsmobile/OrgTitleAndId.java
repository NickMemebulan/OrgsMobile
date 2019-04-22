package com.example.nick.orgsmobile;

public class OrgTitleAndId {

    String orgTitle;
    String orgId;

    public OrgTitleAndId(String orgTitle, String orgId){
        this.orgTitle = orgTitle;
        this.orgId = orgId;
    }

    public String getOrgTitle(){
        return  this.orgTitle;
    }
    public String getOrgId(){
        return this.orgId;
    }
}

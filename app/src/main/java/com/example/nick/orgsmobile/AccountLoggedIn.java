package com.example.nick.orgsmobile;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AccountLoggedIn {


    static JSONArray mMyOrgs;

    static byte[] studentPhotoDecodeString;

    static String mUserid;
    static String mUpmail;

    /*Profile Header*/
    static String mName;
    static String mUsername;
    static String mStudent_no;
    static String mCourse;
    static String mCollege;

    /*Profile Personal*/
    static String mNickname;
    static String mTalents;
    static String mReligion;
    static String mNationality;
    static String mBirthplace;
    static String mMarital_status;
    static String mSex;
    static String mBloodtype;

    /*Profile Contact*/
    static String mAddress_college;
    static String mAddress_permanent;
    static String mRegion;
    static String mMobile_device;
    static String mMobile_no;
    static String mLandline;
    static String mIce;                  //In Case of Emergency (Contact Person)
    static String mIce_no;
    static String mEmail;
    static String mFacebookId;
    static String mTwitterId;

    /*Profile Family*/
    static String mMother_name;
    static String mMother_educ;
    static String mMother_work;
    static String mMother_bday;
    static String mFather_name;
    static String mFather_educ;
    static String mFather_bday;
    static String mFather_work;
    static String mSiblings_total;

    /*Profile Additional*/
    static String mPrev_college;
    static String mPrev_highschool;
    static String mPrev_elementary;
    static String mPassport;
    static String mPassport_exp;
    static String mVisa;
    static String mVisa_exp;
    static String mEmployer;
    static String mJob_title;

    public static void clearAccountLoggedIn(){
        mUserid = null;
        mUpmail = null;
    }

    public static void setStudentPhotoDecodeString(byte[] decodeString){studentPhotoDecodeString = decodeString;}
    public static byte[] getStudentPhotoDecodeString(){return  studentPhotoDecodeString;}

    public static void setMyOrgs(JSONArray myOrgs){mMyOrgs = myOrgs;}
    public static JSONArray getMyOrgs(){return mMyOrgs;}

    public static void setUserID(String userid){mUserid = userid;}
    public static String getUserID(){return mUserid;}

    public static void setUsername(String username){
        mUsername = username;
    }
    public static String getUsername(){
        return mUsername;
    }

    /*Profile Header*/
    /*Set*/
    public static void setName(String name){
        mName = name;
    }
    public static void setStudentNo(String student_no){
        mStudent_no = student_no;
    }
    public static void setCourse(String course){mCourse = course;}
    public static void setCollege(String college){mCollege = college;}
    /*Get*/
    public static String getName(){ return mName; }
    public static String getStudentNo(){ return mStudent_no; }
    public static String getCourse(){return mCourse;}
    public static String getCollege(){return mCollege;}
    /*-----------------------------------*/

    /*Profile Personal*/
    /*Set*/
    public static void setNickname(String nickname){
        mNickname = nickname;
    }
    public static void setTalents(String talents){mTalents = talents;}
    public static void setReligion(String religion){mReligion = religion;}
    public static void setNationality(String nationality){mNationality = nationality;}
    public static void setBirthplace(String birthplace){mBirthplace = birthplace;}
    public static void setMarital(String marital){mMarital_status = marital;}
    public static void setSex(String sex){mSex = sex;}
    public static void setBlood(String blood){mBloodtype = blood;}
    /*Get*/
    public static String getNickname(){
        return mNickname;
    }
    public static String getTalents(){return mTalents;}
    public static String getReligion(){return mReligion;}
    public static String getNationality(){return mNationality;}
    public static String getBirthplace(){return mBirthplace;}
    public static String getMarital(){return mMarital_status;}
    public static String getSex(){return mSex;}
    public static String getBlood(){return mBloodtype;}
    /*-----------------------------------*/

    /*Profile Contact*/
    /*Set*/
    public static void setCollAdd(String address_college){mAddress_college = address_college;}
    public static void setPermAdd(String address_permanent){mAddress_permanent = address_permanent;}
    public static void setRegion(String region){mRegion = region;}
    public static void setMobDevice(String mobile_device){mMobile_device = mobile_device;}
    public static void setMobNum(String mobile_no){mMobile_no = mobile_no;}
    public static void setTelNum(String landline){mLandline = landline;}
    public static void setContactPersonName(String ice){mIce = ice;}
    public static void setContactPersonNum(String ice_no){mIce_no = ice_no;}
    public static void setEmailAdd(String email){mEmail = email;}
    public static void setFbUserId(String facebookId){mFacebookId = facebookId;}
    public static void setTwitterUser(String twitterId){mTwitterId = twitterId;}
    /*Get*/
    public static String getCollAdd(){return  mAddress_college;}
    public static String getPermAdd(){return mAddress_permanent;}
    public static String getRegion(){return mRegion;}
    public static String getMobDevice(){return mMobile_device;}
    public static String getMobNum(){return mMobile_no;}
    public static String getTelNum(){return mLandline;}
    public static String getConntactPersonName(){return mIce;}
    public static String getContactPersonNum(){return mIce_no;}
    public static String getEmailAdd(){return mEmail;}
    public static String getFbUserId(){return mFacebookId;}
    public static String getTwitterUserId(){return mTwitterId;}

    /*Profile Family*/
    /*Set*/
    public static void setMotherName(String mother_name){mMother_name = mother_name;}
    public static void setMotherEduc(String mother_educ){mMother_educ = mother_educ;}
    public static void setMotherWork(String mother_work){mMother_work = mother_work;}
    public static void setMotherBday(String mother_bday){mMother_bday = mother_bday;}
    public static void setFatherName(String father_name){mFather_name = father_name;}
    public static void setFatherEduc(String father_educ){mFather_educ = father_educ;}
    public static void setFatherWork(String father_work){mFather_work = father_work;}
    public static void setFatherBday(String father_bday){mFather_bday = father_bday;}
    public static void setNumSiblings(String siblings_total){mSiblings_total = siblings_total;}
    /*Get*/
    public static String getMotherName(){return mMother_name;}
    public static String getMotherEduc(){return mMother_educ;}
    public static String getMotherWork(){return mMother_work;}
    public static String getMotherBday(){return mMother_bday;}
    public static String getFatherName(){return mFather_name;}
    public static String getFatherEduc(){return mFather_educ;}
    public static String getFatherWork(){return mFather_work;}
    public static String getFatherBday(){return mFather_bday;}
    public static String getNumSiblings(){return mSiblings_total;}

    /*Profile Additional*/
    /*Set*/
    public static void setPrevCollege(String prev_college){mPrev_college = prev_college;}
    public static void setPrevHighSchool(String prev_highschool){mPrev_highschool = prev_highschool;}
    public static void setPrevElementary(String prev_elementary){mPrev_elementary = prev_elementary;}
    public static void setPassport(String passport){mPassport = passport;}
    public static void setPassportExp(String passport_exp){mPassport_exp = passport_exp;}
    public static void setVisa(String visa){mVisa = visa;}
    public static void setVisaExp(String visa_exp){mVisa_exp = visa_exp;}
    public static void setEmployer(String employer){mEmployer = employer;}
    public static void setJobTitle(String job_title){mJob_title = job_title;}
    /*Get*/
    public static String getPrevCollege(){return mPrev_college;}
    public static String getPrevHighSchool(){return mPrev_highschool;}
    public static String getPrevElementary(){return mPrev_elementary;}
    public static String getPassport(){return mPassport;}
    public static String getPassportExp(){return mPassport_exp;}
    public static String getVisa(){return mVisa;}
    public static String getVisaExp(){return mVisa_exp;}
    public static String getEmployer(){return mEmployer;}
    public static String getJobTitle(){return mJob_title;}
}

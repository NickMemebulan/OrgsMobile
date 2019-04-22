package com.example.nick.orgsmobile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MyProfileActivity extends DrawerAndToolbarClass implements StudentUpdateConfirmationDialog.ConfirmationDialogListener {

    private TabLayout tabLayout;
    private TabItem tabPersonal;
    private TabItem tabContact;
    private TabItem tabFamily;
    private TabItem tabAdditional;
    private ViewPager MyProfileViewPager;
    private Button saveButton;

    TextView studentName;
    TextView studentNumber;
    TextView studentCourse;
    TextView studentCollege;
    ImageView studentPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_profile_activity);

        displayDrawer();

        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabPersonal = (TabItem)findViewById(R.id.tabPersonal);
        tabContact = (TabItem)findViewById(R.id.tabContact);
        tabFamily = (TabItem)findViewById(R.id.tabFamily);
        tabAdditional = (TabItem)findViewById(R.id.tabAdditional);
        MyProfileViewPager = (ViewPager)findViewById(R.id.myProfileViewPager);

        MyProfilePageAdapter pageAdapter = new  MyProfilePageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        MyProfileViewPager.setAdapter(pageAdapter);
        MyProfileViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                MyProfileViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        initializeProfileHeader();

        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });



        //initializeData();

    }

    private void initializeProfileHeader(){
        studentName = findViewById(R.id.studentName);
        studentNumber = findViewById(R.id.studentNumber);
        studentCourse = findViewById(R.id.studentCourse);
        studentCollege = findViewById(R.id.studentCollege);
        studentPhoto = findViewById(R.id.studentPicture);

        studentName.setText(AccountLoggedIn.getName());
        studentNumber.setText(AccountLoggedIn.getStudentNo());
        studentCourse.setText(AccountLoggedIn.getCourse());
        studentCollege.setText(AccountLoggedIn.getCollege());

        byte[] decodeString = AccountLoggedIn.getStudentPhotoDecodeString();
        Bitmap decoded = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
        studentPhoto.setImageBitmap(decoded);
    }

    public void openDialog(){
        StudentUpdateConfirmationDialog studentUpdateConfirmationDialog = new StudentUpdateConfirmationDialog();
        studentUpdateConfirmationDialog.show(getSupportFragmentManager(), "confirmation dialog");
    }

    /*public void initializeData(){
        String nickname = AccountLoggedIn.getNickname();
        MyProfileFragmentPersonal.setNickname(nickname);
    }*/

    @Override
    public void checkRequired(String requiredText, String requiredInput) {
        if(requiredInput.equals(requiredText)){
            updateStudentProfile();
        }else{
            wrongConfirmationText();
        }
    }

    public void updateStudentProfile(){
        MyProfileFragmentPersonal.updatePersonalFields();
        MyProfileFragmentContact.updateContactFields();
        MyProfileFragmentFamily.updateFamilyFields();
        //MyProfileFragmentAdditional.updateAdditionalFields();
        Toast.makeText(this, R.string.successful_update, Toast.LENGTH_SHORT).show();
    }

    public void wrongConfirmationText() {
        Toast.makeText(this, R.string.confirmation_prompt, Toast.LENGTH_SHORT).show();
    }
}

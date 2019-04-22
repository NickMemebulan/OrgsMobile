package com.example.nick.orgsmobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ManageCreateActivityForm extends DrawerAndToolbarClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_create_form);

        displayDrawer();
    }
}

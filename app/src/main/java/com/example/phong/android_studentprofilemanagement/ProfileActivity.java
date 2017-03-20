package com.example.phong.android_studentprofilemanagement;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.view.ViewGroup;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends Activity {

    BottomNavigationView bottomNavigation;
    ViewGroup studentProfile;
    CircleImageView studentProfilePicture;
    TextView studentFullName;
    TextView studentCode;
    TextView studentDOB;
    TextView studentEmail;
    TextView studentArress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigation.inflateMenu(R.menu.menu_student);

    }
}

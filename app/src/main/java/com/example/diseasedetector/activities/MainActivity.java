package com.example.diseasedetector.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.example.diseasedetector.R;
import com.example.diseasedetector.dialog.CheckingDialog;
import com.example.diseasedetector.fragments.AboutFragment;
import com.example.diseasedetector.fragments.HomeFragment;
import com.example.diseasedetector.fragments.NewsFragment;
import com.example.diseasedetector.fragments.RecordFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.MenuItem;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //make full screen
        this.getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        if (!isNetworkConnected()) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            final CheckingDialog checkingDialog = CheckingDialog.getInstance(1);
            checkingDialog.show(fragmentManager, "check");
            //checkingDialog.setCancelable(true);

        } else {

            loadFragment(new HomeFragment());
            BottomNavigationView navView = findViewById(R.id.nav_view);
            navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        }


    }


    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    break;
                case R.id.navigation_dashboard:
                    fragment = new NewsFragment();
                    break;
                case R.id.navigation_record:
                    fragment = new RecordFragment();
                    break;
                case R.id.navigation_notifications:
                    fragment = new AboutFragment();
                    break;
            }
            return loadFragment(fragment);
        }
    };


    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);

    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}


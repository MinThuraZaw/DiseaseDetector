package com.example.diseasedetector.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.diseasedetector.R;

public class PatientRecordDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private AppCompatTextView tv_name, tv_age, tv_gender, tv_weight, tv_height, tv_blood, tv_happen,
    txt_name, txt_age, txt_gender, txt_weight, txt_height, txt_blood, txt_happen, tv_date, txt_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_record_detail);

        tv_name = findViewById(R.id.patient_detail_name);
        tv_age = findViewById(R.id.patient_detail_age);
        tv_gender = findViewById(R.id.patient_detail_gender);
        tv_weight = findViewById(R.id.patient_detail_weight);
        tv_height = findViewById(R.id.patient_detail_height);
        tv_blood = findViewById(R.id.patient_detail_blood);
        tv_happen = findViewById(R.id.patient_detail_happen);
        tv_date = findViewById(R.id.patient_detail_date);


        txt_name = findViewById(R.id.txt_detail_name);
        txt_age = findViewById(R.id.txt_detail_age);
        txt_gender = findViewById(R.id.txt_detail_gender);
        txt_weight = findViewById(R.id.txt_detail_weight);
        txt_height = findViewById(R.id.txt_detail_height);
        txt_blood = findViewById(R.id.txt_detail_blood);
        txt_happen = findViewById(R.id.txt_detail_happen);
        txt_date = findViewById(R.id.txt_detail_date);


        toolbar = findViewById(R.id.toolbar_patient_record);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

        String name  = getIntent().getExtras().getString("pname");
        String age  = getIntent().getExtras().getString("page");
        String gender  = getIntent().getExtras().getString("pgender");
        String weight  = getIntent().getExtras().getString("pweight");
        String height  = getIntent().getExtras().getString("pheight");
        String blood  = getIntent().getExtras().getString("pblood");
        String happen  = getIntent().getExtras().getString("phappen");
        String date  = getIntent().getExtras().getString("pdate");

        tv_name.setText(name);
        tv_age.setText(age);
        tv_gender.setText(gender);
        tv_weight.setText(weight);
        tv_height.setText(height);
        tv_blood.setText(blood);
        tv_happen.setText(happen);
        tv_date.setText(date);



        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/avenirnextregular.ttf");
        tv_name.setTypeface(font);
        tv_age.setTypeface(font);
        tv_gender.setTypeface(font);
        tv_weight.setTypeface(font);
        tv_height.setTypeface(font);
        tv_blood.setTypeface(font);
        tv_happen.setTypeface(font);
        tv_date.setTypeface(font);


        txt_name.setTypeface(font);
        txt_age.setTypeface(font);
        txt_gender.setTypeface(font);
        txt_weight.setTypeface(font);
        txt_height.setTypeface(font);
        txt_blood.setTypeface(font);
        txt_happen.setTypeface(font);
        txt_date.setTypeface(font);



    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }
        return true;
    }
}

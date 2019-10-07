package com.example.diseasedetector.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.diseasedetector.R;

public class CheckUpDetailActivity extends AppCompatActivity {

    private AppCompatTextView detail_type, detail_result, detail_date, detail_attr, detail_txttype,
    detail_txtresult, detail_txtdate, detail_txtattr;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkup_detail);

        toolbar = findViewById(R.id.toolbar_checkup_detail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);


        detail_type = findViewById(R.id.detail_type);
        detail_result = findViewById(R.id.detail_result);
        detail_date = findViewById(R.id.detail_date);
        detail_attr = findViewById(R.id.detail_attr);
        detail_txttype = findViewById(R.id.detail_txttype);
        detail_txtresult = findViewById(R.id.detail_txtresult);
        detail_txtdate = findViewById(R.id.detail_txtdate);
        detail_txtattr = findViewById(R.id.detail_txtattr);



        //Intent intent = new Intent();

        String type  = getIntent().getExtras().getString("type");
        String result  = getIntent().getExtras().getString("result");
        String date  = getIntent().getExtras().getString("date");
        String attr  = getIntent().getExtras().getString("attr");



        detail_type.setText(type);
        detail_result.setText(result);
        detail_date.setText(date);
        detail_attr.setText(attr);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/avenirnextregular.ttf");
        detail_type.setTypeface(font);
        detail_result.setTypeface(font);
        detail_date.setTypeface(font);
        detail_attr.setTypeface(font);

        detail_txttype.setTypeface(font);
        detail_txtresult.setTypeface(font);
        detail_txtdate.setTypeface(font);
        detail_txtattr.setTypeface(font);


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

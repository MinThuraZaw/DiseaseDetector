package com.example.diseasedetector.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;

import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.diseasedetector.R;
import com.example.diseasedetector.dialog.Utils;
import com.example.diseasedetector.model.MainModel;
import com.example.diseasedetector.model.PatientRecord;
import com.example.diseasedetector.network.ApiService;
import com.example.diseasedetector.network.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class
AddPatientActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private AppCompatButton btn_add, btn_clear;
    private AppCompatEditText et_name, et_age, et_gender, et_weight, et_height, et_blood, et_happen;
    private String add_patient_api = "http://159.138.11.211";
    private String value_name, value_age, value_gender, value_weight, value_height, value_blood, value_happen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        toolbar = findViewById(R.id.toolbar_addpatient);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

        final ApiService addpApi = RetrofitClientInstance.getRetrofitInstance(add_patient_api).create(ApiService.class);

        et_name = findViewById(R.id.et_patient_name);
        et_age = findViewById(R.id.et_patient_age);
        et_gender = findViewById(R.id.et_patient_gender);
        et_weight = findViewById(R.id.et_patient_weight);
        et_height = findViewById(R.id.et_patient_height);
        et_blood = findViewById(R.id.et_patient_blood);
        et_happen = findViewById(R.id.et_patient_happen);


        btn_add = findViewById(R.id.btn_add_patient);
        btn_clear = findViewById(R.id.btn_clear_patient);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                value_name = et_name.getText().toString();
                value_age = et_age.getText().toString();
                value_gender = et_gender.getText().toString();
                value_weight = et_weight.getText().toString();
                value_height = et_height.getText().toString();
                value_blood = et_blood.getText().toString();
                value_happen = et_happen.getText().toString();

                if (value_name.isEmpty()) {

                    et_name.setError("Please enter the patient's name");

                } else {
                }


                if (value_age.isEmpty()) {

                    et_age.setError("Please enter the patient's age");
                } else {
                }

                if (value_gender.isEmpty()) {

                    et_gender.setError("Please enter the patient's gender");
                } else {
                }


                if (value_weight.isEmpty()) {

                    et_weight.setError("Please enter the patient's weight");
                } else {
                }

                if (value_height.isEmpty()) {

                    et_height.setError("Please enter the patient's height");
                } else {
                }

                if (value_blood.isEmpty()) {

                    et_blood.setError("Please enter the patient's blood type");
                } else {
                }

                if (value_happen.isEmpty()) {

                    et_happen.setError("Please enter what is happening to the patient");
                } else {
                }

                if (checkIsEmpty(value_name) || checkIsEmpty(value_age) || checkIsEmpty(value_gender) || checkIsEmpty(value_weight) || checkIsEmpty(value_height) || checkIsEmpty(value_blood) || checkIsEmpty(value_happen)) {

                    Toast.makeText(AddPatientActivity.this, "Please enter all the information!", Toast.LENGTH_SHORT).show();

                } else {


                    // Toast.makeText(AddPatientActivity.this, "Comming Soon", Toast.LENGTH_SHORT).show();

                    final String json_addpatient = "{ \"name\" : \"" + value_name + "\", \"age\" : \" " + value_age + "\",\"gender\" : \"" + value_gender + "\",\"weight\" : \"" + value_weight + "\",\"height\" : \"" + value_height + "\",\"bloodtype\" : \"" + value_blood + "\", \"happened\" : \"" + value_happen + "\"}";

                    Call<PatientRecord> addPCall = addpApi.AddPatient("application/json", json_addpatient);

                    addPCall.enqueue(new Callback<PatientRecord>() {
                        @Override
                        public void onResponse(Call<PatientRecord> call, Response<PatientRecord> response) {

                            //Log.d("json",""+json_addpatient);


                            if (response.isSuccessful()) {
                                Toast.makeText(AddPatientActivity.this, "Add patient success!", Toast.LENGTH_SHORT).show();
                                Log.d("json", "" + json_addpatient);
                            }

                        }

                        @Override
                        public void onFailure(Call<PatientRecord> call, Throwable t) {

                            Log.d("json", t.getMessage());
                            Log.d("json", "" + json_addpatient);
                            Toast.makeText(AddPatientActivity.this, "Fail to add patient! Try Again", Toast.LENGTH_SHORT).show();


                        }
                    });

                }


            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAlltext();
                //Toast.makeText(AddPatientActivity.this, "Comming Soon", Toast.LENGTH_SHORT).show();
            }
        });

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/avenirnextregular.ttf");
        btn_add.setTypeface(font);
        btn_clear.setTypeface(font);

    }

    private void clearAlltext(){
        et_name.setText("");
        et_age.setText("");
        et_gender.setText("");
        et_weight.setText("");
        et_height.setText("");
        et_blood.setText("");
        et_happen.setText("");

    }


    private boolean checkIsEmpty(String st) {
        if (TextUtils.isEmpty(st)) {
            return true;
        } else
            return false;
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

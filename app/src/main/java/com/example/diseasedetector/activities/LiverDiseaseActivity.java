package com.example.diseasedetector.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.diseasedetector.R;
import com.example.diseasedetector.database.AppDatabase;
import com.example.diseasedetector.database.CheckupRecords;
import com.example.diseasedetector.dialog.CheckingDialog;
import com.example.diseasedetector.dialog.Utils;
import com.example.diseasedetector.model.HeartAttackModel;
import com.example.diseasedetector.model.MainModel;
import com.example.diseasedetector.network.ApiService;
import com.example.diseasedetector.network.RetrofitClientInstance;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiverDiseaseActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private AppCompatEditText et_age, et_totalbi, et_directbi, et_alkaline, et_alamine, et_aspart, et_protien, et_albumin, et_ratio;
    private AppCompatSpinner gender_spinner;
    private int value_gender;
    private String value_age, value_totalBi, value_directBi, value_alkaline, value_alamine, value_aspart, value_protien, value_albumin, value_ratio;
    private AppCompatButton btn_liver_checkup;
    private String liver_Api = "apiaddress";
    MainModel mainModel = null;
    private String attributes;
    String currentDate, currentTime, datetime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liver_disease);

        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build();

        final ApiService liverApi = RetrofitClientInstance.getRetrofitInstance(liver_Api).create(ApiService.class);


        toolbar = findViewById(R.id.toolbar_liver);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);


        gender_spinner = findViewById(R.id.liver_gender_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sex_list_item, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender_spinner.setAdapter(adapter);
        gender_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                value_gender = i ;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        et_age = findViewById(R.id.et_liver_age);
        et_totalbi = findViewById(R.id.et_liver_totalBi);
        et_directbi = findViewById(R.id.et_liver_directBi);
        et_alkaline = findViewById(R.id.et_liver_alkaline);
        et_alamine = findViewById(R.id.et_liver_alamine);
        et_aspart = findViewById(R.id.et_liver_aspart);
        et_protien = findViewById(R.id.et_liver_protien);
        et_albumin = findViewById(R.id.et_liver_albumin);
        et_ratio = findViewById(R.id.et_liver_ratio);



        btn_liver_checkup = findViewById(R.id.btn_liver_checkup);
        btn_liver_checkup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                currentTime = new SimpleDateFormat("h:mm a", Locale.getDefault()).format(new Date());
                datetime = ""+currentDate+","+currentTime;

                value_age = (et_age.getText().toString());
                value_totalBi = (et_totalbi.getText().toString());
                value_directBi = (et_directbi.getText().toString());
                value_alkaline = (et_alkaline.getText().toString());
                value_alamine = (et_alamine.getText().toString());
                value_aspart = (et_aspart.getText().toString());
                value_protien = (et_protien.getText().toString());
                value_albumin = (et_albumin.getText().toString());
                value_ratio = (et_ratio.getText().toString());

                if (value_age.isEmpty()) {

                    et_age.setError("Please enter the patient's age");

                } else {
                }


                if (value_totalBi.isEmpty()) {

                    et_totalbi.setError("Please enter the Total Bilirubin");
                } else {
                }

                if (value_directBi.isEmpty()) {

                    et_directbi.setError("Please enter the Direct Bilirubin");
                } else {
                }


                if (value_alkaline.isEmpty()) {

                    et_alkaline.setError("Please enter the Alkaline Phosphotase");
                } else {
                }

                if (value_alamine.isEmpty()) {

                    et_alamine.setError("Please enter the Alamine Aminotransferase");
                } else {
                }

                if (value_aspart.isEmpty()) {

                    et_aspart.setError("Please enter the Aspartate Aminotransferase");
                } else {
                }

                if (value_protien.isEmpty()) {

                    et_protien.setError("Please enter the Protien");
                } else {
                }

                if (value_albumin.isEmpty()) {

                    et_albumin.setError("Please enter the Albumin");
                } else {
                }

                if (value_ratio.isEmpty()) {

                    et_ratio.setError("Please enter the Albumin and Globulin Ratio");
                } else {
                }


                if (checkIsEmpty(value_age) || checkIsEmpty(value_totalBi) || checkIsEmpty(value_directBi) || checkIsEmpty(value_alkaline) || checkIsEmpty(value_alamine) || checkIsEmpty(value_aspart) || checkIsEmpty(value_protien) || checkIsEmpty(value_albumin) || checkIsEmpty(value_ratio)) {

                    Toast.makeText(LiverDiseaseActivity.this, "Please enter all the information!", Toast.LENGTH_SHORT).show();

                } else {


                    //custom dialog setup
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    final CheckingDialog checkingDialog = CheckingDialog.getInstance(0);
                    checkingDialog.show(fragmentManager, "check");

                    String json_liver = "{\"data\" : { \"req_data\" : [{ \"attr_1\" : \"" + value_age + "\", \"attr_2\" : \" " + value_gender + "\",\"attr_3\" : \"" + value_totalBi + "\",\"attr_4\" : \"" + value_directBi + "\",\"attr_5\" : \"" + value_alkaline + "\",\"attr_6\" : \"" + value_alamine + "\",\"attr_7\" : \"" + value_aspart + "\", \"attr_8\" : \"" + value_protien + "\", \"attr_9\" : \"" + value_albumin + "\", \"attr_10\" : \"" + value_ratio + "\" ,\"attr_11\" : \"\"}]}}";

                    attributes = "Age : "+value_age+",\n Gender : "+value_gender+",\n Total Bilirubin : "+value_totalBi+",\n Direct Bilirubin : "+value_directBi+",\n Alkaline Phosphotase : "+value_alkaline+",\n Alamine Aminotransferase : "+value_alamine+",\n Aspartate Aminotransferase : "+value_aspart+",\n Total Protiens : "+value_protien+",\n Albumin : "+value_albumin+",\n Albumin and Globulin Ratio : "+value_ratio;

                    Call<MainModel> liverCall = liverApi.getLiver("application/json", Utils.user_token, json_liver);

                    Log.d("json", json_liver);

                    liverCall.enqueue(new Callback<MainModel>() {
                        @Override
                        public void onResponse(Call<MainModel> call, Response<MainModel> response) {

                            if (response.isSuccessful()) {
                                checkingDialog.removeChecking();


                                mainModel = response.body();
                                String result = mainModel.getResult().getResp_data()[0].getPredictioncol();

                                if (result.equals("1")) {

                                    checkingDialog.setMainText("The result is\n\"YES\"" + "\nYou have Liver Disease!");
                                    checkingDialog.setCancelable(true);

                                    //insert to Room database
                                    CheckupRecords checkupRecords = new CheckupRecords(""+attributes, "Liver Disease","YES", datetime);
                                    db.histroyDao().insertHistroy(checkupRecords);

                                } else {

                                    checkingDialog.setMainText("The result is\n\"NO\"" + "\nYou don't have Liver Disease!");
                                    checkingDialog.setCancelable(true);

                                    //insert to Room database
                                    CheckupRecords checkupRecords = new CheckupRecords(""+attributes, "Liver Disease","NO", datetime);
                                    db.histroyDao().insertHistroy(checkupRecords);

                                }

                                Log.d("json", "Success");
                                Log.d("json", "result is " + result);


                            } else if (response.code() == 404) {

                                checkingDialog.removeChecking();
                                checkingDialog.setCancelable(true);
                                checkingDialog.setMainText("Sorry, the server does not work! \n Try again later.");

                                //insert to Room database
                                CheckupRecords checkupRecords = new CheckupRecords(""+attributes, "Liver Disease","Server Error",datetime);
                                db.histroyDao().insertHistroy(checkupRecords);

                            }

                        }

                        @Override
                        public void onFailure(Call<MainModel> call, Throwable t) {

                            //checkingDialog.cancel();
                            checkingDialog.removeChecking();
                            checkingDialog.setCancelable(true);

                            checkingDialog.setMainText("Something has wrong! \n Try again!");

                            Log.d("json", "Failed");
                            Log.d("json", "" + t.getMessage());


                        }
                    });

                }




            }
        });

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/avenirnextregular.ttf");
        btn_liver_checkup.setTypeface(font);




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

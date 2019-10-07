package com.example.diseasedetector.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
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
import android.widget.Toast;

import com.example.diseasedetector.R;
import com.example.diseasedetector.database.AppDatabase;
import com.example.diseasedetector.database.CheckupRecords;
import com.example.diseasedetector.dialog.CheckingDialog;
import com.example.diseasedetector.dialog.Utils;
import com.example.diseasedetector.model.MainModel;
import com.example.diseasedetector.network.ApiService;
import com.example.diseasedetector.network.RetrofitClientInstance;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiabetesActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private String diabetes_api = "apiaddress";
    private AppCompatEditText et_pregnacy, et_glucose, et_bloodpressure, et_skin, et_insulin, et_bmi, et_dpf, et_age;
    private String value_pregnacy, value_glucose, value_bloodpressure, value_skin,
            value_insulin, value_bmi, value_dpf, value_age;

    private AppCompatButton btn_dia_checkup;
    MainModel mainModel = null;
    private String attributes;
    String currentDate, currentTime, datetime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diabetes);

        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build();


        toolbar = findViewById(R.id.toolbar_diabete);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);


        final ApiService diabeteApi = RetrofitClientInstance.getRetrofitInstance(diabetes_api).create(ApiService.class);

        et_pregnacy = findViewById(R.id.et_dia_pregnacy);
        et_glucose = findViewById(R.id.et_dia_glucose);
        et_bloodpressure = findViewById(R.id.et_dia_bloodpressure);
        et_skin = findViewById(R.id.et_dia_skin);
        et_insulin = findViewById(R.id.et_dia_insulin);
        et_bmi = findViewById(R.id.et_dia_bmi);
        et_dpf = findViewById(R.id.et_dia_dpf);
        et_age = findViewById(R.id.et_dia_age);

        btn_dia_checkup = findViewById(R.id.btn_dia_checkup);
        btn_dia_checkup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                currentTime = new SimpleDateFormat("h:mm a", Locale.getDefault()).format(new Date());
                datetime = ""+currentDate+","+currentTime;


                value_pregnacy = (et_pregnacy.getText().toString());
                value_glucose = (et_glucose.getText().toString());
                value_bloodpressure = (et_bloodpressure.getText().toString());
                value_skin = (et_skin.getText().toString());
                value_insulin = (et_insulin.getText().toString());
                value_bmi = (et_bmi.getText().toString());
                value_dpf = (et_dpf.getText().toString());
                value_age = (et_age.getText().toString());

                if (value_pregnacy.isEmpty()) {

                    et_pregnacy.setError("Please enter the patient's Pregnancies");

                } else {
                }


                if (value_glucose.isEmpty()) {

                    et_glucose.setError("Please enter the patient's Glucose");
                } else {
                }

                if (value_bloodpressure.isEmpty()) {

                    et_bloodpressure.setError("Please enter the patient's BloodPressure");
                } else {
                }


                if (value_skin.isEmpty()) {

                    et_skin.setError("Please enter the patient's SkinThickness");
                } else {
                }

                if (value_insulin.isEmpty()) {

                    et_insulin.setError("Please enter the patient's Insulin");
                } else {
                }

                if (value_bmi.isEmpty()) {

                    et_bmi.setError("Please enter the patient's Old BMI");
                } else {
                }

                if (value_dpf.isEmpty()) {

                    et_dpf.setError("Please enter the patient's DPF");
                } else {
                }

                if (value_age.isEmpty()) {

                    et_age.setError("Please enter the patient's Age");
                } else {
                }


                if (checkIsEmpty(value_pregnacy) || checkIsEmpty(value_glucose) || checkIsEmpty(value_bloodpressure) || checkIsEmpty(value_skin) || checkIsEmpty(value_insulin) || checkIsEmpty(value_bmi) || checkIsEmpty(value_dpf) || (checkIsEmpty(value_age))) {

                    Toast.makeText(DiabetesActivity.this, "Please enter all the information!", Toast.LENGTH_SHORT).show();

                } else {

                    //custom dialog setup
                    FragmentManager fragmentManager  = getSupportFragmentManager();
                    final CheckingDialog checkingDialog = CheckingDialog.getInstance(0);
                    checkingDialog.show(fragmentManager,"check");

                    String json_diabete = "{\"data\" : { \"req_data\" : [{ \"attr_1\" : \""+value_pregnacy+"\", \"attr_2\" : \" "+value_glucose+"\",\"attr_3\" : \""+value_bloodpressure+"\",\"attr_4\" : \""+value_skin+"\",\"attr_5\" : \""+value_insulin+"\",\"attr_6\" : \""+value_bmi+"\",\"attr_7\" : \""+value_dpf+"\",\"attr_8\" : \""+value_age+"\",\"attr_9\" : \"\"}]}}";
                    attributes = "Pregnancies : "+value_pregnacy+",\n Glucose : "+value_glucose+", \n BloodPressure : "+value_bloodpressure+", \n SkinThickness : "+value_skin+",\n Insulin : "+value_insulin+",\n BMI : "+value_bmi+",\n DiabetesPedigreeFunction : "+value_dpf+",\nAge : "+value_age;

                    Call<MainModel> diabeteCall = diabeteApi.getDiabete("application/json", Utils.user_token,json_diabete);

                    Log.d("json", json_diabete);

                    diabeteCall.enqueue(new Callback<MainModel>() {
                        @Override
                        public void onResponse(Call<MainModel> call, Response<MainModel> response) {

                            if (response.isSuccessful()) {
                                checkingDialog.removeChecking();

                                mainModel = response.body();
                                String result = mainModel.getResult().getResp_data()[0].getPredictioncol();

                                if(result.equals("1")){

                                    checkingDialog.setMainText("The result is\n\"YES\""+"\nYou have Diabetes disease!");
                                    checkingDialog.setCancelable(true);

                                    //insert to Room database
                                    CheckupRecords checkupRecords = new CheckupRecords(""+attributes, "Diabetes","YES", datetime);
                                    db.histroyDao().insertHistroy(checkupRecords);


                                }else{

                                    checkingDialog.setMainText("The result is\n\"NO\""+"\nYou don't have Diabetes disease!");
                                    checkingDialog.setCancelable(true);

                                    //insert to Room database
                                    CheckupRecords checkupRecords = new CheckupRecords(""+attributes, "Diabetes","NO", datetime);
                                    db.histroyDao().insertHistroy(checkupRecords);


                                }

                                Log.d("json", "Success");
                                Log.d("json", "result is"+result);


                            }else if(response.code() == 404){

                                checkingDialog.removeChecking();
                                checkingDialog.setCancelable(true);

                                checkingDialog.setMainText("Sorry, the server does not work! \n Try again later.");

                                //insert to Room database
                                CheckupRecords checkupRecords = new CheckupRecords(""+attributes, "Diabetes","Server Error", datetime);
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
        btn_dia_checkup.setTypeface(font);

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

package com.example.diseasedetector.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
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

public class HypertensiveActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private AppCompatSpinner gender_spinner, age_spinner, race_spinner, bmi_spinner, kidney_spinner, smoking_spinner,
    diabetes_spinner;
    private int value_gender, value_age, value_race, value_bmi, value_kidney, value_smoking, value_diabete;
    private AppCompatButton btn_hyper_checkup;
    private String hyper_Api = "apiaddress";
    MainModel mainModel = null;
    private String attributes;
    String currentDate, currentTime, datetime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hypertensive);

        final ApiService hyperApi = RetrofitClientInstance.getRetrofitInstance(hyper_Api).create(ApiService.class);
        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build();
       // data = db.histroyDao().getAll();


        toolbar = findViewById(R.id.toolbar_hyper);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);


        gender_spinner = findViewById(R.id.hyper_gender_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.hyper_gender, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender_spinner.setAdapter(adapter);
        gender_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                value_gender = i + 1;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        age_spinner = findViewById(R.id.hyper_age_spinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.hyper_age, R.layout.spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        age_spinner.setAdapter(adapter2);
        age_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                value_age = i + 1;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        race_spinner = findViewById(R.id.hyper_race_spinner);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.hyper_race, R.layout.spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        race_spinner.setAdapter(adapter3);
        race_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                value_race = i + 1;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bmi_spinner = findViewById(R.id.hyper_bmirange_spinner);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                R.array.hyper_bmi, R.layout.spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bmi_spinner.setAdapter(adapter4);
        bmi_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                value_bmi = i + 1;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        kidney_spinner = findViewById(R.id.hyper_kidneyproblem_spinner);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,
                R.array.hyper_kidney, R.layout.spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kidney_spinner.setAdapter(adapter5);
        kidney_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                value_kidney = i + 1;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        smoking_spinner = findViewById(R.id.hyper_smoking_spinner);
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this,
                R.array.hyper_kidney, R.layout.spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        smoking_spinner.setAdapter(adapter6);
        smoking_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                value_smoking = i + 1;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        diabetes_spinner = findViewById(R.id.hyper_diabete_spinner);
        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(this,
                R.array.hyper_diabete, R.layout.spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        diabetes_spinner.setAdapter(adapter7);
        diabetes_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                value_diabete = i + 1;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btn_hyper_checkup = findViewById(R.id.btn_hyper_checkup);
        btn_hyper_checkup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                currentTime = new SimpleDateFormat("h:mm a", Locale.getDefault()).format(new Date());
                datetime = ""+currentDate+","+currentTime;

                //custom dialog setup
                FragmentManager fragmentManager  = getSupportFragmentManager();
                final CheckingDialog checkingDialog = CheckingDialog.getInstance(0);
                checkingDialog.show(fragmentManager,"check");

                String json_hyper = "{\"data\" : { \"req_data\" : [{ \"attr_1\" : \""+value_gender+"\", \"attr_2\" : \" "+value_age+"\",\"attr_3\" : \""+value_race+"\",\"attr_4\" : \""+value_bmi+"\",\"attr_5\" : \""+value_kidney+"\",\"attr_6\" : \""+value_smoking+"\",\"attr_7\" : \""+value_diabete+"\",\"attr_8\" : \"\"}]}}";
                attributes = "Gender : "+value_gender+", \nAge Range : "+value_age+",\n RACE : "+value_race+",\nBMI Range : "+value_bmi+",\n Kidney Problem history : "+value_kidney+", \nSmoking history : "+value_smoking+",\n Diabetes history : "+value_diabete;

                Call<MainModel> hyperCall = hyperApi.getHypertensive("application/json", Utils.user_token,json_hyper);

                Log.d("json", json_hyper);

                hyperCall.enqueue(new Callback<MainModel>() {
                    @Override
                    public void onResponse(Call<MainModel> call, Response<MainModel> response) {

                        if (response.isSuccessful()) {
                            checkingDialog.removeChecking();


                            mainModel = response.body();
                            String result = mainModel.getResult().getResp_data()[0].getPredictioncol();

                            if(result.equals("1")){

                                checkingDialog.setMainText("The result is\n\"YES\""+"\nYou have Hypertension!");
                                checkingDialog.setCancelable(true);

                                //insert to Room database
                                CheckupRecords checkupRecords = new CheckupRecords(""+attributes, "Hypertension","YES", datetime);
                                db.histroyDao().insertHistroy(checkupRecords);

                            }else{

                                checkingDialog.setMainText("The result is\n\"NO\""+"\nYou don't have Hypertension!");
                                checkingDialog.setCancelable(true);

                                CheckupRecords checkupRecords = new CheckupRecords(""+attributes, "Hypertension","NO", datetime);
                                db.histroyDao().insertHistroy(checkupRecords);

                            }

                            Log.d("json", "Success");
                            Log.d("json", "result is "+result);


                        }else if(response.code() == 404){

                            checkingDialog.removeChecking();
                            checkingDialog.setCancelable(true);

                            checkingDialog.setMainText("Sorry, the server does not work! \n Try again later.");

                            //insert to Room database
                            CheckupRecords checkupRecords = new CheckupRecords(""+attributes, "Hypertension","Server Error", datetime);
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
        });

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/avenirnextregular.ttf");
        btn_hyper_checkup.setTypeface(font);


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

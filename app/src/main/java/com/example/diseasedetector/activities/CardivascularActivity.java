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
import com.example.diseasedetector.model.MainModel;
import com.example.diseasedetector.network.ApiService;
import com.example.diseasedetector.network.RetrofitClientInstance;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardivascularActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private String cardio_api = "apiaddress";
    private AppCompatSpinner gender_spinner, chol_spinner, glucose_spinner, smoking_spinner, alcohol_spinner, physical_spinner;
    private AppCompatEditText et_age, et_height, et_weight, et_aphi, et_aplo;
    private String value_age, value_height, value_weight, value_aphi, value_aplo;
    private int value_gender, value_chol, value_glucose, value_smoking, value_alcohol, value_physical;
    private AppCompatButton btn_cardio_checkup;
    MainModel mainModel = null;
    private String attributes;
    String currentDate, currentTime, datetime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardivascular);

        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build();

        toolbar = findViewById(R.id.toolbar_cardio);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);



        gender_spinner = findViewById(R.id.cardio_gender_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sex_list_item, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender_spinner.setAdapter(adapter);
        gender_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                value_gender = i;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        chol_spinner = findViewById(R.id.cardio_cholesterol_spinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.cardio_chol_list_item, R.layout.spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chol_spinner.setAdapter(adapter2);
        chol_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                value_chol = i + 1;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        glucose_spinner = findViewById(R.id.cardio_glucose_spinner);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.cardio_chol_list_item, R.layout.spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        glucose_spinner.setAdapter(adapter3);
        glucose_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                value_glucose = i+1;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        smoking_spinner = findViewById(R.id.cardio_smoking_spinner);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                R.array.exang_list_item, R.layout.spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        smoking_spinner.setAdapter(adapter4);
        smoking_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                value_smoking = i;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        alcohol_spinner = findViewById(R.id.cardio_alcohol_spinner);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,
                R.array.exang_list_item, R.layout.spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        alcohol_spinner.setAdapter(adapter5);
        alcohol_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                value_alcohol = i;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        physical_spinner = findViewById(R.id.cardio_physical_spinner);
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this,
                R.array.exang_list_item, R.layout.spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        physical_spinner.setAdapter(adapter6);
        physical_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                value_physical = i;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //api
        final ApiService cardioApi = RetrofitClientInstance.getRetrofitInstance(cardio_api).create(ApiService.class);

        et_age = findViewById(R.id.et_cardio_age);
        et_height = findViewById(R.id.et_cardio_height);
        et_weight = findViewById(R.id.et_cardio_weight);
        et_aphi = findViewById(R.id.et_cardio_aphi);
        et_aplo = findViewById(R.id.et_cardio_aplo);

        btn_cardio_checkup = findViewById(R.id.btn_cardio_checkup);
        btn_cardio_checkup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                currentTime = new SimpleDateFormat("h:mm a", Locale.getDefault()).format(new Date());
                datetime = ""+currentDate+","+currentTime;


                value_age = (et_age.getText().toString());
                value_height = (et_height.getText().toString());
                value_weight = (et_weight.getText().toString());
                value_aphi = (et_aphi.getText().toString());
                value_aplo = (et_aplo.getText().toString());

                if (value_age.isEmpty()) {

                    et_age.setError("Please enter the patient's Age");

                } else {
                }


                if (value_height.isEmpty()) {

                    et_height.setError("Please enter the patient's Height");
                } else {
                }

                if (value_weight.isEmpty()) {

                    et_weight.setError("Please enter the patient's Weight");
                } else {
                }


                if (value_aphi.isEmpty()) {

                    et_aphi.setError("Please enter the patient Aphi");
                } else {
                }

                if (value_aplo.isEmpty()) {

                    et_aplo.setError("Please enter the patient's Aplo");
                } else {
                }


                if (checkIsEmpty(value_age) || checkIsEmpty(value_height) || checkIsEmpty(value_weight) || checkIsEmpty(value_aphi) || checkIsEmpty(value_aplo)) {

                    Toast.makeText(CardivascularActivity.this, "Please enter all the information!", Toast.LENGTH_SHORT).show();

                } else {

                    //custom dialog setup
                    FragmentManager fragmentManager  = getSupportFragmentManager();
                    final CheckingDialog checkingDialog = CheckingDialog.getInstance(0);
                    checkingDialog.show(fragmentManager,"check");

                    String json_cardio = "{\"data\" : { \"req_data\" : [{ \"attr_1\" : \""+value_age+"\", \"attr_2\" : \" "+value_gender+"\",\"attr_3\" : \""+value_height+"\",\"attr_4\" : \""+value_weight+"\",\"attr_5\" : \""+value_aphi+"\",\"attr_6\" : \""+value_aplo+"\",\"attr_7\" : \""+value_chol+"\",\"attr_8\" : \""+value_glucose+"\",\"attr_9\" : \""+value_smoking+"\",\"attr_10\" : \""+value_alcohol+"\",\"attr_11\" : \""+value_physical+"\",\"attr_12\" : \"\"}]}}";

                    attributes = "Age : "+value_age+",\n Gender : "+value_gender+",\nHeight : "+value_height+",\n Weight : "+value_weight+",\n Ap_hi : "+value_aphi+",\n Ap_lo : "+value_aplo+",\nCholesterol : "+value_chol+",\nGlucose : "+value_glucose+",\nSmoking? : "+value_smoking+",\nAlcohol intake? : "+value_alcohol+",\nPhysical Activity : "+value_physical;
                    Call<MainModel> cardioCall = cardioApi.getCardio("application/json", Utils.user_token,json_cardio);

                    Log.d("json", json_cardio);

                    cardioCall.enqueue(new Callback<MainModel>() {
                        @Override
                        public void onResponse(Call<MainModel> call, Response<MainModel> response) {

                            if (response.isSuccessful()) {
                                checkingDialog.removeChecking();

                                mainModel = response.body();
                                String result = mainModel.getResult().getResp_data()[0].getPredictioncol();

                                if(result.equals("1")){

                                    checkingDialog.setMainText("The result is\n\"YES\""+"\nYou have Cardiovascular disease!");
                                    checkingDialog.setCancelable(true);

                                    //insert to Room database
                                    CheckupRecords checkupRecords = new CheckupRecords(""+attributes, "Cardiovascular","YES", datetime);
                                    db.histroyDao().insertHistroy(checkupRecords);


                                }else{

                                    checkingDialog.setMainText("The result is\n\"NO\""+"\nYou don't have Cardiovascular disease!");
                                    checkingDialog.setCancelable(true);

                                    //insert to Room database
                                    CheckupRecords checkupRecords = new CheckupRecords(""+attributes, "Cardiovascular","NO", datetime);
                                    db.histroyDao().insertHistroy(checkupRecords);


                                }

                                Log.d("json", "Success");
                                Log.d("json", "result is "+result);


                            }else if(response.code() == 404){

                                checkingDialog.removeChecking();
                                checkingDialog.setCancelable(true);
                                checkingDialog.setMainText("Sorry, the server does not work! \n Try again later.");

                                //insert to Room database
                                CheckupRecords checkupRecords = new CheckupRecords(""+attributes, "Cardiovascular","Server Error", datetime);
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
        btn_cardio_checkup.setTypeface(font);
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

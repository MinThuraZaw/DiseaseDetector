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
import com.example.diseasedetector.network.ApiService;
import com.example.diseasedetector.network.RetrofitClientInstance;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeartAttackActivity extends AppCompatActivity {

    private AppCompatSpinner sex_spinner, chestpain_spinner, fbs_spinner, exang_spinner;
    private float sex_value, chestpain_value, fbs_value, exang_value;
    private String yesNo;

    private AppCompatEditText et_age, et_trestbps, et_chol, et_restecg,
            et_thalach, et_oldpeak, et_slope, et_ca, et_thal;
    private String value_age, value_trestbps, value_chol, value_restecg,
            value_thalach, value_oldpeak, value_slope, value_ca, value_thal;
    private AppCompatButton btn_ha_checkup;
    private Toolbar toolbar;
    private static final String HA_API = "apiaddress";
    List<HeartAttackModel> data = null;
    private String attributes;
    String currentDate, currentTime, datetime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_attack);

        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build();

        toolbar = findViewById(R.id.toolbar_ha);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);


//        final AppDatabase haDb = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
//                .allowMainThreadQueries()
//                .build();


        final ApiService heartAttackApi = RetrofitClientInstance.getRetrofitInstance(HA_API).create(ApiService.class);


        et_age = findViewById(R.id.et_ha_age);
        et_trestbps = findViewById(R.id.et_ha_trestbps);
        et_chol = findViewById(R.id.et_ha_chol);
        et_restecg = findViewById(R.id.et_ha_restecg);
        et_thalach = findViewById(R.id.et_ha_thalach);
        et_oldpeak = findViewById(R.id.et_ha_oldpeak);
        et_slope = findViewById(R.id.et_ha_slope);
        et_ca = findViewById(R.id.et_ha_ca);
        et_thal = findViewById(R.id.et_ha_thal);


        //setup spinner for sex
        sex_spinner = findViewById(R.id.ha_sex_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sex_list_item, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sex_spinner.setAdapter(adapter);
        sex_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                sex_value = i;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        chestpain_spinner = findViewById(R.id.ha_chestpain_spinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.chestpain_list_item, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chestpain_spinner.setAdapter(adapter2);
        chestpain_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                chestpain_value = i + 1;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        fbs_spinner = findViewById(R.id.ha_fbs_spinner);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.fbs_list_item, R.layout.spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fbs_spinner.setAdapter(adapter3);
        fbs_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                fbs_value = i;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        exang_spinner = findViewById(R.id.ha_exang_spinner);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,
                R.array.exang_list_item, R.layout.spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        exang_spinner.setAdapter(adapter5);
        exang_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                exang_value = i;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btn_ha_checkup = findViewById(R.id.btn_ha_checkup);
        btn_ha_checkup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                CheckupRecords histroyModel = new CheckupRecords(1,"ggwp","HA","YES");
//                haDb.histroyDao().insertHistroy(histroyModel);

                currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                currentTime = new SimpleDateFormat("h:mm a", Locale.getDefault()).format(new Date());
                datetime = ""+currentDate+","+currentTime;
//

                value_age = (et_age.getText().toString());
                value_trestbps = (et_trestbps.getText().toString());
                value_chol = (et_chol.getText().toString());
                value_restecg = (et_restecg.getText().toString());
                value_thalach = (et_thalach.getText().toString());
                value_oldpeak = (et_oldpeak.getText().toString());
                value_slope = (et_slope.getText().toString());
                value_ca = (et_ca.getText().toString());
                value_thal = (et_thal.getText().toString());

                if (value_age.isEmpty()) {

                    et_age.setError("Please enter the patient's age");

                } else {
                }


                if (value_trestbps.isEmpty()) {

                    et_trestbps.setError("Please enter the patient's Trestbps");
                } else {
                }

                if (value_chol.isEmpty()) {

                    et_chol.setError("Please enter the patient's Chol");
                } else {
                }


                if (value_restecg.isEmpty()) {

                    et_restecg.setError("Please enter the patient's Restecg");
                } else {
                }

                if (value_thalach.isEmpty()) {

                    et_thalach.setError("Please enter the patient's Thalach");
                } else {
                }

                if (value_oldpeak.isEmpty()) {

                    et_oldpeak.setError("Please enter the patient's Old Peak");
                } else {
                }

                if (value_slope.isEmpty()) {

                    et_slope.setError("Please enter the patient's Slope");
                } else {
                }

                if (value_ca.isEmpty()) {

                    et_ca.setError("Please enter the patient's Ca");
                } else {
                }

                if (value_thal.isEmpty()) {

                    et_thal.setError("Please enter the patient's Thal");
                } else {
                }

                if (checkIsEmpty(value_age) || checkIsEmpty(value_trestbps) || checkIsEmpty(value_chol) || checkIsEmpty(value_restecg) || checkIsEmpty(value_thalach) || checkIsEmpty(value_oldpeak) || checkIsEmpty(value_slope) || checkIsEmpty(value_ca) ||checkIsEmpty(value_thal) ) {

                    Toast.makeText(HeartAttackActivity.this, "Please enter all the information!", Toast.LENGTH_SHORT).show();

                } else {

                    //custom dialog setup
                    FragmentManager fragmentManager  = getSupportFragmentManager();
                    final CheckingDialog checkingDialog = CheckingDialog.getInstance(0);
                    checkingDialog.show(fragmentManager,"check");

                    String json_heartattack = "{\"data\" : { \"req_data\" : [{ \"attr_1\" : \""+value_age+"\", \"attr_2\" : \" "+sex_value+"\",\"attr_3\" : \""+chestpain_value+"\",\"attr_4\" : \""+value_trestbps+"\",\"attr_5\" : \""+value_chol+"\",\"attr_6\" : \""+fbs_value+"\",\"attr_7\" : \""+value_restecg+"\",\"attr_8\" : \""+value_thalach+"\",\"attr_9\" : \""+exang_value+"\",\"attr_10\" : \""+value_oldpeak+"\",\"attr_11\" : \""+value_slope+"\",\"attr_12\" : \""+value_ca+"\",\"attr_13\" : \""+value_thal+"\",\"attr_14\" : \"\"}]}}";

                    attributes = "Age : "+value_age+",\n Gender : "+sex_value+",\n Chest Pain : "+chestpain_value+",\n Trestbps : "+value_trestbps+",\n Chol : "+value_chol+",\n Fbs : "+fbs_value+",\nRestecg : "+value_restecg+",\nThalach : "+value_thalach+",\n Exang : "+exang_value+",\n Oldpeak : "+value_oldpeak+",\nSlope : "+value_slope+",\n Ca : "+value_ca+",\n Thal : "+value_thal;

                    Call<List<HeartAttackModel>> heartAttackCall = heartAttackApi.getHeartAttack("application/json", Utils.user_token,json_heartattack);

                    Log.d("json", json_heartattack);

                    heartAttackCall.enqueue(new Callback<List<HeartAttackModel>>() {
                        @Override
                        public void onResponse(Call<List<HeartAttackModel>> call, Response<List<HeartAttackModel>> response) {

                            if (response.isSuccessful()) {
                                data = response.body();

                                checkingDialog.removeChecking();

                                int result = data.get(0).getPredictioncol();
                                if(result == 1){

                                    checkingDialog.setMainText("The result is\n\"YES\""+"\nYou have Heart Attack disease!");
                                    checkingDialog.setCancelable(true);

                                    //insert to Room database
                                    CheckupRecords checkupRecords = new CheckupRecords(""+attributes, "Heart Attack","YES", datetime);
                                    db.histroyDao().insertHistroy(checkupRecords);


                                }else{

                                    checkingDialog.setMainText("The result is\n\"NO\""+"\nYou don't have Heart Attack disease!");
                                    checkingDialog.setCancelable(true);

                                    //insert to Room database
                                    CheckupRecords checkupRecords = new CheckupRecords(""+attributes, "Heart Attack","NO", datetime);
                                    db.histroyDao().insertHistroy(checkupRecords);



                                }

                                Log.d("json", "Success");
                                Log.i("json", "The result is " + data.get(0).getPredictioncol());

                                //database



                            }else if(response.code() == 404){

                                checkingDialog.removeChecking();
                                checkingDialog.setCancelable(true);
                                checkingDialog.setMainText("Sorry, the server does not work! \n Try again later.");

                                //insert to Room database
                                CheckupRecords checkupRecords = new CheckupRecords(""+attributes, "Heart Attack","Server Error", datetime);
                                db.histroyDao().insertHistroy(checkupRecords);

                            }

                        }

                        @Override
                        public void onFailure(Call<List<HeartAttackModel>> call, Throwable t) {

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
        btn_ha_checkup.setTypeface(font);


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

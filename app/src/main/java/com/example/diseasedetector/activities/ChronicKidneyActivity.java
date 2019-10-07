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
import com.github.ybq.android.spinkit.SpinKitView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChronicKidneyActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private String kidney_api = "apiaddress";
    private AppCompatSpinner rbc_spinner, pc_spinner, pcc_spinner, ba_spinner, htn_spinner, dm_spinner, cad_spinner, pe_spinner, ane_spinner, appet_spinner;
    private AppCompatEditText et_age, et_bp, et_sg, et_al, et_su, et_bgr, et_bu, et_sc, et_sod, et_pot, et_hemo, et_pcv, et_wc, et_rc;
    private String value_age, value_bp, value_sg, value_al, value_su, value_bgr, value_bu, value_sc, value_sod, value_pot, value_hemo, value_pcv, value_wc, value_rc;
    private int value_rbc, value_pc, value_pcc, value_ba, value_htn, value_dm, value_cad, value_pe, value_ane, value_appet;
    private AppCompatButton btn_kidney_checkup;
    MainModel mainModel = null;
    private String attributes;
    String currentDate, currentTime, datetime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronic_kidney);

        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build();

        toolbar = findViewById(R.id.toolbar_kidney);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);


        rbc_spinner = findViewById(R.id.kidney_rbc_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.normal_list_item, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rbc_spinner.setAdapter(adapter);
        rbc_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                value_rbc = i;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        pc_spinner = findViewById(R.id.kidney_pc_spinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.normal_list_item, R.layout.spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pc_spinner.setAdapter(adapter2);
        pc_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                value_pc = i ;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        pcc_spinner = findViewById(R.id.kidney_pcc_spinner);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.present_list_item, R.layout.spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pcc_spinner.setAdapter(adapter3);
        pcc_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                value_pcc = i;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ba_spinner = findViewById(R.id.kidney_ba_spinner);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                R.array.present_list_item, R.layout.spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ba_spinner.setAdapter(adapter4);
        ba_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                value_ba = i;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        htn_spinner = findViewById(R.id.kidney_htn_spinner);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,
                R.array.exang_list_item, R.layout.spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        htn_spinner.setAdapter(adapter5);
        htn_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                value_htn = i;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        dm_spinner = findViewById(R.id.kidney_dm_spinner);
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this,
                R.array.exang_list_item, R.layout.spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dm_spinner.setAdapter(adapter6);
        dm_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                value_dm = i;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        cad_spinner = findViewById(R.id.kidney_cad_spinner);
        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(this,
                R.array.exang_list_item, R.layout.spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cad_spinner.setAdapter(adapter7);
        cad_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                value_cad = i;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        pe_spinner = findViewById(R.id.kidney_pe_spinner);
        ArrayAdapter<CharSequence> adapter8 = ArrayAdapter.createFromResource(this,
                R.array.exang_list_item, R.layout.spinner_item);
        adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pe_spinner.setAdapter(adapter8);
        pe_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                value_pe = i;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ane_spinner = findViewById(R.id.kidney_ane_spinner);
        ArrayAdapter<CharSequence> adapter9 = ArrayAdapter.createFromResource(this,
                R.array.exang_list_item, R.layout.spinner_item);
        adapter9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ane_spinner.setAdapter(adapter9);
        ane_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                value_ane = i;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        appet_spinner = findViewById(R.id.kidney_appet_spinner);
        ArrayAdapter<CharSequence> adapter10 = ArrayAdapter.createFromResource(this,
                R.array.good_list_item, R.layout.spinner_item);
        adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        appet_spinner.setAdapter(adapter10);
        appet_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                value_appet = i;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //api
        final ApiService kidneyApi = RetrofitClientInstance.getRetrofitInstance(kidney_api).create(ApiService.class);

        et_age = findViewById(R.id.et_kidney_age);
        et_bp = findViewById(R.id.et_kidney_bp);
        et_sg = findViewById(R.id.et_kidney_sg);
        et_al = findViewById(R.id.et_kidney_al);
        et_su = findViewById(R.id.et_kidney_su);
        et_bu = findViewById(R.id.et_kidney_bu);
        et_sc = findViewById(R.id.et_kidney_sc);
        et_sod = findViewById(R.id.et_kidney_sod);
        et_pot = findViewById(R.id.et_kidney_pot);
        et_hemo = findViewById(R.id.et_kidney_hemo);
        et_pcv = findViewById(R.id.et_kidney_pcv);
        et_wc = findViewById(R.id.et_kidney_wc);
        et_rc = findViewById(R.id.et_kidney_rc);
        et_bgr = findViewById(R.id.et_kidney_bgr);


        btn_kidney_checkup = findViewById(R.id.btn_kidney_checkup);
        btn_kidney_checkup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                currentTime = new SimpleDateFormat("h:mm a", Locale.getDefault()).format(new Date());
                datetime = ""+currentDate+","+currentTime;

                value_age = (et_age.getText().toString());
                value_bp = (et_bp.getText().toString());
                value_sg = (et_sg.getText().toString());
                value_al = (et_al.getText().toString());
                value_su = (et_su.getText().toString());
                value_bgr = (et_bgr.getText().toString());
                value_bu = (et_bu.getText().toString());
                value_sc = (et_sc.getText().toString());
                value_sod = (et_sod.getText().toString());
                value_pot = (et_pot.getText().toString());
                value_hemo = (et_hemo.getText().toString());
                value_pcv = (et_pcv.getText().toString());
                value_wc = (et_wc.getText().toString());
                value_rc = (et_rc.getText().toString());


                if (value_age.isEmpty()) {

                    et_age.setError("Please enter the patient's Age");

                } else {
                }


                if (value_bp.isEmpty()) {

                    et_bp.setError("Please enter the patient's BP");
                } else {
                }

                if (value_sg.isEmpty()) {

                    et_sg.setError("Please enter the patient's Sg");
                } else {
                }


                if (value_al.isEmpty()) {

                    et_al.setError("Please enter the patient Al");
                } else {
                }

                if (value_su.isEmpty()) {

                    et_su.setError("Please enter the patient's Su");
                } else {
                }

                if (value_bgr.isEmpty()) {

                    et_bgr.setError("Please enter the patient's Bgr");
                } else {
                }

                if (value_bu.isEmpty()) {

                    et_bu.setError("Please enter the patient's Bu");
                } else {
                }

                if (value_sc.isEmpty()) {

                    et_sc.setError("Please enter the patient's Sc");
                } else {
                }
                if (value_sod.isEmpty()) {

                    et_sod.setError("Please enter the patient's Sod");
                } else {
                }
                if (value_pot.isEmpty()) {

                    et_pot.setError("Please enter the patient's Pot");
                } else {
                }
                if (value_hemo.isEmpty()) {

                    et_hemo.setError("Please enter the patient's Hemo");
                } else {
                }
                if (value_pcv.isEmpty()) {

                    et_pcv.setError("Please enter the patient's Pcv");
                } else {
                }
                if (value_wc.isEmpty()) {

                    et_wc.setError("Please enter the patient's Wc");
                } else {
                }
                if (value_rc.isEmpty()) {

                    et_rc.setError("Please enter the patient's Rc");
                } else {
                }


                if (checkIsEmpty(value_age) || checkIsEmpty(value_bp) || checkIsEmpty(value_sg) || checkIsEmpty(value_al) || checkIsEmpty(value_su) || checkIsEmpty(value_bgr) || checkIsEmpty(value_bu)  || checkIsEmpty(value_sc)  || checkIsEmpty(value_sod)  || checkIsEmpty(value_pot) || checkIsEmpty(value_hemo) || checkIsEmpty(value_pcv) || checkIsEmpty(value_wc) || checkIsEmpty(value_rc)) {

                    Toast.makeText(ChronicKidneyActivity.this, "Please enter all the information!", Toast.LENGTH_SHORT).show();

                } else {

                    //custom dialog setup
                    FragmentManager fragmentManager  = getSupportFragmentManager();
                    final CheckingDialog checkingDialog = CheckingDialog.getInstance(0);
                    checkingDialog.show(fragmentManager,"check");

                    String json_kidney = "{\"data\" : { \"req_data\" : [{ \"attr_1\" : \""+value_age+"\", \"attr_2\" : \" "+value_bp+"\",\"attr_3\" : \""+value_sg+"\",\"attr_4\" : \""+value_al+"\",\"attr_5\" : \""+value_su+"\",\"attr_6\" : \""+value_rbc+"\",\"attr_7\" : \""+value_pc+"\",\"attr_8\" : \""+value_pcc+"\",\"attr_9\" : \""+value_ba+"\",\"attr_10\" : \""+value_bgr+"\",\"attr_11\" : \""+value_bu+"\",\"attr_12\" : \""+value_sc+"\",\"attr_13\" : \""+value_sod+"\", \"attr_14\" : \""+value_pot+"\", \"attr_15\" : \" "+value_hemo+"\",\"attr_16\" : \" "+value_pcv+"\",\"attr_17\" : \""+value_wc+"\", \"attr_18\" : \""+value_rc+"\",\"attr_19\" : \""+value_htn+"\",\"attr_20\" : \""+value_dm+"\",\"attr_21\" : \""+value_cad+"\",\"attr_22\" : \""+value_appet+"\",\"attr_23\" : \""+value_pe+"\",\"attr_24\" : \""+value_ane+"\",\"attr_25\" : \"\"}]}}";
                    attributes = "Age : "+value_age+",\nBloodPressure : "+value_bp+",\nSpecific gravity : "+value_sg+",\nAlnumin : "+value_al+",\nSyger : "+value_su+",\nRed Blood Cell : "+value_rbc+",\nPus Cell : "+value_pc+",\nPus Cell Clumps : "+value_pcc+",\nBacteria : "+value_ba+",\nBlood Glucose Random : "+value_bgr+",\nBlood urea : "+value_bu+",\nSerum creatinine : "+value_sc+",\nSodium : "+value_sod+",\nPotassium : "+value_pot+",\nHemoglobin : "+value_hemo+",\nPacked Cell Volume : "+value_pcv+",\nWhite Blood Cell Count : "+value_wc+",\nRed Blood Cell Count : "+value_rc+",\nHypertension : "+value_htn+",\nDiabetes Mellitus : "+value_dm+",\nCoronary Artery : "+value_cad+",\nAppet : "+value_appet+",\nPedal edema : "+value_pe+",\nAnemia : "+value_ane;

                    Call<MainModel> kidneyCall = kidneyApi.getKidney("application/json", Utils.user_token,json_kidney);

                    Log.d("json", json_kidney);

                    kidneyCall.enqueue(new Callback<MainModel>() {
                        @Override
                        public void onResponse(Call<MainModel> call, Response<MainModel> response) {

                            if (response.isSuccessful()) {
                                checkingDialog.removeChecking();

                                mainModel = response.body();
                                String result = mainModel.getResult().getResp_data()[0].getPredictioncol();

                                if(result.equals("1.0")){

                                    checkingDialog.setMainText("The result is\n\"YES\""+"\nYou have ChronicKidney disease!");
                                    checkingDialog.setCancelable(true);

                                    //insert to Room database
                                    CheckupRecords checkupRecords = new CheckupRecords(""+attributes, "ChronicKidney","YES", datetime);
                                    db.histroyDao().insertHistroy(checkupRecords);


                                }else{

                                    checkingDialog.setMainText("The result is\n\"NO\""+"\nYou don't have ChronicKidney disease!");
                                    checkingDialog.setCancelable(true);

                                    //insert to Room database
                                    CheckupRecords checkupRecords = new CheckupRecords(""+attributes, "ChronicKidney","NO", datetime);
                                    db.histroyDao().insertHistroy(checkupRecords);


                                }

                                Log.d("json", "Success");
                                Log.d("json", "result is "+result);


                            }else if(response.code() == 404){

                                checkingDialog.removeChecking();
                                checkingDialog.setCancelable(true);
                                checkingDialog.setMainText("Sorry, the server does not work! \n Try again later.");

                                //insert to Room database
                                CheckupRecords checkupRecords = new CheckupRecords(""+attributes, "ChronicKidney","Server Error", datetime);
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
        btn_kidney_checkup.setTypeface(font);

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

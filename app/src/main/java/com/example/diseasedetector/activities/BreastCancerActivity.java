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

public class BreastCancerActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private String bc_Api = "apiaddress";
    private AppCompatEditText et_meanradius, et_meantexture, et_meanperimeter, et_meanarea, et_meansmoothness;
    private String value_radius, value_texture, value_perimeter, value_area, value_smoothness;

    private AppCompatButton btn_bc_checkup;
    MainModel mainModel = null;
    private String attributes;
    String currentDate, currentTime, datetime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breast_cancer);

        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build();

        toolbar = findViewById(R.id.toolbar_bcancer);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);


        final ApiService bcApi = RetrofitClientInstance.getRetrofitInstance(bc_Api).create(ApiService.class);

        et_meanradius = findViewById(R.id.et_bc_meanradius);
        et_meantexture = findViewById(R.id.et_bc_meantexture);
        et_meanperimeter = findViewById(R.id.et_bc_meanperimeter);
        et_meanarea = findViewById(R.id.et_bc_meanarea);
        et_meansmoothness = findViewById(R.id.et_bc_meansmoothness);

        btn_bc_checkup = findViewById(R.id.btn_bc_checkup);
        btn_bc_checkup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                currentTime = new SimpleDateFormat("h:mm a", Locale.getDefault()).format(new Date());
                datetime = ""+currentDate+","+currentTime;


                value_radius = (et_meanradius.getText().toString());
                value_texture = (et_meantexture.getText().toString());
                value_perimeter = (et_meanperimeter.getText().toString());
                value_area = (et_meanarea.getText().toString());
                value_smoothness = (et_meansmoothness.getText().toString());

                if (value_radius.isEmpty()) {

                    et_meanradius.setError("Please enter the lump's mean radius");

                } else {
                }


                if (value_texture.isEmpty()) {

                    et_meantexture.setError("Please enter the lump's mean texture");
                } else {
                }

                if (value_perimeter.isEmpty()) {

                    et_meanperimeter.setError("Please enter the lump's mean perimeter");
                } else {
                }


                if (value_area.isEmpty()) {

                    et_meanarea.setError("Please enter the lump's mean area");
                } else {
                }

                if (value_smoothness.isEmpty()) {

                    et_meansmoothness.setError("Please enter the lump's mean smoothness");
                } else {
                }


                if (checkIsEmpty(value_radius) || checkIsEmpty(value_texture) || checkIsEmpty(value_perimeter) || checkIsEmpty(value_area) || checkIsEmpty(value_smoothness)) {

                    Toast.makeText(BreastCancerActivity.this, "Please enter all the information!", Toast.LENGTH_SHORT).show();

                } else {

                    //custom dialog setup
                    FragmentManager fragmentManager  = getSupportFragmentManager();
                    final CheckingDialog checkingDialog = CheckingDialog.getInstance(0);
                    checkingDialog.show(fragmentManager,"check");

                    String json_bc = "{\"data\" : { \"req_data\" : [{ \"attr_1\" : \""+value_radius+"\", \"attr_2\" : \" "+value_texture+"\",\"attr_3\" : \""+value_perimeter+"\",\"attr_4\" : \""+value_area+"\",\"attr_5\" : \""+value_smoothness+"\",\"attr_6\" : \"\"}]}}";
                    attributes = "Lump's mean radius : "+value_radius+",\n Lumps mean texture : "+value_texture+",\n Lumps mean perimeter : "+value_perimeter+",\n Lumps mean area : "+value_area+",\nLumps mean smoothness : "+value_smoothness;

                    Call<MainModel> bcCall = bcApi.getBC("application/json", Utils.user_token,json_bc);

                    Log.d("json", json_bc);

                    bcCall.enqueue(new Callback<MainModel>() {
                        @Override
                        public void onResponse(Call<MainModel> call, Response<MainModel> response) {

                            if (response.isSuccessful()) {
                                checkingDialog.removeChecking();

                                mainModel = response.body();
                                String result = mainModel.getResult().getResp_data()[0].getPredictioncol();

                                if(result.equals("1")){

                                    checkingDialog.setMainText("The result is\n\"YES\""+"\nYou have Breast Cancer!");
                                    checkingDialog.setCancelable(true);

                                    //insert to Room database
                                    CheckupRecords checkupRecords = new CheckupRecords(""+attributes, "Breast Cancer","YES", datetime);
                                    db.histroyDao().insertHistroy(checkupRecords);


                                }else{

                                    checkingDialog.setMainText("The result is\n\"NO\""+"\nYou don't have Breast Cancer!");
                                    checkingDialog.setCancelable(true);

                                    //insert to Room database
                                    CheckupRecords checkupRecords = new CheckupRecords(""+attributes, "Breast Cancer","NO", datetime);
                                    db.histroyDao().insertHistroy(checkupRecords);



                                }

                                Log.d("json", "Success");
                                Log.d("json", "result is "+result);


                            }else if(response.code() == 404){

                                checkingDialog.removeChecking();
                                checkingDialog.setCancelable(true);
                                checkingDialog.setMainText("Sorry, the server does not work! \n Try again later.");

                                //insert to Room database
                                CheckupRecords checkupRecords = new CheckupRecords(""+attributes, "Breast Cancer","Server Error", datetime);
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
        btn_bc_checkup.setTypeface(font);

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

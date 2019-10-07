package com.example.diseasedetector.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.diseasedetector.R;
import com.example.diseasedetector.activities.BreastCancerActivity;
import com.example.diseasedetector.activities.CardivascularActivity;
import com.example.diseasedetector.activities.ChronicKidneyActivity;
import com.example.diseasedetector.activities.DiabetesActivity;
import com.example.diseasedetector.activities.HeartAttackActivity;
import com.example.diseasedetector.activities.HypertensiveActivity;
import com.example.diseasedetector.activities.LiverDiseaseActivity;
import com.example.diseasedetector.dialog.Utils;
import com.example.diseasedetector.network.ApiService;
import com.example.diseasedetector.network.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment implements View.OnClickListener {

    private String token_api = "https://iam.ap-southeast-1.myhuaweicloud.com";
    private String token_body = "{\"auth\": { \"identity\" : { \"methods\" : [\"password\"], \"password\" : { \"user\" : { \"name\" : \"MinThuraZaw\", \"password\" : \"minthuramodelart123\", \"domain\" : { \"name\" : \"UCSY-Virus\"}}}}, \"scope\" : { \"project\" : { \"id\" : \"05b83ddf258010082fd6c01686ade2f3\"}}}}";

    private AppCompatTextView txt_heart, txt_kidney, txt_diabete, txt_cardi, txt_breast, txt_coming,
    txt_liver, txt_hyper;
    private RelativeLayout card_heart, card_kidney, card_diabete, card_cardi, card_breastcancer, card_liver,
                            card_hyper;
    private AppCompatImageView img_heartbeat;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    void getUserToken(){

        final ApiService tokenApi = RetrofitClientInstance.getRetrofitInstance(token_api).create(ApiService.class);
        Call tokenCall = tokenApi.getUserToken("application/json;charset=utf8", token_body);

        tokenCall.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

                if(response.isSuccessful()){
                    Log.d("json","Successful");

                    Utils.user_token = response.headers().get("X-Subject-Token");
                    //Log.d("json",""+headerList);

                    Log.d("json",""+Utils.user_token);



                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {

                Log.d("json","Failed");


            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_home, container, false);

         getUserToken();

         img_heartbeat = v.findViewById(R.id.img_heartbeat);
        Glide
                .with(v.getContext())
                .load(R.mipmap.heart_beat)
                //.placeholder(R.drawable.placeholder1)
                .into(img_heartbeat);



        txt_heart = v.findViewById(R.id.txt_heart);
        txt_kidney = v.findViewById(R.id.txt_kidney);
        txt_diabete = v.findViewById(R.id.txt_diabete);
        txt_cardi = v.findViewById(R.id.txt_cardi);
        txt_breast = v.findViewById(R.id.txt_breastcancer);
        txt_coming = v.findViewById(R.id.txt_comingsoon);
        txt_liver = v.findViewById(R.id.txt_liver);
        txt_hyper = v.findViewById(R.id.txt_hyper);


        card_heart = v.findViewById(R.id.card_heart);
        card_kidney = v.findViewById(R.id.card_kidney);
        card_diabete = v.findViewById(R.id.card_diabetes);
        card_cardi = v.findViewById(R.id.card_cardi);
        card_breastcancer = v.findViewById(R.id.card_breastcancer);
        card_liver = v.findViewById(R.id.card_liver);
        card_hyper = v.findViewById(R.id.card_hyper);


        card_heart.setOnClickListener(this);
        card_kidney.setOnClickListener(this);
        card_diabete.setOnClickListener(this);
        card_cardi.setOnClickListener(this);
        card_breastcancer.setOnClickListener(this);
        card_liver.setOnClickListener(this);
        card_hyper.setOnClickListener(this);



        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/avenirnextregular.ttf");
        txt_heart.setTypeface(font);
        txt_kidney.setTypeface(font);
        txt_diabete.setTypeface(font);
        txt_cardi.setTypeface(font);
        txt_breast.setTypeface(font);
        txt_coming.setTypeface(font);
        txt_hyper.setTypeface(font);
        txt_liver.setTypeface(font);



        return  v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id){

            case R.id.card_heart:
                startActivity(new Intent(getActivity().getBaseContext(), HeartAttackActivity.class));
                break;

            case R.id.card_kidney:
                startActivity(new Intent(getActivity().getBaseContext(), ChronicKidneyActivity.class));
                break;

            case R.id.card_diabetes:
                startActivity(new Intent(getActivity().getBaseContext(), DiabetesActivity.class));
                break;

            case R.id.card_cardi:
                startActivity(new Intent(getActivity().getBaseContext(), CardivascularActivity.class));
                break;

            case R.id.card_breastcancer:
                startActivity(new Intent(getActivity().getBaseContext(), BreastCancerActivity.class));
                break;

            case R.id.card_hyper:
                startActivity(new Intent(getActivity().getBaseContext(), HypertensiveActivity.class));
                break;

            case R.id.card_liver:
                startActivity(new Intent(getActivity().getBaseContext(), LiverDiseaseActivity.class));
                break;

                default:
                    break;

        }


    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}

package com.example.diseasedetector.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.diseasedetector.R;
import com.example.diseasedetector.activities.AddPatientActivity;
import com.example.diseasedetector.adapter.PatientRecordAdapter;
import com.example.diseasedetector.model.Data;
import com.example.diseasedetector.model.PatientData;
import com.example.diseasedetector.model.PatientRecord;
import com.example.diseasedetector.network.ApiService;
import com.example.diseasedetector.network.RetrofitClientInstance;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PatientRecordFragment extends Fragment {

    private FloatingActionButton fab;
    private String get_patient_api = "http://159.138.11.211";

    private PatientData patientData = null;
    private List<Data> data = null;
    private RecyclerView recyclerView;
    private PatientRecordAdapter adapter;
    private AppCompatTextView tv_nohistory, tv_gg;
    private AppCompatImageView img_nohistory;
    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        getDataFromServer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_patient_record, container, false);

        tv_nohistory = view.findViewById(R.id.txt_patient_nohistory);
        img_nohistory = view.findViewById(R.id.img_patient_nohistory);

        recyclerView = view.findViewById(R.id.recycler_view_patient);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(layoutManager);

        getDataFromServer();


        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.getContext().startActivity(new Intent(getActivity().getApplicationContext(), AddPatientActivity.class));
            }
        });

        return view;
    }

    private void getDataFromServer(){

        final ApiService getpApi = RetrofitClientInstance.getRetrofitInstance(get_patient_api).create(ApiService.class);
        Call<PatientData> getPCall = getpApi.getAllPatient();

        getPCall.enqueue(new Callback<PatientData>() {
            @Override
            public void onResponse(Call<PatientData> call, Response<PatientData> response) {


                if(response.isSuccessful()){

                    tv_nohistory.setVisibility(View.INVISIBLE);
                    img_nohistory.setVisibility(View.INVISIBLE);

                    patientData = response.body();
                    data = patientData.getData();

                    adapter = new PatientRecordAdapter(getActivity().getApplicationContext(), data);
                    recyclerView.setAdapter(adapter);

                    Log.d("json", "Name : "+data.get(0).getName());

                }

            }

            @Override
            public void onFailure(Call<PatientData> call, Throwable t) {

                Log.d("json", t.getMessage());
                Toast.makeText(getActivity().getBaseContext(), "Error while loading data!", Toast.LENGTH_SHORT).show();


            }
        });

    }

}

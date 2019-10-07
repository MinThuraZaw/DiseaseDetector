package com.example.diseasedetector.fragments;


import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.diseasedetector.R;
import com.example.diseasedetector.adapter.NewsAdapter;
import com.example.diseasedetector.dialog.CheckingDialog;
import com.example.diseasedetector.model.NewsModel;
import com.example.diseasedetector.network.ApiService;
import com.example.diseasedetector.network.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class NewsFragment extends Fragment {

    private NewsModel data = null;
    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private static final String BASE_URL = "https://newsapi.org";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_news, container, false);

        recyclerView = v.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(layoutManager);

        FragmentManager fragmentManager  = getActivity().getSupportFragmentManager();
        final CheckingDialog checkingDialog = CheckingDialog.getInstance(2);

        checkingDialog.show(fragmentManager,"check");


        ApiService service = RetrofitClientInstance.getRetrofitInstance(BASE_URL).create(ApiService.class);
        Call<NewsModel> call = service.getNews();

        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {

                checkingDialog.dismiss();

                data = response.body();
                Log.d("news", "Success");

                adapter = new NewsAdapter(getActivity().getBaseContext(), data);
                recyclerView.setAdapter(adapter);

            }


            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {

                Log.d("news", "Failed" +t.getMessage());

                checkingDialog.removeChecking();
                checkingDialog.setCancelable(true);
                checkingDialog.setMainText("Something has wrong! \n Try again!");




            }
        });

        return v;

    }


}

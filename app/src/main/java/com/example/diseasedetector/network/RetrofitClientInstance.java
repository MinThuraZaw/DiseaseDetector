package com.example.diseasedetector.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static final String HA_API = "https://e77bab36de434d4bbb37a4b0588b64b9.apigw.ap-southeast-1.huaweicloud.com";


    public static Retrofit getRetrofitInstance(String url) {

            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        return retrofit;
    }
}

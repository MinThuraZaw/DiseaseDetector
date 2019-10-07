package com.example.diseasedetector.network;

import com.example.diseasedetector.model.HeartAttackModel;
import com.example.diseasedetector.model.NewsModel;
import com.example.diseasedetector.model.MainModel;
import com.example.diseasedetector.model.PatientData;
import com.example.diseasedetector.model.PatientRecord;
import com.example.diseasedetector.model.User;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {


    @GET("/v2/top-headlines?country=us&category=health&apiKey=191c922c27044dfe9b0773d6bd6f9a56")
    Call<NewsModel> getNews();

    @POST("/v3/auth/tokens")
    Call<User> getUserToken(@Header("Content-Type") String contentType,
                            @Body String body);


    @POST("/v1/infers/26931c55-718f-464a-8b89-7eb820f69a53")
    Call<List<HeartAttackModel>> getHeartAttack(
            @Header("Content-Type") String contentType,
            @Header("X-Auth-Token") String user_token,
            @Body String body);

    //@Headers({"Content-Type: application/json", "X-Auth-Token: "})
    @POST("/v1/infers/fe54a8cd-eb17-4f67-baa5-1b215c7ac200")
    Call<MainModel> getDiabete(
            @Header("Content-Type") String contentType,
            @Header("X-Auth-Token") String user_token,
            @Body String body);

    @POST("/v1/infers/97079afc-c065-4f82-9b9d-6bcfe6f824ed")
    Call<MainModel> getBC(
            @Header("Content-Type") String contentType,
            @Header("X-Auth-Token") String user_token,
            @Body String body);

    @POST("/v1/infers/199cf03a-c269-4186-817c-9330093ec445")
    Call<MainModel> getCardio(
            @Header("Content-Type") String contentType,
            @Header("X-Auth-Token") String user_token,
            @Body String body);

    @POST("/v1/infers/3a8b34cf-c5b3-4c86-9d34-5b9e239ef1e8")
    Call<MainModel> getKidney(
            @Header("Content-Type") String contentType,
            @Header("X-Auth-Token") String user_token,
            @Body String body);

    @POST("/v1/infers/388ef7d3-c30b-40c6-bc30-6a8df259dbb6")
    Call<MainModel> getHypertensive(
            @Header("Content-Type") String contentType,
            @Header("X-Auth-Token") String user_token,
            @Body String body);

    @POST("/v1/infers/a81bae03-74d4-4560-9408-313f6018af41")
    Call<MainModel> getLiver(
            @Header("Content-Type") String contentType,
            @Header("X-Auth-Token") String user_token,
            @Body String body);

    @POST("/disease/public/api/patientrecord")
    Call<PatientRecord> AddPatient(@Header("Content-Type") String contentType, @Body String body);

    @GET("/disease/public/api/patientrecorddata")
    Call<PatientData> getAllPatient();



}

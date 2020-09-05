package com.example.news_app.Clinets;

import com.example.news_app.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API_interface {

    @GET("top-headlines")
    Call<ResponseModel> getLatestNews(

            @Query("country") String country,
            @Query("apiKey") String apiKey


    );

    @GET("everything")
    Call<ResponseModel> getSpecificNews(

            @Query("q") String query,
            @Query("apiKey") String apiKey


    );
}
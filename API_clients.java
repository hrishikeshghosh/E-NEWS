package com.example.news_app.Clinets;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API_clients {

    public static  final String BASE_URL="https://newsapi.org/v2/";
    private static Retrofit retrofit=null;
    private static API_clients api_clients;

    private API_clients(){

        retrofit= new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static synchronized API_clients getInstance(){

        if (api_clients==null){
            api_clients=new API_clients();
        }
        return api_clients;

    }

    public API_interface getApi(){

        return  retrofit.create(API_interface.class);

    }
}



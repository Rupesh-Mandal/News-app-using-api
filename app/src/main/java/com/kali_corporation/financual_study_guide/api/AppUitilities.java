package com.kali_corporation.financual_study_guide.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppUitilities {
    private static Retrofit retrofit=null;

    public static ApiInterFace getApiInterFace(){
        if (retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(ApiInterFace.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(ApiInterFace.class);
    }

}

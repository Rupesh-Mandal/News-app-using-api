package com.kali_corporation.financual_study_guide.api;

import com.kali_corporation.financual_study_guide.modle.MainModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterFace {
    String BASE_URL="https://newsapi.org/v2/";


    @GET("top-headlines")
    Call<MainModel> getNews(
        @Query("country") String country,
        @Query("pageSize") int pageSize,
        @Query("apiKey") String apiKey
        );


    @GET("top-headlines")
    Call<MainModel> getCategoryNews(
            @Query("country") String country,
            @Query("category") String category,
            @Query("pageSize") int pageSize,
            @Query("apiKey") String apiKey
    );

      @GET("everything")
    Call<MainModel> getFinance(
            @Query("pageSize") int pageSize,
            @Query("q") String q,
            @Query("apiKey") String apiKey
    );



}

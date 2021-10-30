package com.ahnsong.studymobile.network;

import com.ahnsong.studymobile.data.opendata.Corona;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenDataService {

    @GET("/openapi/service/rest/Covid19/getCovid19InfStateJson")
    Call<Corona> getCoronaInfo(@Query("serviceKey") String serviceKey,
                               @Query("pageNo") int num,
                               @Query("numOfRows") int rows,
                               @Query("startCreateDt") int startDate,
                               @Query("endCreateDt") int endDate);
}

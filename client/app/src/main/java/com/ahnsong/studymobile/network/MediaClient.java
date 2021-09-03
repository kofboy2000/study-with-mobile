package com.ahnsong.studymobile.network;

import com.ahnsong.studymobile.base.Const;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MediaClient {

    public static MediaService getApiService() {
        return getInstance().create(MediaService.class);
    }

    private static Retrofit getInstance() {
        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder()
                .baseUrl(Const.LiveMedia.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}

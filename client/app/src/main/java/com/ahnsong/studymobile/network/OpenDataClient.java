package com.ahnsong.studymobile.network;

import com.ahnsong.studymobile.base.Consts;
import com.tickaroo.tikxml.TikXml;
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory;

import retrofit2.Retrofit;

public class OpenDataClient {

    public static OpenDataService getApiService() {
        return getInstance().create(OpenDataService.class);
    }

    private static Retrofit getInstance() {
        TikXml tikXml = new TikXml.Builder()
                .exceptionOnUnreadXml(false).build();
        return new Retrofit.Builder()
                .baseUrl("http://openapi.data.go.kr")
                .addConverterFactory(TikXmlConverterFactory.create(tikXml))
                .build();
    }
}

package com.ahnsong.studymobile.network;

import com.ahnsong.studymobile.base.Const;
import com.ahnsong.studymobile.data.naver.Channel;
import com.ahnsong.studymobile.data.naver.Content;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MediaService {

    @Headers({"Content-Type: application/json"})
    @POST(Const.LiveMedia.CREATE_CHANNEL)
    Call<Content> createChannel(@Header("x-ncp-apigw-timestamp") long timestamp,
                                @Header("x-ncp-iam-access-key") String key,
                                @Header("x-ncp-apigw-signature-v2") String signature,
                                @Header("x-ncp-region_code") String region,
                                @Body Channel channel);

    @GET("/api/v2/channels/{channelId}")
    Call<Content> getChannelInfo(@Header("x-ncp-apigw-timestamp") long timestamp,
                                 @Header("x-ncp-iam-access-key") String key,
                                 @Header("x-ncp-apigw-signature-v2") String signature,
                                 @Header("x-ncp-region_code") String region,
                                 @Path("channelId") String id);
}

package com.ahnsong.studymobile.base;

import com.ahnsong.studymobile.BuildConfig;

public final class Const {

    public static final class LiveMedia {
        public static final String BASE_URL = "https://livestation.apigw.ntruss.com";
        public static final String API_ACCESS_KEY = BuildConfig.NAVER_API_KEY;
        public static final String API_SECRET_KEY = BuildConfig.NAVER_SECRETE_API_KEY;

        public static final String REGION = "KR";

        public static final String CREATE_CHANNEL = "/api/v2/channels";
        public static final String QUERY_CHANNEL = "/api/v2/channels/";
        public static final String GET_CHANNEL_INFO = "/api/v2/channels/{channelId}";

        public static final String BASE_HLS_URL = BuildConfig.BASE_HLS_URL;
        public static final String BASE_CHANNEL_ID = BuildConfig.BASE_CHANNEL_ID;
        public static final String FHD = "/1080p-9-16/playlist.m3u8";
        public static final String HD = "/720p-9-16/playlist.m3u8";
        public static final String SD = "/480p-9-16/playlist.m3u8";

        public static final int QUALITY_SET_ID = 397; // custom set id

        public static final String BASE_RTMP_URL = BuildConfig.BASE_RTMP_URL;

        public static final int CDN_ID = 7598472;
        public static final String CDN_TYPE_PLUS = "CDN_PLUS";
        public static final String CDN_SERVICE_NAME = BuildConfig.CDN_SERVICE_NAME;
        public static final String CDN_SERVICE_DOMAIN = BuildConfig.CDN_SERVICE_DOMAIN;
    }

    public static class Storage {
        public static final String PROFILE = "profiles/";
        public static final String SLIDE = "slide/";
    }
}

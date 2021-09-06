package com.ahnsong.studymobile.streaming;

import com.ahnsong.studymobile.data.MyClass;

public class LiveStreamManager {
    private static final String TAG = "LiveMedia";

    private LiveStreamService streamService;
    private LiveStreamCallback streamCallback;


    public LiveStreamManager(LiveStreamService streamService, LiveStreamCallback streamCallback) {
        this.streamService = streamService;
        this.streamCallback = streamCallback;
    }

    public void setStreamData(MyClass streamData) {
        streamService.setStreamingData(streamData);
    }

    public void createStream() {
        streamService.setStream();
    }
}

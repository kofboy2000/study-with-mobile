package com.ahnsong.studymobile.streaming;

import com.ahnsong.studymobile.data.Streaming;

public interface LiveStreamService {

    void setStreamingData(Streaming streaming);
    void setStream();
    void getStreamKey(String channelId);
}

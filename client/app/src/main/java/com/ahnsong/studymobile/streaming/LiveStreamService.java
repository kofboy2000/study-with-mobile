package com.ahnsong.studymobile.streaming;

import com.ahnsong.studymobile.data.MyClass;

public interface LiveStreamService {

    void setStreamingData(MyClass streaming);
    void setStream();
    void getStreamKey(String channelId);
}

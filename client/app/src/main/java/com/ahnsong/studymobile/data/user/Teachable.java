package com.ahnsong.studymobile.data.user;

import com.ahnsong.studymobile.data.Streaming;

public interface Teachable {
    void startStreaming(Streaming streaming);
    void endStreaming(Streaming streaming);
}

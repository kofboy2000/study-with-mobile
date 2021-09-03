package com.ahnsong.studymobile.data.livestation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Channel {

    private String channelId;
    private String channelName;
    private String instanceNo;
    private int qualitySetId;
    private String qualitySetName;
    private String outputProtocol; // Default set: HLS, DASH
    private String channelStatus; // CREATING, READY, PUBLISH, DELETED
    private boolean isRecording;
    private boolean useDvr;
    private boolean immediateOnAir;
    private int timemachineMin;
    private CDN cdn;
    private String publishUrl;
    private String streamKey;
    private int totalPublishSeconds;
    private boolean alertOn;
    private long createdTime;
}

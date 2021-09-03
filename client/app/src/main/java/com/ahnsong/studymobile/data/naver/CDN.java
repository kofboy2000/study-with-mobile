package com.ahnsong.studymobile.data.naver;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class CDN {

    private String instanceNo;
    private String serviceName;
    private boolean createCdn;
    private String cdnType;
    private String domain;
    private int cdnInstanceNo;
}

package com.ahnsong.studymobile.data.opendata;

import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

import lombok.Getter;
import lombok.Setter;

@Xml(name = "item")
@Getter
@Setter
public class Item {
    @PropertyElement
    double accDefRate;
    @PropertyElement
    int accExamCnt;
    @PropertyElement
    int accExamCompCnt;
    @PropertyElement
    int careCnt;
    @PropertyElement
    String createDt;
    @PropertyElement
    int deathCnt;
    @PropertyElement
    int decideCnt;
    @PropertyElement
    int examCnt;
    @PropertyElement
    int resultNegCnt;
    @PropertyElement
    int seq;
    @PropertyElement
    int stateDt;
    @PropertyElement
    String stateTime;
    @PropertyElement
    String updateDt;
}

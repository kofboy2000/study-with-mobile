package com.ahnsong.studymobile.ui.corona;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CoronaStat {
    int decideCnt; // DECIDE_CNT
    int deathCnt; // DEATH_CNT
    int examCnt; // EXAM_CNT
    int careCnt; // CARE_CNT
    double accDefRate; // ACC_DEF_RATE
}

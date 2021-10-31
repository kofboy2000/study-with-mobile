package com.ahnsong.studymobile.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class HomeSlide {
    private String title;
    private String imageName;
    private boolean coronaStat;
    private boolean coronaInfo;
    private boolean playable;
}

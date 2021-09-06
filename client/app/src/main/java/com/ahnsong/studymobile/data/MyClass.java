package com.ahnsong.studymobile.data;

import com.google.firebase.database.IgnoreExtraProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IgnoreExtraProperties
public class MyClass {

    private String channelid;
    private String classid;
    private String day;
    private int likes;
    private String location;
    private String month;
    private String status;
    private String profile;
    private String teacher;
    private String teacherid;
    private String title;
    private int viewers;
}
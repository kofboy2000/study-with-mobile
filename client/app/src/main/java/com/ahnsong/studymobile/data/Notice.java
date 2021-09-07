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
public class Notice {

    private String contents;
    private String date;
    private String title;
}

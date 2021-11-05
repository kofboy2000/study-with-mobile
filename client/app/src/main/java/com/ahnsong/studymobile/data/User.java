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
public class User {
    private String name;
    private String email;
    private String profile;
    private String phone;
    private String userStatus;

    private String address = "Seoul";
    private String provider = "email";
    private String subject = "Coding";
    private long fee = 30;
}

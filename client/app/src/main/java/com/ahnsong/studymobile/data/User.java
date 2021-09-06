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
    private String address;
    private String email;
    private String profile;

    private String userStatus;
    private String provider;
    private String phone;
    private String subject;
    private long fee;
}

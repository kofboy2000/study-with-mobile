package com.ahnsong.studymobile.data.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private String address;
    private int gender;
    private String profile;
    private int age;

    private boolean isTeacher;
    private String channel;
    private String subject;
    private long fee;
}

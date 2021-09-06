package com.ahnsong.studymobile.applications;

public class StudyWithMeInstance {

    private static StudyWithMeInstance sInstance;

    private String status;

    private StudyWithMeInstance() { }

    public static StudyWithMeInstance getInstance() {
        if (sInstance == null) {
            sInstance = new StudyWithMeInstance();
        }
        return sInstance;
    }

    public void setCurrentUserStatus(String status) {
        this.status = status;
    }

    public String getCurrentUserStatus() {
        return this.status;
    }
}

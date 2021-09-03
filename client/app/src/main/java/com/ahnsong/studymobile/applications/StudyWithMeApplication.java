package com.ahnsong.studymobile.applications;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

public class StudyWithMeApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(getBaseContext());
    }
}

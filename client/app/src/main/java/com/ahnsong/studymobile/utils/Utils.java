package com.ahnsong.studymobile.utils;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {
    public static StorageReference getImageReference(String type, String id) {
        String filePath = type+id;
        return FirebaseStorage.getInstance().getReference().child(filePath);
    }

    public static String getMonth() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM", Locale.KOREA);
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getDay() {
        SimpleDateFormat format1 = new SimpleDateFormat ("yyyy.MM.dd", Locale.KOREA);
        String today = format1.format(new Date());
        return today.split("\\.")[2];
    }
}

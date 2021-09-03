package com.ahnsong.studymobile.utils;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Utils {
    public static StorageReference getImageReference(String type, String id) {
        String filePath = type+id;
        return FirebaseStorage.getInstance().getReference().child(filePath);
    }

    public static String getImageReferenceString(String type, String id) {
        return type+id;
    }
}

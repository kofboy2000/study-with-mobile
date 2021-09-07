package com.ahnsong.studymobile.firebase;

import android.content.Context;

import com.ahnsong.studymobile.R;
import com.ahnsong.studymobile.base.Consts;
import com.ahnsong.studymobile.data.MyClass;
import com.ahnsong.studymobile.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseManager {
    private static final String TAG = "FirebaseManager";

    private Context context;

    public FirebaseManager(Context context) {
        this.context = context;
    }

    public void createLectureReference(FirebaseCallback callback) {
        DatabaseReference reference = FirebaseDatabase.getInstance(Consts.Database.DB_URL)
                .getReference(Consts.Database.CLASS);
        DatabaseReference newReference = reference.push();
        final String myClassKey = newReference.getKey();
        MyClass myClass = new MyClass();
        myClass.setClassid(myClassKey);
        myClass.setLocation(context.getString(R.string.seoul));
        myClass.setTitle(context.getString(R.string.today_class_title));
        myClass.setTeacher("김국어");
        myClass.setTeacherid(FirebaseAuth.getInstance().getCurrentUser().getUid());
        myClass.setMonth(Utils.getMonth());
        myClass.setDay(Utils.getDay());
        myClass.setProfile("teacher_01.png");
        myClass.setStatus("live");
        newReference.setValue(myClass)
                .addOnSuccessListener(unused -> callback.onCreateCallback(myClassKey));
    }
}

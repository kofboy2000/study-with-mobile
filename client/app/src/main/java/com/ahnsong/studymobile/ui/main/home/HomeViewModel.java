package com.ahnsong.studymobile.ui.main.home;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahnsong.studymobile.base.Consts;
import com.ahnsong.studymobile.data.MyClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {
    private static final String TAG = "HomeViewModel";

    private MutableLiveData<List<MyClass>> classData;

    public HomeViewModel() {
        classData = new MutableLiveData<>();
    }

    public MutableLiveData<List<MyClass>> getClassData() {
        return classData;
    }

    public void startReferenceClass() {
        DatabaseReference classDatabase = FirebaseDatabase.getInstance(Consts.Database.DB_URL)
                .getReference(Consts.Database.CLASS);
        classDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                List<MyClass> list = new ArrayList<>();
                for (DataSnapshot data: snapshot.getChildren()) {
                    MyClass aClass = data.getValue(MyClass.class);
                    list.add(aClass);
                }
                Log.d(TAG, "List size: " + list.size());
                classData.setValue(list);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Log.e(TAG, error.getMessage());
            }
        });
    }
}
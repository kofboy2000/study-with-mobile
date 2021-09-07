package com.ahnsong.studymobile.ui.live;

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

public class LiveStationCastViewModel extends ViewModel {
    private static final String TAG = "LiveStationCastViewModel";

    private MutableLiveData<MyClass> myClassData;
    private DatabaseReference classDatabase;

    public LiveStationCastViewModel() {
        myClassData = new MutableLiveData<>();
    }

    public MutableLiveData<MyClass> getMyClassData() {
        return myClassData;
    }

    public void startReferenceClassData(String classKey) {
        classDatabase = FirebaseDatabase.getInstance(Consts.Database.DB_URL)
                .getReference(Consts.Database.CLASS).child(classKey);
        classDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                MyClass currentClass = snapshot.getValue(MyClass.class);
                myClassData.setValue(currentClass);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Log.e(TAG, error.getMessage());
            }
        });
    }

    public void endClassData(String myClassKey) {
        Log.d(TAG, "Delete key: " + myClassKey);
        classDatabase.removeValue();
    }
}

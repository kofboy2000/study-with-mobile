package com.ahnsong.studymobile.ui.main.search;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahnsong.studymobile.base.Consts;
import com.ahnsong.studymobile.data.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SearchViewModel extends ViewModel {
    private static final String TAG = "SearchViewModel";

    private MutableLiveData<List<User>> userList;

    public SearchViewModel() {
        Log.d(TAG, "SearchViewModel");
        userList = new MutableLiveData<>();
    }

    public void startReferenceUsers() {
        DatabaseReference userReference = FirebaseDatabase.getInstance(Consts.Database.DB_URL)
                .getReference(Consts.Database.USERS);
        userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                List<User> list = new ArrayList<>();
                for (DataSnapshot data: snapshot.getChildren()) {
                    User user = data.getValue(User.class);
                    list.add(user);
                }
                Log.d(TAG, "List size: " + list.size());
                userList.setValue(list);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Log.e(TAG, error.getMessage());
            }
        });
    }

    public MutableLiveData<List<User>> getUserList() {
        return userList;
    }
}
package com.ahnsong.studymobile.ui.main.settings;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahnsong.studymobile.base.Consts;
import com.ahnsong.studymobile.data.Notice;
import com.ahnsong.studymobile.data.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SettingsViewModel extends ViewModel {
    private static final String TAG = "SettingsViewModel";

    private DatabaseReference userReference;
    private DatabaseReference noticeReference;

    private MutableLiveData<User> userLiveData;
    private MutableLiveData<List<Notice>> noticeLiveData;

    public SettingsViewModel() {
        userLiveData = new MutableLiveData<>();
        noticeLiveData = new MutableLiveData<>();
    }

    public void startReferenceUserData() {
        String currentUserId = Objects.requireNonNull(
                FirebaseAuth.getInstance().getCurrentUser()).getUid();
        userReference = FirebaseDatabase.getInstance(Consts.Database.DB_URL)
                .getReference(Consts.Database.USERS).child(currentUserId);
        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                User currentUser = snapshot.getValue(User.class);
                userLiveData.setValue(currentUser);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Log.e(TAG, error.getMessage());
            }
        });
    }

    public void startReferenceNoticeData() {
        noticeReference = FirebaseDatabase.getInstance(Consts.Database.DB_URL)
                .getReference(Consts.Database.NOTICE);
        noticeReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Notice> noticeList = new ArrayList<>();
                for (DataSnapshot data: snapshot.getChildren()) {
                    Notice notice = data.getValue(Notice.class);
                    noticeList.add(notice);
                }
                Log.d(TAG, "Notice list : " + noticeList.size());
                noticeLiveData.setValue(noticeList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, error.getMessage());
            }
        });
    }

    public MutableLiveData<User> getUserLiveData() {
        return userLiveData;
    }

    public MutableLiveData<List<Notice>> getNoticeLiveData() {
        return noticeLiveData;
    }
}
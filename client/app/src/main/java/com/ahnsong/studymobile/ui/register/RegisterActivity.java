package com.ahnsong.studymobile.ui.register;

import android.util.Log;
import android.widget.Toast;

import com.ahnsong.studymobile.R;
import com.ahnsong.studymobile.applications.StudyWithMeInstance;
import com.ahnsong.studymobile.base.BaseActivity;
import com.ahnsong.studymobile.base.Consts;
import com.ahnsong.studymobile.data.User;
import com.ahnsong.studymobile.databinding.ActivityRegisterBinding;
import com.ahnsong.studymobile.ui.main.MainActivity;
import com.ahnsong.studymobile.utils.ProgressDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends BaseActivity {
    private static final String TAG = "RegisterActivity";
    private ActivityRegisterBinding binding;

    private String password;
    private User userData;
    private ProgressDialog progressDialog;

    @Override
    protected int setLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void initData() {
        binding.doneButton.setOnClickListener(view -> {
            completeRegisterIfPossible();
        });
    }

    private void completeRegisterIfPossible() {
        removeCachedUser();
        createUser();
        signUpUser();
    }

    private void removeCachedUser() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signOut();
        Log.d(TAG, "Signout cached user");
    }

    private void createUser() {
        String name = binding.nameTextInput.getText().toString().trim();
        String email = binding.emailInput.getText().toString().trim();

        userData = new User();
        userData.setEmail(email);
        userData.setName(name);
        boolean isTeacher = binding.radioButtonTeacher.isChecked();
        if (isTeacher) {
            userData.setProfile("teacher_07.png");
            userData.setUserStatus(Consts.Database.USER_STATUS_TEACHER);
            StudyWithMeInstance.getInstance()
                    .setCurrentUserStatus(Consts.Database.USER_STATUS_TEACHER);
        } else {
            userData.setProfile("student_04.png");
            userData.setUserStatus(Consts.Database.USER_STATUS_STUDNET);
            StudyWithMeInstance.getInstance()
                    .setCurrentUserStatus(Consts.Database.USER_STATUS_STUDNET);
        }
        password = binding.passwordInput.getText().toString().trim();
    }

    private void signUpUser() {
        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(userData.getEmail(), password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "createUserWithEmail:success");
                        Log.d(TAG, "user information will be saved in database");
                        updateUserProfile();
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        progressDialog.dismiss();
                        Toast.makeText(this, R.string.user_create_error,
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void updateUserProfile() {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                    .setDisplayName(userData.getName()).build();
            user.updateProfile(profileUpdate);
            db.child(Consts.Database.USERS).child(user.getUid()).setValue(userData)
                    .addOnSuccessListener(aVoid -> {
                        progressDialog.dismiss();
                        startAndClearAllActivity(MainActivity.class);
                    })
                    .addOnFailureListener(e -> {
                        progressDialog.dismiss();
                    });
        }
    }
}
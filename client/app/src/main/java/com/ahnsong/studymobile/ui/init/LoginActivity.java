package com.ahnsong.studymobile.ui.init;

import android.util.Log;
import android.widget.Toast;

import com.ahnsong.studymobile.R;
import com.ahnsong.studymobile.applications.StudyWithMeInstance;
import com.ahnsong.studymobile.base.BaseActivity;
import com.ahnsong.studymobile.base.Consts;
import com.ahnsong.studymobile.databinding.ActivityLoginBinding;
import com.ahnsong.studymobile.ui.main.MainActivity;
import com.ahnsong.studymobile.ui.register.RegisterActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginActivity";
    private ActivityLoginBinding binding;
    private FirebaseAuth firebaseAuth;

    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void initData() {
        firebaseAuth = FirebaseAuth.getInstance();
        binding.loginButton.setOnClickListener(view -> {
            String email = binding.emailInput.getText().toString().trim();
            String password = binding.passwordInput.getText().toString().trim();
            if (!email.isEmpty() && !password.isEmpty())
                signIn(email, password);
        });
        binding.registerButton.setOnClickListener(view -> startActivity(RegisterActivity.class));
    }

    private void signIn(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "Login complete");
                        setUserStatus();
                        startAndClearAllActivity(MainActivity.class);
                    } else {
                        Log.e(TAG, "Login error : " + task.getException());
                        binding.passwordInput.setError(getResources().getString(R.string.password_error));
                        Toast.makeText(LoginActivity.this, "로그인 오류", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void setUserStatus() {
        String email = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getEmail();
        assert email != null;
        if (email.startsWith(Consts.Database.USER_STATUS_TEACHER)) {
            StudyWithMeInstance.getInstance()
                    .setCurrentUserStatus(Consts.Database.USER_STATUS_TEACHER);
        } else {
            StudyWithMeInstance.getInstance()
                    .setCurrentUserStatus(Consts.Database.USER_STATUS_STUDNET);
        }
    }
}

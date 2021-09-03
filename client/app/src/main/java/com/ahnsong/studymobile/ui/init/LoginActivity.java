package com.ahnsong.studymobile.ui.init;

import android.util.Log;
import android.widget.Toast;

import com.ahnsong.studymobile.R;
import com.ahnsong.studymobile.base.BaseActivity;
import com.ahnsong.studymobile.databinding.ActivityLoginBinding;
import com.ahnsong.studymobile.ui.main.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginActivity";
    private ActivityLoginBinding binding;

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
        binding.loginButton.setOnClickListener(v->loginAsStudent());
    }

    private void loginAsStudent() {
        String email = "student1@gmail.com";
        String password = "student1";
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult ->
                    startAndClearAllActivity(MainActivity.class))
                .addOnFailureListener(authResult -> {
                    Log.e(TAG, "Login error : " + authResult.getMessage());
                    Toast.makeText(LoginActivity.this, "로그인 오류",
                            Toast.LENGTH_SHORT).show();
                });
    }
}

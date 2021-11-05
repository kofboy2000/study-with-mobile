package com.ahnsong.studymobile.ui.register;

import com.ahnsong.studymobile.R;
import com.ahnsong.studymobile.base.BaseActivity;
import com.ahnsong.studymobile.databinding.ActivityRegisterBinding;

public class RegisterActivity extends BaseActivity {
    private static final String TAG = "RegisterActivity";
    private ActivityRegisterBinding binding;

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
    }
}
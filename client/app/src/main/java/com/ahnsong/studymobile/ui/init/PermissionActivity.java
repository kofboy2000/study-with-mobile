package com.ahnsong.studymobile.ui.init;

import android.Manifest;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahnsong.studymobile.R;
import com.ahnsong.studymobile.base.BaseActivity;
import com.ahnsong.studymobile.databinding.ActivityPermissionBinding;

public class PermissionActivity extends BaseActivity {
    private static final String TAG = "PermissionActivity";

    private static final int REQUEST_CODE = 20210828;

    private ActivityPermissionBinding binding;

    private final String[] permissions = new String[]{
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    @Override
    protected int setLayout() {
        return R.layout.activity_permission;
    }

    @Override
    protected void initView() {
        binding = ActivityPermissionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void initData() {
        if (hasPermissions(permissions))
            startAndClearAllActivity(LoginActivity.class);
        binding.btnNext.setOnClickListener(view -> doRequestPermission());
        setupView();
    }

    private void setupView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setAutoMeasureEnabled(true);
        binding.rcvPermission.setLayoutManager(layoutManager);

        PermissionAdapter adapter = new PermissionAdapter(this);
        binding.rcvPermission.setAdapter(adapter);
    }

    private void doRequestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                + ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                + ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                + ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE);
        } else {
            startAndClearAllActivity(LoginActivity.class);
        }
    }

    private boolean hasPermissions(@NonNull String... permissions) {
        for(String permission: permissions) {
            if (ActivityCompat.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            startAndClearAllActivity(LoginActivity.class);
        } else {
            Log.e(TAG, "Unexpected request code");
        }
    }
}

package com.ahnsong.studymobile.ui.init;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.ahnsong.studymobile.R;
import com.ahnsong.studymobile.base.BaseActivity;

public class SplashActivity extends BaseActivity {
    private static final String TAG = "SplashActivity";

    @Override
    protected int setLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        Log.d(TAG, "initView");
    }

    @Override
    protected void initData() {
        new Handler().postDelayed(this::doNext, 2000);
    }

    private void doNext() {
        if (hasPermissions(this)) {
            startAndClearAllActivity(LoginActivity.class);
        } else {
            startAndClearAllActivity(PermissionActivity.class);
        }
    }

    private boolean hasPermissions(@NonNull Context context) {
        final String[] permissions = new String[] {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO
        };
        for (String permission: permissions) {
            if (ActivityCompat.checkSelfPermission(context, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
}

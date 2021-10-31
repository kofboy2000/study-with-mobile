package com.ahnsong.studymobile.utils;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Window;

import androidx.annotation.NonNull;

import com.ahnsong.studymobile.R;

public class ProgressDialog extends Dialog {
    private static final String TAG = "ProgressDialog";

    public ProgressDialog(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.progress_dialog);
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawable(null);
        }
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "Ignore back button");
    }
}

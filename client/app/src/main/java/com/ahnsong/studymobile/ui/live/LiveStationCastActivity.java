package com.ahnsong.studymobile.ui.live;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.WindowManager;

import com.ahnsong.studymobile.R;
import com.ahnsong.studymobile.base.BaseActivity;
import com.ahnsong.studymobile.base.Const;
import com.ahnsong.studymobile.databinding.ActivityLiveStationCastBinding;
import com.pedro.rtmp.utils.ConnectCheckerRtmp;
import com.pedro.rtplibrary.rtmp.RtmpCamera1;

import org.jetbrains.annotations.NotNull;

public class LiveStationCastActivity extends BaseActivity implements ConnectCheckerRtmp, SurfaceHolder.Callback {
    private static final String TAG = "SWM-LiveStationCastActivity";

    private static final int NUM_OF_RETRIES = 10;

    private ActivityLiveStationCastBinding binding;
    private RtmpCamera1 rtmpCamera;

    @Override
    protected int setLayout() {
        return R.layout.activity_live_station_cast;
    }

    @Override
    protected void initView() {
        binding = ActivityLiveStationCastBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        rtmpCamera = new RtmpCamera1(binding.liveCastScreen, this);
        rtmpCamera.setReTries(NUM_OF_RETRIES);
        binding.liveCastScreen.getHolder().addCallback(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        setupWindow();
    }

    private void setupWindow() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected void initData() {
        binding.btnEndCast.setOnClickListener(v-> {
            endStream();
            finish();
        });
        startStreamIfReady();
    }

    private void startStreamIfReady() {
        String liveUrl = getStreamingUrl("a55vjhgtdvhlgb17ckj8pc3qsmxuklb5");
        new Handler().postDelayed(() -> {
            if (rtmpCamera.prepareAudio() && rtmpCamera.prepareVideo()) {
                rtmpCamera.startStream(liveUrl);
            }
        }, 2000);
    }

    private String getStreamingUrl(String streamKey) {
        return Const.LiveMedia.BASE_RTMP_URL + streamKey;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        Log.d(TAG, "surfaceCreated");
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        Log.d(TAG, "surfaceChanged");
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        Log.d(TAG, "surfaceDestroyed");
    }

    @Override
    public void onAuthErrorRtmp() {

    }

    @Override
    public void onAuthSuccessRtmp() {

    }

    @Override
    public void onConnectionFailedRtmp(@NotNull String s) {
        Log.e(TAG, "onConnectionFailedRtmp: " + s);
    }

    @Override
    public void onConnectionStartedRtmp(@NotNull String s) {
        Log.d(TAG, "onConnectionStartedRtmp: " + s);
    }

    @Override
    public void onConnectionSuccessRtmp() {

    }

    @Override
    public void onDisconnectRtmp() {

    }

    @Override
    public void onNewBitrateRtmp(long l) {

    }

    @Override
    public void onDestroy() {
        endStream();
        super.onDestroy();
    }

    private void endStream() {
        if (rtmpCamera.isStreaming()) {
            rtmpCamera.stopStream();
            rtmpCamera.stopPreview();
        }
    }
}
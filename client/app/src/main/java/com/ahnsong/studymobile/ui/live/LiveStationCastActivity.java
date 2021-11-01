package com.ahnsong.studymobile.ui.live;

import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.ahnsong.studymobile.R;
import com.ahnsong.studymobile.applications.GlideApp;
import com.ahnsong.studymobile.base.BaseActivity;
import com.ahnsong.studymobile.base.Consts;
import com.ahnsong.studymobile.databinding.ActivityLiveStationCastBinding;
import com.ahnsong.studymobile.utils.Utils;
import com.google.firebase.storage.StorageReference;
import com.pedro.rtmp.utils.ConnectCheckerRtmp;
import com.pedro.rtplibrary.rtmp.RtmpCamera1;

import org.jetbrains.annotations.NotNull;

public class LiveStationCastActivity extends BaseActivity implements ConnectCheckerRtmp, SurfaceHolder.Callback {
    private static final String TAG = "SWM-LiveStationCastActivity";

    private static final int NUM_OF_RETRIES = 10;

    private ActivityLiveStationCastBinding binding;
    private LiveStationCastViewModel castViewModel;
    private RtmpCamera1 rtmpCamera;
    private String myClassKey;

    @Override
    protected int setLayout() {
        return R.layout.activity_live_station_cast;
    }

    @Override
    protected void initView() {
        binding = ActivityLiveStationCastBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        castViewModel = new ViewModelProvider(this).get(LiveStationCastViewModel.class);

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
        myClassKey = getIntent().getStringExtra(Consts.Key.LIVE_CHANNEL_KEY);
        binding.btnEndCast.setOnClickListener(v-> {
            endStream();
            finish();
        });
        startReferenceClassData(myClassKey);
        startStreamIfReady();
    }

    private void startReferenceClassData(String key) {
        castViewModel.getMyClassData().observe(this, myClass -> {
            binding.tvLiveTitle.setText(myClass.getTitle());
            binding.tvTeacherName.setText(myClass.getTeacher());
            StorageReference ref = Utils.getImageReference(Consts.Storage.PROFILE, myClass.getProfile());
            GlideApp.with(this)
                    .load(ref)
                    .placeholder(R.color.colorGrey)
                    .into(binding.imgLiveProfile);

        });
        castViewModel.startReferenceClassData(key);
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
        return Consts.LiveMedia.BASE_RTMP_URL + streamKey;
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

    private void endClassData() {
        castViewModel.endClassData(myClassKey);
    }

    private void endStream() {
        if (rtmpCamera.isStreaming()) {
            rtmpCamera.stopStream();
            rtmpCamera.stopPreview();
        }
        endClassData();
    }
}
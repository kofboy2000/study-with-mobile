package com.ahnsong.studymobile.ui.live;

import android.util.Log;
import android.view.WindowManager;

import androidx.lifecycle.ViewModelProvider;

import com.ahnsong.studymobile.R;
import com.ahnsong.studymobile.applications.GlideApp;
import com.ahnsong.studymobile.base.BaseActivity;
import com.ahnsong.studymobile.base.Consts;
import com.ahnsong.studymobile.databinding.ActivityLiveStationWatchBinding;
import com.ahnsong.studymobile.utils.Utils;
import com.google.firebase.storage.StorageReference;
import com.potyvideo.library.AndExoPlayerView;

public class LiveStationWatchActivity extends BaseActivity {
    private static final String TAG = "LiveStationWatchActivity";

    private ActivityLiveStationWatchBinding binding;
    private LiveStationWatchViewModel watchViewModel;

    private AndExoPlayerView playerView;
    private String myClassKey;

    @Override
    protected int setLayout() {
        return R.layout.activity_live_station_watch;
    }

    @Override
    protected void initView() {
        binding = ActivityLiveStationWatchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        watchViewModel = new ViewModelProvider(this).get(LiveStationWatchViewModel.class);
        playerView = binding.exoPlayerView;
    }

    @Override
    protected void initData() {
        myClassKey = getIntent().getStringExtra(Consts.Key.LIVE_CHANNEL_KEY);
        binding.btnExitCast.setOnClickListener(v->finish());
        startReferenceClassData(myClassKey);
        startStreamingIfReady();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupWindow();
    }

    private void setupWindow() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    private void startReferenceClassData(String myClassKey) {
        watchViewModel.getMyClassData().observe(this, myClass -> {
            binding.tvLiveTitle.setText(myClass.getTitle());
            binding.tvTeacherName.setText(myClass.getTeacher());
            StorageReference ref = Utils.getImageReference(Consts.Storage.PROFILE, myClass.getProfile());
            GlideApp.with(this)
                    .load(ref)
                    .placeholder(R.color.colorGrey)
                    .into(binding.imgLiveProfile);
        });
        watchViewModel.startReferenceClassData(myClassKey);
    }

    private void startStreamingIfReady() {
        String hls = Consts.LiveMedia.BASE_HLS_URL + Consts.LiveMedia.BASE_CHANNEL_ID + Consts.LiveMedia.HD;
        Log.d(TAG, "Play url: " + hls);
        playerView.setSource(hls);
        playerView.setExoPlayerCallBack(() -> {
            Log.e(TAG, "Error occured");

        });
        playerView.setPlayWhenReady(true);
    }
}
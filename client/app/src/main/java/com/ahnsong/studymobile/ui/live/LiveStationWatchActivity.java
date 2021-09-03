package com.ahnsong.studymobile.ui.live;

import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.ahnsong.studymobile.R;
import com.ahnsong.studymobile.base.BaseActivity;
import com.ahnsong.studymobile.base.Const;
import com.ahnsong.studymobile.databinding.ActivityLiveStationWatchBinding;
import com.potyvideo.library.AndExoPlayerView;

public class LiveStationWatchActivity extends BaseActivity {
    private static final String TAG = "SWM-LiveStationWatchActivity";

    private ActivityLiveStationWatchBinding binding;

    private AndExoPlayerView playerView;
    private String liveCurrentStatus;

    @Override
    protected int setLayout() {
        return R.layout.activity_live_station_watch;
    }

    @Override
    protected void initView() {
        binding = ActivityLiveStationWatchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        playerView = binding.exoPlayerView;
    }

    @Override
    protected void initData() {
        startStreamingIfReady();
        binding.btnExitCast.setOnClickListener(v->finish());
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

    private void startStreamingIfReady() {
        String hls = Const.LiveMedia.BASE_HLS_URL + Const.LiveMedia.BASE_CHANNEL_ID + Const.LiveMedia.HD;
        Log.d(TAG, "Play url: " + hls);
        playerView.setSource(hls);
        playerView.setExoPlayerCallBack(() -> {
            Log.e(TAG, "Error occured");

        });
        playerView.setPlayWhenReady(true);
    }
}
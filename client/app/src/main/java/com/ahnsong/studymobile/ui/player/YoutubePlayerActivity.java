package com.ahnsong.studymobile.ui.player;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ahnsong.studymobile.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

import org.jetbrains.annotations.NotNull;

import kr.co.prnd.YouTubePlayerView;

public class YoutubePlayerActivity extends AppCompatActivity {
    private YouTubePlayerView playerView;
    private static final String VIDEO_URL = "3dNms3JYKik";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_player);
        playerView = findViewById(R.id.you_tube_player_view);
        playerView.play(VIDEO_URL, new YouTubePlayerView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(@NotNull YouTubePlayer.Provider provider,
                                                @NotNull YouTubePlayer youTubePlayer,
                                                boolean b) {
                youTubePlayer.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
                    @Override
                    public void onLoading() {

                    }

                    @Override
                    public void onLoaded(String s) {
                        youTubePlayer.play();
                    }

                    @Override
                    public void onAdStarted() {

                    }

                    @Override
                    public void onVideoStarted() {

                    }

                    @Override
                    public void onVideoEnded() {

                    }

                    @Override
                    public void onError(YouTubePlayer.ErrorReason errorReason) {

                    }
                });
            }

            @Override
            public void onInitializationFailure(@NotNull YouTubePlayer.Provider provider, @NotNull YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }
}

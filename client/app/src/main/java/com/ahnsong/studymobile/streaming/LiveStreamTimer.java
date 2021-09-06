package com.ahnsong.studymobile.streaming;

import android.os.CountDownTimer;
import android.util.Log;

public class LiveStreamTimer extends CountDownTimer {
    private static final String TAG = "LiveStreamTimer";

    public static final long TIME_LIMIT_SEC = 30;

    private LiveStreamTimerCallback streamTimerCallback;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public LiveStreamTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    public void setStreamTimerCallback(LiveStreamTimerCallback callback) {
        streamTimerCallback = callback;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        long secUntilFinished = (millisUntilFinished/1000);
        Log.d(TAG, "Time left: " + secUntilFinished);
    }

    @Override
    public void onFinish() {
        streamTimerCallback.onEventEndTick();
    }
}

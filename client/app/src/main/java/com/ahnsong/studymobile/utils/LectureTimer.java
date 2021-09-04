package com.ahnsong.studymobile.utils;

import android.os.CountDownTimer;
import android.util.Log;

public class LectureTimer extends CountDownTimer {
    private static final String TAG = "LectureTimer";

    public static final long TIME_LIMIT_SEC = 30;

    private LectureTimerCallback lectureTimerCallback;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public LectureTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    public void setLectureTimerCallback(LectureTimerCallback callback) {
        lectureTimerCallback = callback;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        long secUntilFinished = (millisUntilFinished/1000);
        Log.d(TAG, "Time left: " + secUntilFinished);
    }

    @Override
    public void onFinish() {
        lectureTimerCallback.onEventEndTick();
    }
}

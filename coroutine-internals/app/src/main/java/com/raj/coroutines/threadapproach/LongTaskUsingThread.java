package com.raj.coroutines.threadapproach;

import android.util.Log;

interface Listener {
    void onComplete(String result);
}

class LongTaskUsingThread {
    private final String TAG = "LongTaskUsingThread";

    void callMeForLongTask() {
        Log.d(TAG, "Start callMeForLongTask");
        doTaskUsingThread(new Listener() {
            @Override
            public void onComplete(String result) {
                Log.d(TAG, "onComplete callMeForLongTask");
            }
        });
        Log.d(TAG, "End callMeForLongTask");

    }

    void doTaskUsingThread(Listener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                listener.onComplete("Long task is done");
            }
        }).start();
    }

    public static void main(String[] args) {
        LongTaskUsingThread longTaskUsingThread = new LongTaskUsingThread();
        longTaskUsingThread.callMeForLongTask();
    }
}



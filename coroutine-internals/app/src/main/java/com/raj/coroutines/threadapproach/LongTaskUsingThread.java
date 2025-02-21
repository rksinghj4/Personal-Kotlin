package com.raj.coroutines.threadapproach;

interface Listener {
    void onComplete(String result);
}

class LongTaskUsingThread {
    private final String TAG = "LongTaskUsingThread";

    void callMeForLongTask() {
        System.out.println("Start callMeForLongTask");
        doTaskUsingThread(new Listener() {
            @Override
            public void onComplete(String result) {
                System.out.println("onComplete callMeForLongTask");
            }
        });
        System.out.println("End callMeForLongTask");

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

                listener.onComplete("Long task is result");
            }
        }).start();
    }

    public static void main(String[] args) {
        LongTaskUsingThread longTaskUsingThread = new LongTaskUsingThread();
        longTaskUsingThread.callMeForLongTask();
    }
}



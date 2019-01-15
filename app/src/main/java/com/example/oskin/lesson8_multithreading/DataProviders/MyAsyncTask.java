package com.example.oskin.lesson8_multithreading.DataProviders;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MyAsyncTask extends AsyncTask<Void, Void, Void> {

    public static final int RANDOM_NUMBER = 100;
    private Handler mHandler;

    public MyAsyncTask(Handler handler){
        mHandler = handler;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Random random = new Random();
        while (!isCancelled()){
            Message message = mHandler.obtainMessage(RANDOM_NUMBER, random.nextInt(100));
            message.sendToTarget();

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return null;
    }
}

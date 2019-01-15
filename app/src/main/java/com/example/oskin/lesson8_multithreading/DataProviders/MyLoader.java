package com.example.oskin.lesson8_multithreading.DataProviders;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MyLoader extends AsyncTaskLoader<Integer> {

    public MyLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public Integer loadInBackground() {
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        return color;
    }
}

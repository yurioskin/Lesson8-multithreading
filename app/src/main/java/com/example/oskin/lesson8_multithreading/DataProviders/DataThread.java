package com.example.oskin.lesson8_multithreading.DataProviders;

import java.sql.DataTruncation;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import android.os.Handler;
import android.os.Message;

public class DataThread extends Thread {
    Handler mHandler;

    public boolean stop = false;

    public static final int NEW_DATA = 200;

    public DataThread(Handler handler){
        mHandler = handler;
    }

    @Override
    public void run() {
        List<Integer> data = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < 10; i++) {
            int number = rnd.nextInt(100);
            data.add(number);
        }
        sendMsg(data);


        while (!stop){

            data.remove(rnd.nextInt(data.size()));
            data.add(rnd.nextInt(100));
            sendMsg(data);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }

    private void sendMsg(List<Integer> data){
        Message message = mHandler.obtainMessage(NEW_DATA,data);
        message.sendToTarget();
    }
}

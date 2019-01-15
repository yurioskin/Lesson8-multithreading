package com.example.oskin.lesson8_multithreading.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.oskin.lesson8_multithreading.DataProviders.MyAsyncTask;
import com.example.oskin.lesson8_multithreading.R;


public class SecondFragment extends Fragment {

    private TextView mNumber;
    private MyAsyncTask mMyAsyncTask;
    private Handler mHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MyAsyncTask.RANDOM_NUMBER:
                    mNumber.setText(msg.obj.toString());
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNumber = view.findViewById(R.id.random_number);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMyAsyncTask = new MyAsyncTask(mHandler);
        mMyAsyncTask.execute();

    }

    @Override
    public void onPause() {
        mMyAsyncTask.cancel(true);
        super.onPause();
    }

    public static SecondFragment newInstance() {
        return new SecondFragment();
    }
}

package com.example.oskin.lesson8_multithreading.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.oskin.lesson8_multithreading.DataProviders.DataThread;
import com.example.oskin.lesson8_multithreading.R;
import com.example.oskin.lesson8_multithreading.Recycler.CustomAdapter;

import java.util.ArrayList;
import java.util.List;

public class ThirdFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private CustomAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DataThread mDataThread;
    private List<Integer> mData;

    private Handler mHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case DataThread.NEW_DATA:
                    mData = (List<Integer>) msg.obj;
                    mAdapter.newData( mData);
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.third_fragment_recycler);

        mData = new ArrayList<>();
        mAdapter = new CustomAdapter();
        mLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        //mAdapter.newData(mData);
    }

    @Override
    public void onResume() {
        super.onResume();
        mDataThread = new DataThread(mHandler);
        mDataThread.start();
    }

    @Override
    public void onPause() {
        //Почему это не работает?
        //mDataThread.interrupt();

        mDataThread.stop = true;
        super.onPause();
    }

    public static ThirdFragment newInstance() {
        return new ThirdFragment();
    }
}

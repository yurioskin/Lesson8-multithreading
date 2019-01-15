package com.example.oskin.lesson8_multithreading;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.oskin.lesson8_multithreading.Fragments.FirstFragment;
import com.example.oskin.lesson8_multithreading.Fragments.SecondFragment;
import com.example.oskin.lesson8_multithreading.Fragments.ThirdFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragments();
    }

    private void initFragments() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.second_fragment,SecondFragment.newInstance())
                .add(R.id.first_fragment,FirstFragment.newInstance())
                .add(R.id.third_fragment,ThirdFragment.newInstance())
                .commitNow();
    }
}

package com.example.oskin.lesson8_multithreading.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.oskin.lesson8_multithreading.DataProviders.MyLoader;
import com.example.oskin.lesson8_multithreading.R;

import java.util.Objects;

public class FirstFragment extends Fragment implements LoaderManager.LoaderCallbacks<Integer> {

    public static final int LOADER_ID = 10000;
    private ConstraintLayout mConstraintLayout;
    private Loader<Integer> mLoader;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mConstraintLayout = view.findViewById(R.id.second_fragment_constraint);
        mLoader = Objects.requireNonNull(getActivity()).getSupportLoaderManager().initLoader(LOADER_ID,null,this);
    }


    @Override
    public void onResume() {
        super.onResume();
        mLoader.forceLoad();
    }

    @NonNull
    @Override
    public Loader<Integer> onCreateLoader(int id, @Nullable Bundle bundle) {
        if (id == LOADER_ID)
            return new MyLoader(Objects.requireNonNull(getContext()));
        return new Loader<>(Objects.requireNonNull(getContext()));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Integer> loader, Integer s) {
        mConstraintLayout.setBackgroundColor(s);
        mLoader.forceLoad();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Integer> loader) {

    }

    public static FirstFragment newInstance() {
        return new FirstFragment();
    }
}

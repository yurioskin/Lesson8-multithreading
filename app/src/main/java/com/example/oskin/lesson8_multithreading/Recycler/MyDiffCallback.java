package com.example.oskin.lesson8_multithreading.Recycler;

import android.support.v7.util.DiffUtil;

import java.util.List;

public class MyDiffCallback extends DiffUtil.Callback {

    private List<Integer> mOldList;
    private List<Integer> mNewList;

    public MyDiffCallback(List<Integer> oldList, List<Integer> newList) {
        mOldList = oldList;
        mNewList = newList;
    }

    @Override
    public int getOldListSize() {
        return mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldList.get(oldItemPosition).hashCode() == mNewList.get(newItemPosition);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldList.get(oldItemPosition).equals(mNewList.get(newItemPosition));
    }


}

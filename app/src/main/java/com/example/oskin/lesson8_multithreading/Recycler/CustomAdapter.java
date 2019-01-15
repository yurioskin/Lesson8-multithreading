package com.example.oskin.lesson8_multithreading.Recycler;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.oskin.lesson8_multithreading.R;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Integer> mData;

    public CustomAdapter() {
        mData = new ArrayList<>();
    }

    public void newData(List<Integer> data){
        //TODO DiffUtils
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffCallback(mData,data));
        mData.clear();
        mData.addAll(data);
        diffResult.dispatchUpdatesTo(this);
        //notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.recycler_item_layout,viewGroup,false);
        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        MyHolder myHolder = (MyHolder) viewHolder;
        myHolder.textView.setText("Number: " + mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder{

        public TextView textView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_number);
        }


    }
}

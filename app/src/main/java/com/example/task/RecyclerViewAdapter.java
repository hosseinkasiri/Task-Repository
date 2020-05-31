package com.example.task;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<TaskHolder> {
    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

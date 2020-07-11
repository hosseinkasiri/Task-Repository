package com.example.task.controller;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task.R;
import com.example.task.model.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {
    private List<Task> mTasks ;
    private Context mContext;
    private DialogInterface.OnDismissListener mListener;

    public TaskAdapter(Context context , List<Task> tasks, DialogInterface.OnDismissListener listener) {
        mTasks = tasks;
        mContext = context;
        mListener = listener;
    }
    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_recycler , parent , false);
        return new TaskHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
        Task task = mTasks.get(position);
        holder.bind(task);
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }
}


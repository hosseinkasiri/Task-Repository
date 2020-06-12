package com.example.task.controller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task.R;
import com.example.task.model.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {
    private List<Task> mTasks ;
    private Context mContext;

    public TaskAdapter(Context context , List<Task> tasks) {
        mTasks = tasks;
        mContext = context;
    }
    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_recycler , parent , false);
        TaskHolder taskHolder = new TaskHolder(view , mContext);
        return taskHolder;
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

class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView mFirstText , mTitleText , mDateText;
    private Context mContext;
    private Task mTask;

    public TaskHolder(@NonNull View itemView , Context context) {
        super(itemView);
        itemView.setOnClickListener(this);
        mFirstText = itemView.findViewById(R.id.first_text_show);
        mTitleText = itemView.findViewById(R.id.title_show_text);
        mDateText = itemView.findViewById(R.id.date_show_text);
        mContext = context;
    }

    public void bind(Task task){
        mTask = task;
        String firstCharacterOfTitle = String.valueOf(task.getTitle().charAt(0));
        mFirstText.setText(firstCharacterOfTitle);
        mTitleText.setText(task.getTitle());
        mDateText.setText(task.getDate().toString());
    }

    @Override
    public void onClick(View v) {
        Intent intent = DescriptionActivity.newIntent(v.getContext() , mTask);
        mContext.startActivity(intent);
    }
}
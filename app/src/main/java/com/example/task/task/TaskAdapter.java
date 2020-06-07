package com.example.task.task;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task.R;
import com.example.task.model.Task;

import java.lang.reflect.Array;
import java.util.Arrays;
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
        TaskHolder taskHolder = new TaskHolder(view);
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

class TaskHolder extends RecyclerView.ViewHolder{
    private TextView mFirstText;
    private TextView mTitleText;
    private TextView mDateText;

    public TaskHolder(@NonNull View itemView) {
        super(itemView);
        mFirstText = itemView.findViewById(R.id.first_text_show);
        mTitleText = itemView.findViewById(R.id.title_show_text);
        mDateText = itemView.findViewById(R.id.date_show_text);
    }

    public void bind(Task task){
        String firstCharacterOfTitle = String.valueOf(task.getTitle().charAt(0));
        mFirstText.setText(firstCharacterOfTitle);
        mTitleText.setText(task.getTitle());
        mDateText.setText(task.getDate().toString());
    }
}
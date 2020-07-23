package com.example.task.controller;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task.R;
import com.example.task.model.Task;
import com.example.task.model.TaskLab;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskHolder> implements Filterable {
    private List<Task> mTasks ;
    private List<String> mAllTasks;
    private Context mContext;
    private DialogInterface.OnDismissListener mListener;

    public TaskAdapter(Context context , List<Task> tasks, DialogInterface.OnDismissListener listener) {
        mTasks = tasks;
        mAllTasks = new ArrayList<>(stringTasks(tasks));
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
        holder.bind(task , mContext);
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<String> titleTasks = new ArrayList<>();
            if (constraint.toString().isEmpty())
                titleTasks.addAll(mAllTasks);
            else {
                for (String task : mAllTasks){
                    if (task.toLowerCase().contains(constraint.toString().toLowerCase()))
                        titleTasks.add(task);
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = titleTasks;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mTasks.clear();
            List<String> stringTasks = new ArrayList<>();
            stringTasks.addAll((Collection<? extends String>) results.values);
            mTasks.addAll(getTasks(stringTasks));
            notifyDataSetChanged();
        }
    };

    private List<String> stringTasks(List<Task> tasks){
        List<String> stringTasks = new ArrayList<>();
        for (Task task : tasks)
            stringTasks.add(task.getTitle());

        return stringTasks;
    }

    private List<Task> getTasks(List<String> titleTasks){
        List<Task> tasks = new ArrayList<>();
        for (String title : titleTasks)
            tasks.add(TaskLab.getInstance().getTaskWithTitle(title));

        return tasks;
    }
}


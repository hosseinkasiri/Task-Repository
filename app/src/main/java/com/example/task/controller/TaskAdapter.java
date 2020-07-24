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

import java.nio.file.ProviderNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskHolder> implements Filterable {
    private List<Task> mTasks ;
    private List<Task> mAllTask;
    private Context mContext;
    private DialogInterface.OnDismissListener mListener;

    public TaskAdapter(Context context , List<Task> tasks, DialogInterface.OnDismissListener listener) {
        mTasks = tasks;
        mAllTask = new ArrayList<>(tasks);
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

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
           List<Task> filterList = new ArrayList<>();

           if (constraint.toString().isEmpty())
               filterList.addAll(mAllTask);
           else {
              String filterPattern = constraint.toString().toLowerCase().trim();
               for (Task task : mAllTask){
                   if (task.getTitle().toLowerCase().contains(filterPattern) ||
                           task.getDescription().toLowerCase().contains(filterPattern))
                       filterList.add(task);
               }
           }
           FilterResults filterResults = new FilterResults();
           filterResults.values = filterList;
           return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
           mTasks.clear();
           mTasks.addAll((ArrayList) results.values);
           notifyDataSetChanged();
        }
    };
}


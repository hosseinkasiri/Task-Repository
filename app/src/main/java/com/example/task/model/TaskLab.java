package com.example.task.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class TaskLab {
    private static  TaskLab mInstance ;
    private List<Task> mTasks;
    private List<Task> mDoneTasks;
    private List<Task> mUnDoneTasks;

    private TaskLab() {
        mTasks = new ArrayList<>();
        mDoneTasks = new ArrayList<>();
        mUnDoneTasks  = new ArrayList<>();
    }

    public static TaskLab getInstance() {
        if (mInstance == null){
            mInstance = new TaskLab();
            return mInstance;
        }
        return mInstance;
    }

    public void addTask(Task task){
        mTasks.add(task);
        updateTaskLists();

    }

    public List<Task> getTasks(TaskListMode taskListMode) {
        switch (taskListMode){
            case all:
                return mTasks;
            case done:
               return  mDoneTasks;
            case unDone:
                return  mUnDoneTasks;

        }
    return mTasks;
    }

    private void updateTaskLists(){
        mUnDoneTasks.clear();
        mUnDoneTasks.addAll(mTasks);
        mUnDoneTasks.removeIf(T -> T.isDone());
        mDoneTasks.clear();
        mDoneTasks.addAll(mTasks);
        mDoneTasks.removeIf(T -> !T.isDone());


    }

}

package com.example.task.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class TaskLab {

    private List<Task> mTasks;
    private static  TaskLab mInstance ;

    private TaskLab() {
        mTasks = new ArrayList<>();
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
    }

    public List<Task> getTasks(TaskListMode taskListMode) {

        List<Task> tasks = new ArrayList<>(mTasks);
        switch (taskListMode){
            case all:
                break;

            case done:
                tasks.removeIf(task -> !task.isDone());
                break;

            case unDone:
                tasks.removeIf(Task::isDone);
                break;
        }
        return tasks;
    }
}

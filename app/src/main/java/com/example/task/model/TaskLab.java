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
        }


        return mInstance;
    }

    public List<Task> addTask(Task task){

        mTasks.add(task);

        return mTasks;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Task> getTasks(TaskListMode taskListMode) {

        List<Task> tasks = new ArrayList<>(mTasks);
        switch (taskListMode){
            case all:
                return tasks;

            case done:
                tasks.removeIf(task -> !task.isDone());
                return tasks;

            case unDone:
                tasks.removeIf(Task::isDone);
                return tasks;
        }
        return tasks;
    }
}

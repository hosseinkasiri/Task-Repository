package com.example.task.model;

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

    public List<Task> getTasks() {
        return mTasks;
    }
}

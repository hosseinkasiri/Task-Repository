package com.example.task.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

public class TaskLab {
    private static TaskLab mInstance;
    private LinkedHashMap<UUID , Task> mTasks;

    private TaskLab() {
        mTasks = new LinkedHashMap<>();
    }

    public static TaskLab getInstance() {
        if (mInstance == null) {
            mInstance = new TaskLab();
            return mInstance;
        }
        return mInstance;
    }

    public void addTask(Task task) {
        mTasks.put(task.getId(),task);
    }

    public List<Task> getTasks(TaskListMode taskListMode) {
        List<Task> list = new ArrayList<>(mTasks.values());
        switch (taskListMode) {
            case all:
                break;
            case done:
                list.removeIf(T -> !T.isDone());
                break;
            case unDone:
                list.removeIf(Task::isDone);
                break;
        }
        return list;
    }

    public Task getTask(UUID id){
        return mTasks.get(id);
    }
}

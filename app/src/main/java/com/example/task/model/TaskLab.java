package com.example.task.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.task.controller.TaskListFragment;
import com.example.task.database.TaskBaseHelper;
import com.example.task.database.TaskCursorWrapper;
import com.example.task.database.TaskDbSchema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

public class TaskLab {
    private static TaskLab mInstance;
    private SQLiteDatabase mDatabase;
    private Context mContext;

    private TaskLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new TaskBaseHelper(mContext).getWritableDatabase();
    }

    public static TaskLab getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new TaskLab(context);
            return mInstance;
        }
        return mInstance;
    }

    public void addTask(Task task) {
        ContentValues values = getContentValues(task);
        mDatabase.insert(TaskDbSchema.TaskTable.NAME,null,values);
    }

    public List<Task> getTasks(TaskListMode taskListMode,UUID userId) {
        List<Task> tasks = new ArrayList<>();
        String whereClause = TaskDbSchema.TaskTable.TaskCols.USER_ID + " = ?";
        String[] whereArgs = new String[]{userId.toString()};
        TaskCursorWrapper cursor = queryTask(whereClause, whereArgs);
        if (cursor.getCount() == 0)
            return tasks;
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                tasks.add(cursor.getTask());
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        switch (taskListMode){
            case all:
                return tasks;
            case done:
                tasks.removeIf(T -> !T.isDone());
                return tasks;
            case unDone:
                tasks.removeIf(Task::isDone);
                return tasks;
        }
        return tasks;
    }

    public Task getTask(UUID id){
        String whereClause = TaskDbSchema.TaskTable.TaskCols.UUID + " = ? ";
        String[] whereArgs = new String[]{id.toString()};
        TaskCursorWrapper cursor = queryTask(whereClause, whereArgs);
        if (cursor.getCount() == 0)
            return null;

        try {
            cursor.moveToFirst();
                return cursor.getTask();
        }
        finally {
            cursor.close();
        }
    }
    private TaskCursorWrapper queryTask(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                TaskDbSchema.TaskTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new TaskCursorWrapper(cursor);
    }

    public void removeTask(UUID id){
        String whereClause = TaskDbSchema.TaskTable.TaskCols.UUID + " = ?" ;
        mDatabase.delete(TaskDbSchema.TaskTable.NAME,whereClause,new String[]{id.toString()});
    }

    public void clearTasks(UUID userId){
        String whereClause = TaskDbSchema.TaskTable.TaskCols.USER_ID + " = ?";
        String[] whereArgs = new String[]{userId.toString()};
        mDatabase.delete(TaskDbSchema.TaskTable.NAME,whereClause,whereArgs);
    }

    public void updateTask(Task task){
        ContentValues values = getContentValues(task);
        String whereClause = TaskDbSchema.TaskTable.TaskCols.UUID + " = ?";
        String[] whereArgs = new String[]{task.getId().toString()};
        mDatabase.update(TaskDbSchema.TaskTable.NAME,values,whereClause,whereArgs);
}

    public ContentValues getContentValues(Task task){
        ContentValues values = new ContentValues();
        values.put(TaskDbSchema.TaskTable.TaskCols.UUID,task.getId().toString());
        values.put(TaskDbSchema.TaskTable.TaskCols.TITLE,task.getTitle());
        values.put(TaskDbSchema.TaskTable.TaskCols.DESCRIPTION,task.getDescription());
        values.put(TaskDbSchema.TaskTable.TaskCols.DATE,task.getDate().getTime());
        values.put(TaskDbSchema.TaskTable.TaskCols.DONE,task.isDone());
        values.put(TaskDbSchema.TaskTable.TaskCols.USER_ID,task.getUserId().toString());
        return values;
    }
}

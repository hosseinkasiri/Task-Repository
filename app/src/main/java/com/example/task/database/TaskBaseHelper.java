package com.example.task.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class TaskBaseHelper extends SQLiteOpenHelper {
    public TaskBaseHelper(Context context) {
        super(context, TaskDbSchema.NAME, null, TaskDbSchema.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TaskDbSchema.UserTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                TaskDbSchema.UserTable.UserCols.UUID + ", " +
                TaskDbSchema.UserTable.UserCols.FIRST_NAME + ", " +
                TaskDbSchema.UserTable.UserCols.LAST_NAME + ", " +
                TaskDbSchema.UserTable.UserCols.USERNAME + ", " +
                TaskDbSchema.UserTable.UserCols.PASSWORD +
                ")"
        );

        db.execSQL("create table " + TaskDbSchema.TaskTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                TaskDbSchema.TaskTable.TaskCols.UUID + ", " +
                TaskDbSchema.TaskTable.TaskCols.TITLE + ", " +
                TaskDbSchema.TaskTable.TaskCols.DESCRIPTION + ", " +
                TaskDbSchema.TaskTable.TaskCols.DATE + ", " +
                TaskDbSchema.TaskTable.TaskCols.DONE + ", " +
                TaskDbSchema.TaskTable.TaskCols.USER_ID +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

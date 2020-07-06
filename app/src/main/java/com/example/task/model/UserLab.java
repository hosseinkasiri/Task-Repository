package com.example.task.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.task.database.TaskBaseHelper;

public class UserLab {
    private static UserLab mInstance;
    private SQLiteDatabase mDatabase;
    private Context mContext;

    private UserLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new TaskBaseHelper(mContext).getWritableDatabase();
    }

    public static UserLab getInstance(Context context) {
        if (mInstance == null)
            mInstance = new UserLab(context);

        return mInstance;
    }
}

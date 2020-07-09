package com.example.task.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.task.database.TaskBaseHelper;
import com.example.task.database.TaskDbSchema;
import com.example.task.database.UserCursorWrapper;

import java.util.UUID;

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

    public void addUser(User user){
        ContentValues values = getContentValues(user);
        mDatabase.insert(TaskDbSchema.UserTable.NAME,null,values);
    }

    public User getUser(String username , String password){
        String whereClause = TaskDbSchema.UserTable.UserCols.USERNAME + " = ? AND password = ? ";
        String [] whereArgs = new String[]{username,password};
        UserCursorWrapper cursor = queryUser(whereClause, whereArgs);
        if (cursor.getCount() == 0)
            return null;

        try {
            cursor.moveToFirst();
           return cursor.getUser();

        } finally {
            cursor.close();
        }
    }

    private UserCursorWrapper queryUser(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                    TaskDbSchema.UserTable.NAME,
                    null,
                    whereClause,
                    whereArgs,
                    null,null,
                    null
            );
        return new UserCursorWrapper(cursor);
    }

    public ContentValues getContentValues(User user){
        ContentValues values = new ContentValues();
        values.put(TaskDbSchema.UserTable.UserCols.FIRST_NAME,user.getFirstName());
        values.put(TaskDbSchema.UserTable.UserCols.LAST_NAME,user.getLastName());
        values.put(TaskDbSchema.UserTable.UserCols.USERNAME,user.getUsername());
        values.put(TaskDbSchema.UserTable.UserCols.PASSWORD,user.getPassword());
        values.put(TaskDbSchema.UserTable.UserCols.UUID,user.getUuid().toString());
        return values;
    }
}

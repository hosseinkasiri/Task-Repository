package com.example.task.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.task.model.User;

import java.util.UUID;

public class UserCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public UserCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public User getUser(){
        String firstName = getString(getColumnIndex(TaskDbSchema.UserTable.UserCols.FIRST_NAME));
        String lastName = getString(getColumnIndex(TaskDbSchema.UserTable.UserCols.LAST_NAME));
        String userName = getString(getColumnIndex(TaskDbSchema.UserTable.UserCols.USERNAME));
        String passWord = getString(getColumnIndex(TaskDbSchema.UserTable.UserCols.PASSWORD));
        UUID uuid = UUID.fromString(getString(getColumnIndex(TaskDbSchema.UserTable.UserCols.UUID)));

        User user = new User(uuid);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(userName);
        user.setPassword(passWord);
        return user;
    }
}

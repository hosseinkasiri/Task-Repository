package com.example.task.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.task.model.Task;

import java.util.Date;
import java.util.UUID;

public class TaskCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public TaskCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public Task getTask(){
        UUID uuid = UUID.fromString(getString(getColumnIndex(TaskDbSchema.TaskTable.TaskCols.UUID)));
        UUID userId = UUID.fromString(getString(getColumnIndex(TaskDbSchema.TaskTable.TaskCols.USER_ID)));
        String title = getString(getColumnIndex(TaskDbSchema.TaskTable.TaskCols.TITLE));
        String description = getString(getColumnIndex(TaskDbSchema.TaskTable.TaskCols.DESCRIPTION));
        Date date = new Date(getLong(getColumnIndex(TaskDbSchema.TaskTable.TaskCols.DATE)));
        boolean isDone = getInt(getColumnIndex(TaskDbSchema.TaskTable.TaskCols.DONE)) != 0;

        Task task = new Task(uuid);
        task.setUserId(userId);
        task.setTitle(title);
        task.setDescription(description);
        task.setDate(date);
        task.setDone(isDone);

        return task;
    }
}

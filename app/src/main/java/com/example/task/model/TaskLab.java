package com.example.task.model;
import android.content.Context;

import com.example.task.database.App;

import java.io.File;
import java.util.List;
import java.util.UUID;

public class TaskLab {
    private static TaskLab mInstance;
    private DaoSession mTaskSession;
    private TaskDao mTaskDao;

    private TaskLab() {
        mTaskSession = (App.getApp()).getSession();
        mTaskDao = mTaskSession.getTaskDao();
    }

    public static TaskLab getInstance() {
        if (mInstance == null) {
            mInstance = new TaskLab();
            return mInstance;
        }
        return mInstance;
    }

    public void addTask(Task task) {
        mTaskDao.insert(task);
    }

    public List<Task> getTasks(TaskListMode taskListMode,Long userId) {
        List<Task> tasks = mTaskDao.queryBuilder()
                .where(TaskDao.Properties.UserId.eq(userId))
                .list();
        switch (taskListMode){
            case all:
                return tasks;
            case done:
                tasks.removeIf(T -> !T.getDone());
                return tasks;
            case unDone:
                tasks.removeIf(Task::getDone);
                return tasks;
        }
        return tasks;
    }

    public Task getTask(Long id){
        return mTaskDao.queryBuilder()
                .where(TaskDao.Properties.Id.eq(id))
                .unique();
    }

    public Task getTaskWithText(String title){
        return mTaskDao.queryBuilder()
                .whereOr(TaskDao.Properties.Title.eq(title) , TaskDao.Properties.Description.eq(title))
                .unique();
    }

    public void removeTask(Long id){
        mTaskDao.delete(getTask(id));
    }

    public void clearTasks(Long userId){
        List<Task> tasks = mTaskDao.queryBuilder()
                .where(TaskDao.Properties.UserId.eq(userId))
                .list();
        mTaskDao.deleteInTx(tasks);
    }

    public void updateTask(Task task){
        mTaskDao.update(task);
    }

    public File getPhotoFile(Context context , Task task){
        File fileDir = context.getFilesDir();
        File photoFile = new File(fileDir,task.getPhotoName());
        return photoFile;
    }
}

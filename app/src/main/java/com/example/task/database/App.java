package com.example.task.database;

import android.app.Application;
import android.content.SharedPreferences;
import android.se.omapi.Session;

import com.example.task.model.DaoMaster;
import com.example.task.model.DaoSession;

import org.greenrobot.greendao.database.Database;

public class App extends Application {

    public static App sApp;
    private DaoSession mSession;

    @Override
    public void onCreate() {
        super.onCreate();
        MyDevOpenHelper myDevOpenHelper = new MyDevOpenHelper(this,"task-db");
        Database db = myDevOpenHelper.getWritableDb();
        sApp = this;
        mSession = new DaoMaster(db).newSession();
    }

    public static App getApp() {
        return sApp;
    }

    public DaoSession getSession() {
        return mSession;
    }
}

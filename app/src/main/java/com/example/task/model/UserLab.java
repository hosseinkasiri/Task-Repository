package com.example.task.model;

import android.content.Context;

import com.example.task.database.App;

import org.greenrobot.greendao.query.Query;

import java.util.List;

public class UserLab {
    private static UserLab mInstance;
    private DaoSession mSession = (App.getApp()).getSession();
    private UserDao mUserDao = mSession.getUserDao();

    private UserLab(){
    }

    public static UserLab getInstance() {
        if (mInstance == null)
            mInstance = new UserLab();

        return mInstance;
    }

    public void addUser(User user){
        mUserDao.insert(user);
    }

    public User getUser(String username , String password){
      User user = mUserDao.queryBuilder()
              .where(UserDao.Properties.Username.eq(username))
              .where(UserDao.Properties.Password.eq(password))
              .unique();
      return user;
    }

    public User getUserById(Long userId){
       User user = mUserDao.queryBuilder()
               .where(UserDao.Properties.Uuid.eq(userId))
               .unique();
      return user;
    }

    public void deleteUser(Long userId){
        mUserDao.delete(getUserById(userId));
    }

    public void updateUser(User user){
        mUserDao.update(user);
    }
}

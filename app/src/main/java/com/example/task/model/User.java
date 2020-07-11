package com.example.task.model;

import java.util.Random;
import java.util.UUID;

public class User {
    private String mFirstName;
    private String mLastName;
    private UUID mUuid;
    private String mUsername;
    private String mPassword;
    private boolean mGuest;
    private boolean mFirstActivity;

    public User() {
        this(UUID.randomUUID());
    }

    public User(UUID uuid){
        mUuid = uuid;
        mGuest = false;
        mFirstActivity = false;
    }
    
    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public UUID getUuid() {
        return mUuid;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public boolean isGuest() {
        return mGuest;
    }

    public void setGuest(boolean guest) {
        mGuest = guest;
    }

    public boolean isFirstActivity() {
        return mFirstActivity;
    }

    public void setFirstActivity(boolean firstActivity) {
        mFirstActivity = firstActivity;
    }
}

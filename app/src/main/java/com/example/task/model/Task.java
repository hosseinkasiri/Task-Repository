package com.example.task.model;

import java.util.Date;
import java.util.Timer;

public class Task {

    private String mDescription;

    private Date mDate;

    private boolean mDone;

    public Task() {

        mDate = new Date();

    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isDone() {
        return mDone;
    }

    public void setDone(boolean done) {
        mDone = done;
    }
}

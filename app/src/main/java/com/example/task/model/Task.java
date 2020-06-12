package com.example.task.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Timer;
import java.util.UUID;

public class Task implements Serializable {

    private String mTitle;
    private String mDescription;
    private Date mDate;
    private boolean mDone;
    private UUID mId;

    public Task() {
        mDate = new Date();
        mId = UUID.randomUUID();
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

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public UUID getId() {
        return mId;
    }
}

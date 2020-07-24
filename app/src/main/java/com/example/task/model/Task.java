package com.example.task.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.task.helper.ConverterId;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.converter.PropertyConverter;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class Task implements Serializable {
    private static final long serialVersionUID = -2338626292552177485L;
    @Id(autoincrement = true)
    private Long id;
    private String title;
    private String description;
    private Date date;
    private boolean done;
    private Long userId;
    private String photoName;
    @Generated(hash = 1573387333)
    public Task(Long id, String title, String description, Date date, boolean done,
            Long userId, String photoName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.done = done;
        this.userId = userId;
        this.photoName = photoName;
    }
    @Keep
    public Task() {
        date = new Date();
    }

    protected Task(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        title = in.readString();
        description = in.readString();
        done = in.readByte() != 0;
        if (in.readByte() == 0) {
            userId = null;
        } else {
            userId = in.readLong();
        }
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getDate() {
        return this.date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public boolean getDone() {
        return this.done;
    }
    public void setDone(boolean done) {
        this.done = done;
    }
    public Long getUserId() {
        return this.userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getPhotoName() {
        return "IMG_" + id + ".jpg";
    }
    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }
}

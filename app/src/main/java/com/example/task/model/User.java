package com.example.task.model;

import com.example.task.helper.ConverterId;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Unique;

import java.util.Random;
import java.util.UUID;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class User {
    @Id(autoincrement = true)
    private Long uuid;
    private String firstName;
    private String lastName;
    @Unique
    private String username;
    private String password;
    private boolean guest;
    @Generated(hash = 547969295)
    public User(Long uuid, String firstName, String lastName, String username,
            String password, boolean guest) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.guest = guest;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public Long getUuid() {
        return this.uuid;
    }
    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean getGuest() {
        return this.guest;
    }
    public void setGuest(boolean guest) {
        this.guest = guest;
    }
}
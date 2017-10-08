package com.example.kiran.in_class_03;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by kiran on 9/18/17.
 */

public class Profile implements Serializable{

    String name;
    String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    String department;
    String avatar;
    String mood;

    public Profile(String name, String email, String department, String avatar, String mood) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.avatar = avatar;
        this.mood = mood;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                ", avatar='" + avatar + '\'' +
                ", mood='" + mood + '\'' +
                '}';
    }
}

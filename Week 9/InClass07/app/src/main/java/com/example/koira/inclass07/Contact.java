package com.example.koira.inclass07;

/*
Assignment # : In Class 7
File Name: Contact.java
Full Names: Kiran Koirala
 */
import java.io.Serializable;

/**
 * Created by koira on 10/30/2017.
 */

public class Contact implements Serializable{
    String name, email, phone, department, pictureId;

    public Contact(String name, String email, String phone, String department, String pictureId) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.department = department;
        this.pictureId = pictureId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getDepartment() {
        return department;
    }

    public String getPictureId() {
        return pictureId;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", department='" + department + '\'' +
                ", pictureId='" + pictureId + '\'' +
                '}';
    }
}

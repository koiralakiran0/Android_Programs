package com.example.kiran.inclass09;

import java.io.Serializable;

/**
 * Created by kiran on 11/16/17.
 */

public class Contact implements Serializable{
    String name, email, phone, department, image;

    public Contact(String name, String email, String phone, String department, String image){
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.department = department;
        this.image = image;
    }


    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", department='" + department + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}

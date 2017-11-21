package com.example.kiran.inclass09;

import java.io.Serializable;

/**
 * Created by kiran on 11/16/17.
 */

public class Contact implements Serializable{
<<<<<<< HEAD
    String name, email, phone, department, image, uid;
=======
    String name, email, phone, department, image;
>>>>>>> a7fea9a5951b59e1fd340e8d5c70db62a170921c

    public Contact(String name, String email, String phone, String department, String image){
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.department = department;
        this.image = image;
    }


<<<<<<< HEAD
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

    public String getImage() {
        return image;
    }

=======
>>>>>>> a7fea9a5951b59e1fd340e8d5c70db62a170921c
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
<<<<<<< HEAD
}
=======
}
>>>>>>> a7fea9a5951b59e1fd340e8d5c70db62a170921c

package com.example.kiran.inclass08;

import java.io.Serializable;

/**
 * Created by kiran on 11/6/17.
 */

public class ThreadMessage implements Serializable{

    String user_fname;
    String user_lname;
    String user_id;
    String title;

    @Override
    public String toString() {
        return "ThreadMessage{" +
                "user_first_name='" + user_fname + '\'' +
                ", user_lname='" + user_lname + '\'' +
                ", user_id='" + user_id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}

package com.example.kiran.inclass08;

import java.io.Serializable;

/**
 * Created by kiran on 11/6/17.
 */

public class ThreadMessage implements Serializable{

    String token;
    String user_fname;
    String user_lname;
    String user_id;
    String user_email;
    String user_role;

    public ThreadMessage(String token,String user_fname, String user_lname, String user_id, String user_email, String user_role) {
        this.token = token;
        this.user_fname = user_fname;
        this.user_lname = user_lname;
        this.user_id = user_id;
        this.user_email = user_email;
        this.user_role = user_role;
    }

    @Override
    public String toString() {
        return user_fname + " " + user_lname;
    }
}

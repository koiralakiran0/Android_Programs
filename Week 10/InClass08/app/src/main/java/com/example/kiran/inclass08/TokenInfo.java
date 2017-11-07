package com.example.kiran.inclass08;

import java.io.Serializable;

/**
 * Created by kiran on 11/6/17.
 */

class TokenInfo implements Serializable {
    /*

     */

    private String token;
    private String user_email;
    private String user_fname;
    private String user_lname;
    private String user_role;
    private int user_id;


    public String getToken() {
        return token;
    }

    public String getUser_email() {
        return user_email;
    }

    public String getUser_fname() {
        return user_fname;
    }

    public String getUser_lname() {
        return user_lname;
    }

    public String getUser_role() {
        return user_role;
    }

    public int getUser_id() {
        return user_id;
    }

    @Override
    public String toString() {
        return "TokenInfo{" +
                "token='" + token + '\'' +
                ", user_email='" + user_email + '\'' +
                ", user_fname='" + user_fname + '\'' +
                ", user_lname='" + user_lname + '\'' +
                ", user_role='" + user_role + '\'' +
                ", user_id=" + user_id +
                '}';
    }

}

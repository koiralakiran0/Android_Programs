package com.example.kiran.inclass08;

import java.io.Serializable;

/**
 * Created by koira on 11/10/2017.
 */

class MessageThread implements Serializable{
    String user_fname;
    String user_lname;
    String user_id;
    String id;
    String title;
    String created_at;

    public MessageThread(String user_fname, String user_lname, String user_id, String id, String title, String created_at) {
        this.user_fname = user_fname;
        this.user_lname = user_lname;
        this.user_id = user_id;
        this.id = id;
        this.title = title;
        this.created_at = created_at;
    }

    public String toString(){
        return title;
    }
}

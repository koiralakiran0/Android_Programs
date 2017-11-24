package com.example.koira.inclass06;

import java.io.Serializable;

/**
 * Created by koira on 11/22/2017.
 */

public class Source implements Serializable{
    String id, name;

    @Override
    public String toString() {
        return "Source{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

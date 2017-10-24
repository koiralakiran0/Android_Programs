package com.example.koira.inclass06;

import java.io.Serializable;

/**
 * Created by koira on 10/23/2017.
 */

public class Source implements Serializable {
    String id, name;

    public Source(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId(){
        return id;
    }
    @Override
    public String toString() {
        return name;
    }
}

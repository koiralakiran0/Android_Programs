package com.example.kiran.inclass011;

import java.util.ArrayList;

/**
 * Created by kiran on 11/27/17.
 */

public class Places {
    private ArrayList<Place> points = new ArrayList<>();
    private String title;

    public Places(ArrayList<Place> points, String title) {
        this.points = points;
        this.title = title;
    }

    public ArrayList<Place> getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "Places{" +
                "places=" + points +
                ", title='" + title + '\'' +
                '}';
    }
}

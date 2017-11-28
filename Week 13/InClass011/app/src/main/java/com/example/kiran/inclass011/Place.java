package com.example.kiran.inclass011;

/**
 * Created by kiran on 11/27/17.
 */

public class Place {
    double latitude, longitude;

    @Override
    public String toString() {
        return "Place{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    public Place(int latitude, int longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

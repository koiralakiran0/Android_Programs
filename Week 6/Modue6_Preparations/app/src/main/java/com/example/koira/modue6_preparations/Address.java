package com.example.koira.modue6_preparations;

/**
 * Created by koira on 10/8/2017.
 */

public class Address {
    String line1, city, state, zip;

    public Address() {
    }

    @Override
    public String toString() {
        return "Address{" +
                "line1='" + line1 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }
}


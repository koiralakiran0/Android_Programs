package com.example.koira.modue6_preparations;

/**
 * Created by koira on 10/8/2017.
 */

public class Person {
    String name;
    long id;
    int age;
    Address address;

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", age=" + age +
                ", address=" + address +
                '}';
    }
}

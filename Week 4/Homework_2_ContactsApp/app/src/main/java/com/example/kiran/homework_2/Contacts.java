package com.example.kiran.homework_2;

/**
 * Created by kiran on 10/1/17.
 */

public class Contacts {
    String firstName;
    String lastName;
    String company;
    String phone;
    String email;
    String URL;
    String address;
    String birthday;
    String nickName;
    String facebook;
    String twitter;
    String youtube;
    String skype;

    public Contacts(String firstName,String lastName,String phone){
        this.firstName=firstName;
        this.lastName=lastName;
        this.phone=phone;
    }

    public Contacts(String firstName, String lastName, String company, String phone, String email, String URL, String address, String birthday, String nickName, String facebook, String twitter, String youtube, String skype) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.phone = phone;
        this.email = email;
        this.URL = URL;
        this.address = address;
        this.birthday = birthday;
        this.nickName = nickName;
        this.facebook = facebook;
        this.twitter = twitter;
        this.youtube = youtube;
        this.skype = skype;
    }


}

package edu.uncc.cci.mobileapps;

public class User implements Comparable<User>{
    String firstname,lastname,age,email,gender,city,state;

    public User(String firstname, String lastname, String age, String email, String gender, String city, String state) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.gender = gender;
        this.city = city;
        this.state = state;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age='" + age + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    @Override
    public int compareTo(User o) {
        return Integer.valueOf(this.age) - Integer.valueOf(o.age);
    }
}

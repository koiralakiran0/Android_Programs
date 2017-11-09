package edu.uncc.cci.mobileapps;

import java.util.ArrayList;
import java.util.Collections;

public class MainPart1 {
    /*
    * Question 1:
    * - In this question you will use the Data.users array that includes
    * a list of users. Formatted as : firstname,lastname,age,email,gender,city,state
    * - Create a User class that should parse all the parameters for each user.
    * - Insert each of the users in a list.
    * - Print out the TOP 10 oldest users.
    * */

    public static void main(String[] args) {
        ArrayList<User> arrayList = new ArrayList<>();
        String[] arguments = new String[7];
        //example on how to access the Data.users array.
        for (String str : Data.users) {
            //System.out.println(str);
            arguments = str.split(",");
            arrayList.add(new User(arguments[0], arguments[1], arguments[2],
                    arguments[3], arguments[4], arguments[5], arguments[6]));
        }

        Collections.sort(arrayList, Collections.reverseOrder());
        for (int i = 0; i < 10; i++){
            System.out.println(arrayList.get(i));
        }
    }
}
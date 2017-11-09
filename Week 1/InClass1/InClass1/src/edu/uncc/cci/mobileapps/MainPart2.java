package edu.uncc.cci.mobileapps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MainPart2 {
    /*
    * Question 2:
    * - In this question you will use the Data.users array that includes
    * a list of users. Formatted as : firstname,lastname,age,email,gender,city,state
    * - Create a User class that should parse all the parameters for each user.
    * - The goal is to count the number of users living each state.
    * - Print out the list of State, Count order in ascending order by count.
    * */

    public static void main(String[] args) {

        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();

        String[] arguments = new String[7];

        //example on how to access the Data.users array.
        for (String str : Data.users) {
           // System.out.println(str);
            arguments = str.split(",");
            if (hashMap.containsKey(arguments[6])){
                hashMap.put(arguments[6], hashMap.get(arguments[6]) + 1);
            } else {
                hashMap.put(arguments[6], 1);
            }
        }

        //System.out.println(hashMap);
        Map map = MainPart2.sortByValue(hashMap);
        System.out.println(map);
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}

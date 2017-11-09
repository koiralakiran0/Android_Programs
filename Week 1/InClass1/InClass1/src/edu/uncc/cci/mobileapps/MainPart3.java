package edu.uncc.cci.mobileapps;

import java.util.*;

public class MainPart3 {
    /*
    * Question 3:
    * - This is a simple programming question that focuses on finding the
    * longest increasing subarray. Given the array A = {1,2,3,2,5,2,4,6,7} the
    * longest increasing subarray is {2,4,6,7}, note that the values have to be
    * contiguous.
    * */

    public static void main(String[] args) {
        //example call
        //int[] input = {}; // output {}
        //int[] input = {1}; // output {1}
        //int[] input = {1,2,3,4}; // output {1,2,3,4}
        //int[] input = {1,2,3,4,4,4,4,4,5,6}; // output {1,2,3,4}
        //int[] input = {1,2,3,-1,4,5,8,20,25,1,1,4,6}; // output {-1,4,5,8,20,25}
        //int[] input = {1,2,3,1,1,1,2,3,4,1,1,2,4,6,7}; // output{1,2,4,6,7}
        int[] input = {1,2,3,2,5,2,4,6,7}; // output {2,4,6,7}
        int[] result = printLongestSequence(input);
        for (int i: result) {
            System.out.print(i + ",");
        }
    }

    public static int[] printLongestSequence(int[] input){
        int[] result = {};
        if (input.length == 0 || input.length == 1){
            return input;
        }
        ArrayList<Integer> cList = new ArrayList<>();
        ArrayList<Integer> longest_alist = new ArrayList<>();

        int longest = 1;
        int previous_num;
        int current_num;

        for (int i = 1; i < input.length; i++){
            previous_num = input[i-1];
            current_num = input[i];

            if (current_num >= previous_num){
                cList.add(input[i]);
                longest++;
            } else {
                if (cList.size() >  longest){
                    longest_alist = cList;
                }
                longest = 1;
                cList.clear();
                cList.add(current_num);
            }
        }

        result = convertIntegers(longest_alist);

        return result;
    }

    public static int[] convertIntegers(List<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        Iterator<Integer> iterator = integers.iterator();
        for (int i = 0; i < ret.length; i++)
        {
            ret[i] = iterator.next().intValue();
        }
        return ret;
    }
}
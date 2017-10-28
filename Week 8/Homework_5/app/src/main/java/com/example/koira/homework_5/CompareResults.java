package com.example.koira.homework_5;

import java.util.Comparator;

/**
 * Created by koira on 10/28/2017.
 */


//This is to load which items first
public class CompareResults implements Comparator<Result>{

    String searchItem;
    public CompareResults(String searchItem){
        this.searchItem = searchItem;
    }

    @Override
    public int compare(Result result1, Result result2) {
        if(result1.getTitle().contains(searchItem) && !result2.getTitle().contains(searchItem)){
            return -1;
        }
        else if(!result1.getSummary().contains(searchItem) && result2.getSummary().contains(searchItem)){
            return 1;
        }
        return 0;
    }
}

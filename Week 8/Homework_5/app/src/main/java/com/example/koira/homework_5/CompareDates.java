package com.example.koira.homework_5;

import java.text.ParseException;
import java.util.Comparator;

/**
 * Created by koira on 10/28/2017.
 */

//this lets all the ones that has the keywords to come in front
public class CompareDates implements Comparator<Result> {

    @Override
    public int compare(Result result1, Result result2) {
        try {
            return result1.getReleaseDate().compareTo(result2.getReleaseDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

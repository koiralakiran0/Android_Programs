package com.example.koira.homework_3;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by koira on 10/5/2017.
 */

public class Question implements Serializable {
    private String question_number;
    private String question_content;
    private String image_URL;
    private ArrayList<String> answers = new ArrayList<>();
    private String answer;

    public Question(String content){
        String[] all_contents = content.split(";");
        question_number = all_contents[0];
        question_content = all_contents[1];
        image_URL = all_contents[2];
        for (int i = 3; i < all_contents.length-2; i++){
            answers.add(all_contents[i]);
        }
        answer = all_contents[all_contents.length-1];
    }
}

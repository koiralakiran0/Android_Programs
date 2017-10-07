package com.example.koira.homework_3;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by koira on 10/5/2017.
 * Homework 3
 * Kiran Koirala
 * Question.java
 */

public class Question implements Serializable {

    private String question_number;
    private String question_content;
    private String image_URL;
    private ArrayList<String> answers = new ArrayList<>();
    private String answer;

    public Question(String content){
        //Log.d("demo", content);
        String[] all_contents = content.split(";");
        question_number = all_contents[0];
        question_content = all_contents[1];
        image_URL = all_contents[2];
        for (int i = 3; i < all_contents.length-1; i++){
            answers.add(all_contents[i]);
        }
        answer = all_contents[all_contents.length-1];
    }

    @Override
    public String toString() {
        return "Question{" +
                "question_number='" + question_number + '\'' +
                ", question_content='" + question_content + '\'' +
                ", image_URL='" + image_URL + '\'' +
                ", answers=" + answers +
                ", answer='" + answer + '\'' +
                '}';
    }

    public String getQuestion_number() {
        return question_number;
    }

    public String getQuestion_content() {
        return question_content;
    }

    public String getImage_URL() {
        return image_URL;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public String getAnswer() {
        return answer;
    }
}

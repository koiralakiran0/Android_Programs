package com.example.koira.homework_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Trivia extends AppCompatActivity {

    TextView textView_questionNum;
    TextView textView_question;
    RadioGroup radioGroup_answers;
    ImageView imageView_questionImage;
    int index;
    ArrayList<Question> questions = null;
    int numCorrectAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        textView_questionNum = (TextView) findViewById(R.id.textView_questionNum);
        TextView textView_timeCounter = (TextView) findViewById(R.id.textView_timeCounter);
        textView_question = (TextView) findViewById(R.id.textView_question);
        imageView_questionImage = (ImageView) findViewById(R.id.imageView_fileImage);
        Button button_quit = (Button) findViewById(R.id.button_quit);
        Button button_next = (Button) findViewById(R.id.button_next);
        radioGroup_answers = (RadioGroup) findViewById(R.id.radioGroup_answers);

        index = 0;

        if (getIntent() != null){
            questions = (ArrayList<Question>) getIntent().getExtras().getSerializable(MainActivity.arrray_Key);
        }

        setQuestionView(questions, index);
        new GetImage(imageView_questionImage, Trivia.this).execute(questions.get(index).getImage_URL());

        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //CHECK IF ANSWER IS CORRECT

                index +=1;
                //IF QUESTION MAX, CHANGE VIEW
                if (index == questions.size()){
                    Intent intent = new Intent(Trivia.this, Stats.class);
                    startActivity(intent);
                }else {
                    setQuestionView(questions, index);
                    new GetImage(imageView_questionImage, Trivia.this).execute(questions.get(index).getImage_URL());
                }
            }
        });

        button_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        /*
        for (int i = 0; i < questions.size(); i++){
            Log.d("demo", questions.get(i).toString());
        }
        */
    }

    private void setQuestionView(ArrayList<Question> questions, int index) {
        radioGroup_answers.removeAllViews();
        Question question = questions.get(index);
        textView_questionNum.setText(question.getQuestion_number());
        textView_question.setText(question.getQuestion_content());
        ArrayList<String> answers = question.getAnswers();
        for (int i = 0; i < answers.size(); i++){
            RadioButton radioButton = new RadioButton(Trivia.this);
            radioButton.setText(answers.get(i));
            radioGroup_answers.addView(radioButton);
        }
    }
}

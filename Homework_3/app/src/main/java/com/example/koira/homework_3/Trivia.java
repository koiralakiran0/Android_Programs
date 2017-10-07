package com.example.koira.homework_3;

import android.content.Intent;
import android.os.CountDownTimer;
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

    static String NUM_CORRECT_KEY = "CORRECT_KEY";
    static String TOTAL_KEY = "TOTAL_KEY";
    TextView textView_questionNum;
    TextView textView_question;
    RadioGroup radioGroup_answers;
    ImageView imageView_questionImage;
    int index;
    ArrayList<Question> questions = null;
    int numCorrectAns;
    int totalQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        textView_questionNum = (TextView) findViewById(R.id.textView_questionNum);
        final TextView textView_timeCounter = (TextView) findViewById(R.id.textView_timeCounter);
        textView_question = (TextView) findViewById(R.id.textView_question);
        imageView_questionImage = (ImageView) findViewById(R.id.imageView_fileImage);
        Button button_quit = (Button) findViewById(R.id.button_quit);
        Button button_next = (Button) findViewById(R.id.button_next);
        radioGroup_answers = (RadioGroup) findViewById(R.id.radioGroup_answers);

        //2 Minutes
        new CountDownTimer(120000, 1000){
            @Override
            public void onTick(long millisecond) {
                textView_timeCounter.setText("Time Remaining: " + millisecond/1000 + " seconds");
            }
            @Override
            public void onFinish() {
                gotoStats();
            }
        }.start();
        index = 0;
        numCorrectAns = 0;

        if (getIntent() != null){
            questions = (ArrayList<Question>) getIntent().getExtras().getSerializable(MainActivity.arrray_Key);
        }

        totalQuestions = questions.size();
        //Log.d("demo", totalQuestions+"");

        setQuestionView(questions, index);
        new GetImage(imageView_questionImage, Trivia.this).execute(questions.get(index).getImage_URL());

        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //CHECK IF ANSWER IS CORRECT
                ArrayList<String> aList = questions.get(index).getAnswers();
                int radioButtonID = radioGroup_answers.getCheckedRadioButtonId();
                View radioButton = radioGroup_answers.findViewById(radioButtonID);
                int idx = radioGroup_answers.indexOfChild(radioButton);
                RadioButton r = (RadioButton)  radioGroup_answers.getChildAt(idx);
                String selectedtext = r.getText().toString();

                for (int i = 0; i < aList.size(); i++){
                    if (aList.get(i) == selectedtext && i == Integer.parseInt(questions.get(index).getAnswer())){
                        numCorrectAns +=1;
                    }
                }

                index +=1;
                //IF QUESTION MAX, CHANGE VIEW
                if (index == questions.size()){
                    gotoStats();
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

    private void gotoStats() {
        Intent intent = new Intent(Trivia.this, Stats.class);
        intent.putExtra(NUM_CORRECT_KEY, numCorrectAns);
        intent.putExtra(TOTAL_KEY, totalQuestions);
        startActivity(intent);
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

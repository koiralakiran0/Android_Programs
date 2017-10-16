package com.example.koira.midtermpracticepiazza;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_second);

        ArrayList<Result> list;
        ArrayList<Integer> layout;


        if (getIntent() != null && getIntent().getExtras() != null){
            list = (ArrayList<Result>) getIntent().getExtras().get(MainActivity.ARRAYLIST_CODE);
            layout = new ArrayList<>();

            ScrollView scrollView = new ScrollView(this);
            scrollView.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT));
            scrollView.setId(View.generateViewId());
            scrollView.setClickable(true);
            setContentView(scrollView);

            ConstraintLayout screen = new ConstraintLayout(this);
            screen.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            screen.setClickable(true);

            if (list.size() > 0){
                ConstraintLayout currentLayout = new ConstraintLayout(this);
                ConstraintLayout.LayoutParams currentLayoutParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);
                currentLayoutParams.topToBottom = screen.getId();
                currentLayout.setLayoutParams(currentLayoutParams);
                currentLayout.setId(View.generateViewId());
                currentLayout.setClickable(true);
                currentLayout.setOnClickListener(this);
                ImageView currentProfilePic = new ImageView(this);
                currentProfilePic.setId(View.generateViewId());
            }
        }

            /*
        ConstraintLayout currentLayout = new ConstraintLayout(this);
        ConstraintLayout.LayoutParams currentLayoutParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);
        currentLayoutParams.topToBottom = screen.getId();
        currentLayout.setLayoutParams(currentLayoutParams);
        currentLayout.setId(View.generateViewId());
        currentLayout.setClickable(true);
        currentLayout.setOnClickListener(this);
        ImageView currentProfilePic = new ImageView(this);
        currentProfilePic.setId(View.generateViewId());*/
    }
}

/*
Name: Kiran Koirala, Curtrina Howell
Assignment: In-Class 02
File Name: Main Activity
 */

package com.example.kiran.inclass02;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText length1 = (EditText) findViewById(R.id.edit_Length1);
        final EditText length2 = (EditText) findViewById(R.id.edit_length2);

        Button calculate = (Button) findViewById(R.id.button_calculate);
        Button clear = (Button) findViewById(R.id.button_clear);

        ImageView triangle = (ImageView) findViewById(R.id.image_traingle);
        final ImageView square = (ImageView) findViewById(R.id.image_square);
        ImageView circle = (ImageView) findViewById(R.id.image_circle);

        final TextView shapeName = (TextView) findViewById(R.id.text_shapeName);
        final TextView text2 = (TextView) findViewById(R.id.text_Length2);
        final TextView answer = (TextView) findViewById(R.id.text_answer);

        triangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shapeName.setText("Triangle");
                length2.setVisibility(View.VISIBLE);
                text2.setVisibility(View.VISIBLE);
            }
        });

        square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shapeName.setText("Square");
                length2.setVisibility(View.INVISIBLE);
                text2.setVisibility(View.INVISIBLE);
            }
        });

        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shapeName.setText("Circle");
                length2.setVisibility(View.INVISIBLE);
                text2.setVisibility(View.INVISIBLE);
            }
        });
//TextUtils.isEmpty(length1.toString())

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shapeName.getText().toString() == "Square") {
                    double length = Double.parseDouble(length1.getText().toString());
                    double area = length * length;
                    answer.setText(area + "");
                } else if (shapeName.getText().toString() == "Circle") {
                    double rad = Double.parseDouble(length1.getText().toString());
                    double area = Math.PI * rad * rad;
                    answer.setText(area + "");
                } else if (shapeName.getText().toString() == "Triangle") {

                    double length_1 = Double.parseDouble(length1.getText().toString());
                    double length_2 = Double.parseDouble(length2.getText().toString());
                    double area = 0.5 * length_1 * length_2;
                    answer.setText(area + "");
                }
            }

        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                length1.setText("");
                length2.setText("");
                length2.setVisibility(View.VISIBLE);
                text2.setVisibility(View.VISIBLE);
                shapeName.setText("Select a shape");
                answer.setText("");
            }
        });

    }
}

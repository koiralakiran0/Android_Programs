package com.example.kiran.module3practice;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    String[] items = {"Bacon", "Cheese", "Garlic", "Green Pepper", "Mushroom", "Olives", "Onions", "Red Peppers"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.imageView_Pizza);
        Button button_Add = (Button) findViewById(R.id.button_AddToppings);
        Button button_Clear= (Button) findViewById(R.id.button_ClearPizza);
        Button button_Checkout= (Button) findViewById(R.id.button_Proceed);

        CheckBox delivery = (CheckBox) findViewById(R.id.checkBox_Delivery);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);



        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setNegativeButton("B")
    }
}

package com.example.kiran.homework_2;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import static java.lang.Character.isDigit;

public class Create_New_Contact extends AppCompatActivity {
    public ArrayList<Contacts> contacts = new ArrayList<>();
    DatePickerDialog.OnDateSetListener dateSetListener;
    static final int CAMERA_ACCESS_CODE = 10000;
    ImageView imageView_picture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__new__contact);

        imageView_picture = (ImageView) findViewById(R.id.image_camera);
        Button button_save = (Button) findViewById(R.id.button_Save);
        final EditText editText_firstName = (EditText) findViewById(R.id.edit_First);
        final EditText editText_lastName = (EditText) findViewById(R.id.edit_LastName);
        final EditText editText_phone = (EditText) findViewById(R.id.edit_Phone);
        final EditText editText_company = (EditText)findViewById(R.id.edit_Company);
        final EditText editText_email = (EditText)findViewById(R.id.edit_email);
        final EditText editText_URL = (EditText)findViewById(R.id.edit_URL);
        final EditText editText_address = (EditText) findViewById(R.id.edit_Address);
        final EditText editText_birthday = (EditText)findViewById(R.id.edit_BirthDay);
        final EditText editText_nickName = (EditText)findViewById(R.id.edit_NickName);
        final EditText editText_facebook = (EditText)findViewById(R.id.edit_FacebookURL);
        final EditText editText_twitter = (EditText)findViewById(R.id.edit_TwitterURL);
        final EditText editText_skype = (EditText)findViewById(R.id.edit_Skype);
        final EditText editText_youtube = (EditText)findViewById(R.id.edit_Youtube);


        editText_birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Create_New_Contact.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setCancelable(false);
                dialog.show();
            }
        });
        dateSetListener = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month+=1;
                //Date Validation
            }
        };

        imageView_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intent, CAMERA_ACCESS_CODE);
            }
        });
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isEmpty(editText_firstName) && !isEmpty(editText_lastName) && !isEmpty(editText_phone)){
                    if (!validatePhone(editText_phone)){
                        Toast.makeText(Create_New_Contact.this, "Phone number not valid!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if (!validateEmail(editText_email)){

                            Toast.makeText(Create_New_Contact.this, "INVALID EMAIL", Toast.LENGTH_SHORT).show();
                        }

                        if (!validateBirthday(editText_birthday)){

                            Toast.makeText(Create_New_Contact.this, "INVALID BIRTHDAY", Toast.LENGTH_SHORT).show();
                        }

                        if (validateEmail(editText_email) && validateBirthday(editText_birthday)){
                            contacts.add(new Contacts(editText_firstName.getText().toString(),
                                    editText_lastName.getText().toString(), editText_company.getText().toString(), editText_phone.getText().toString(),
                                    editText_email.getText().toString(), editText_URL.getText().toString(), editText_address.getText().toString(), editText_birthday.getText().toString(),
                                    editText_nickName.getText().toString(), editText_facebook.getText().toString(), editText_twitter.getText().toString(), editText_youtube.getText().toString(),
                                    editText_skype.getText().toString()));
                            Intent intent = new Intent(Create_New_Contact.this, Contacts_List.class);
                            startActivity(intent);
                        }
                    }
                }
                else{
                    Toast.makeText(Create_New_Contact.this, "Enter firstName, LastName and Phone to proceed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateBirthday(EditText editText_birthday) {
        String target =  editText_birthday.getText().toString().trim();
        return true;
    }

    private boolean validateEmail(EditText editText_email) {
        String target =  editText_email.getText().toString().trim();
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Create_New_Contact.CAMERA_ACCESS_CODE && resultCode == Activity.RESULT_OK) {
            super.onActivityResult(requestCode, resultCode, data);
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView_picture.setImageBitmap(photo);
        }
    }

    private boolean validatePhone(EditText editText_phone) {
        String string =  editText_phone.getText().toString().trim();
        for (int i = 0; i < string.length(); i++){
            if (!(isDigit(string.charAt(i)) || string.charAt(i) == '+')){
                return false;
            }
        }
        return true;
    }

    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().length() == 0;
    }
}

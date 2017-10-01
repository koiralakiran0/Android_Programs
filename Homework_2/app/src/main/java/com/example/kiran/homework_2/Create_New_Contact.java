package com.example.kiran.homework_2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.lang.Character.isDigit;

public class Create_New_Contact extends AppCompatActivity {
    public ArrayList<Contacts> contacts = new ArrayList<>();
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
        EditText editText_company = (EditText)findViewById(R.id.edit_Company);
        final EditText editText_email = (EditText)findViewById(R.id.edit_email);
        EditText editText_URL = (EditText)findViewById(R.id.edit_URL);
        EditText editText_address = (EditText) findViewById(R.id.edit_Address);
        final EditText editText_birthday = (EditText)findViewById(R.id.edit_BirthDay);
        EditText editText_nickName = (EditText)findViewById(R.id.edit_NickName);
        EditText editText_facebook = (EditText)findViewById(R.id.edit_FacebookURL);
        EditText editText_twitter = (EditText)findViewById(R.id.edit_TwitterURL);
        EditText editText_skype = (EditText)findViewById(R.id.edit_Skype);
        EditText editText_youtube = (EditText)findViewById(R.id.edit_Youtube);


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
        return false;
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

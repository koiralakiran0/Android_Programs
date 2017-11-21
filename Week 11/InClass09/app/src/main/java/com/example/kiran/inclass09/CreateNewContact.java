package com.example.kiran.inclass09;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateNewContact extends AppCompatActivity {
    private ImageView imageView_select;
    private EditText editText_Name;
    private EditText editText_email;
    private EditText editText_phone;
    private RadioGroup radioGroup;
    private Button button_submit;
    static final String GET_IMAGE_TAG = "image tag";
    static final int REQ_CODE = 3;

    String avatar = "";

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_contact);

        user = FirebaseAuth.getInstance().getCurrentUser();
        myRef = database.getReference(user.getUid());

        imageView_select = (ImageView) findViewById(R.id.image_Select);
        editText_Name = (EditText) findViewById(R.id.edit_Name);
        editText_email = (EditText) findViewById(R.id.editText_email);
        editText_phone = (EditText) findViewById(R.id.editText_Phone);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        button_submit = (Button) findViewById(R.id.button_submit);

        imageView_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateNewContact.this, SelectAvatar.class);
                startActivityForResult(intent, REQ_CODE);
            }
        });

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create new Contact
                String department = "";
                RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radioGroup);

                if (radiogroup.getCheckedRadioButtonId() == R.id.radio_SIS){
                    department = "SIS";
                } else if (radiogroup.getCheckedRadioButtonId() == R.id.radio_CIS){
                    department = "CS";
                } else if (radiogroup.getCheckedRadioButtonId() == R.id.radio_BIO){
                    department = "BIO";
                }

                //Validation and Firebase Object Database Storage
                if (validateEmail(editText_email.getText().toString())
                        && editText_Name.getText().toString().length() > 0
                        && editText_phone.getText().toString().length() > 0){
                    Contact contact = new Contact(editText_Name.getText().toString(), editText_email.getText().toString(),
                            editText_phone.getText().toString(), department, avatar);
                    myRef.child("Contacts").push().setValue(contact);
                    Toast.makeText(CreateNewContact.this, "New Contact Created", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(CreateNewContact.this, "Informations Not Valid!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    boolean validateEmail(String email){
        if (email == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE){
            if (resultCode == RESULT_OK){
                int id = data.getExtras().getInt(GET_IMAGE_TAG);
                if (id == R.id.image_female1){
                    imageView_select.setImageDrawable(getResources().getDrawable(R.drawable.avatar_f_1));
                    avatar = "R.drawable.avatar_f_1";
                }else if (id == R.id.image_female2){
                    imageView_select.setImageDrawable(getResources().getDrawable(R.drawable.avatar_f_2));
                    avatar = "R.drawable.avatar_f_2";
                } else if (id == R.id.image_female3) {
                    imageView_select.setImageDrawable(getResources().getDrawable(R.drawable.avatar_f_3));
                    avatar = "R.drawable.avatar_f_3";
                } else if (id == R.id.image_male1){
                    imageView_select.setImageDrawable(getResources().getDrawable(R.drawable.avatar_m_3));
                    avatar = "R.drawable.avatar_m_3";
                } else if (id == R.id.image_male2){
                    imageView_select.setImageDrawable(getResources().getDrawable(R.drawable.avatar_m_2));
                    avatar = "R.drawable.avatar_m_2";
                }else if (id == R.id.image_male3){
                    imageView_select.setImageDrawable(getResources().getDrawable(R.drawable.avatar_m_1));
                    avatar = "R.drawable.avatar_m_1";
                }
            }
        }
    }
}
package com.example.koira.inclass07;

/*
Assignment # : In Class 7
File Name: CreateNewContact.java
Full Names: Kiran Koirala
 */
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class CreateNewContact extends Fragment {

    private OnFragmentInteractionListener mListener;

    public CreateNewContact() {

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ImageView imageView = getActivity().findViewById(R.id.imageView_contactImage);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.Container, new SelectAvatar(), "selectAvatar")
                        .addToBackStack(null)
                        .commit();
            }
        });

        getActivity().findViewById(R.id.button_Submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name = getActivity().findViewById(R.id.editText_Name);
                EditText email = getActivity().findViewById(R.id.editText_Email);
                EditText phone = getActivity().findViewById(R.id.editText_Phone);

                String department = "";
                RadioGroup radioGroup = getActivity().findViewById(R.id.radioGroup_dep);
                if (radioGroup.getCheckedRadioButtonId() == R.id.radioButton_BIO){
                    department = "BIO";
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.radioButton_CS){
                    department = "CS";
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.radioButton_SIS){
                    department = "SIS";
                }

                Contact contact = new Contact(name.getText().toString(),
                        email.getText().toString(), phone.getText().toString(),
                        department, "");
                //Pass contact to MainActivity
                //mListener.onButtonClicked(contact);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_new_contact, container, false);
    }

    public void onButtonPressed() {
        if (mListener != null) {
            mListener.changeToSelectAvatar();
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        public void changeToSelectAvatar();
        public void onButtonClicked(Contact contact);
    }
}

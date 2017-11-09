package com.example.koira.inclass07;

/*
Assignment # : In Class 7
File Name: Contacts.java
Full Names: Kiran Koirala
 */
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class Contacts extends Fragment {
    private OnFragmentInteractionListener mListener;


    public Contacts() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*
        ListView listView = (ListView) findViewById(R.id.Container);
        ContactListAdapter adapter = new ContactListAdapter(this, R.layout.contact_item, manyContacts);
        listView.setAdapter(adapter);
        */
        getActivity().findViewById(R.id.button_createNew).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.gotoCreateNew();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e){
            throw new RuntimeException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
       public void gotoCreateNew();
    }
}

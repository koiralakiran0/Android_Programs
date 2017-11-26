package com.example.koira.inclass07;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SelectAvatar extends Fragment {

    private Contacts.OnFragmentInteractionListener mListener;

    public SelectAvatar() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_select_avatar, container, false);
        getActivity().setTitle("Select Avatar");

        view.findViewById(R.id.imageView_b1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.setIconId(R.drawable.avatar_m_1);
            }
        });

        view.findViewById(R.id.imageView_b2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.setIconId(R.drawable.avatar_m_2);
            }
        });

        view.findViewById(R.id.imageView_b3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.setIconId(R.drawable.avatar_m_3);
            }
        });

        view.findViewById(R.id.imageView_g1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.setIconId(R.drawable.avatar_f_1);
            }
        });

        view.findViewById(R.id.imageView_g2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.setIconId(R.drawable.avatar_f_2);
            }
        });

        view.findViewById(R.id.imageView_g3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.setIconId(R.drawable.avatar_f_3);
            }
        });
        return view;
    }

    public interface OnFragmentInteractionListener {
        void setIconId(int iconId);
    }

}

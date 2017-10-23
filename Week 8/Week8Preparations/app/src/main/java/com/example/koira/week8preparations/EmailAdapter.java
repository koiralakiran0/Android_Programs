package com.example.koira.week8preparations;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by koira on 10/23/2017.
 */

public class EmailAdapter extends ArrayAdapter<Email> {
    public EmailAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Email> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Email email = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.email_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textViewSubject = (TextView) convertView.findViewById(R.id.textViewSubject);
            viewHolder.textViewSummary = (TextView) convertView.findViewById(R.id.textViewSummary);
            viewHolder.textViewEmail = (TextView) convertView.findViewById(R.id.textViewEmail);
            convertView.setTag(viewHolder);
        } else {

            // TextView textViewSubject = convertView.findViewById(R.id.textViewSubject);
            // TextView textViewSummary = convertView.findViewById(R.id.textViewSummary);
            // TextView textViewEmail = convertView.findViewById(R.id.textViewEmail);

            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textViewSubject.setText(email.subject);
        viewHolder.textViewSummary.setText(email.summary);
        viewHolder.textViewEmail.setText(email.sender);
        return convertView;
    }

    private static class ViewHolder{
        TextView textViewSubject;
        TextView textViewSummary;
        TextView textViewEmail;
    }
}

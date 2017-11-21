package com.example.kiran.inclass09;

import android.content.Context;
import android.media.Image;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by koira on 11/17/2017.
 */

public class ContactsAdapter extends ArrayAdapter<Contact>{

    public ContactsAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contact contact = getItem(position);

        ViewHolder viewHolder;

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.contact_Name);
            viewHolder.email = (TextView) convertView.findViewById(R.id.contact_email);
            viewHolder.phone = (TextView) convertView.findViewById(R.id.contact_phone);
            viewHolder.dept = (TextView) convertView.findViewById(R.id.contact_department);
            viewHolder.profilepic = (ImageView) convertView.findViewById(R.id.contact_imageView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(contact.getName());
        viewHolder.email.setText(contact.getEmail());
        viewHolder.phone.setText(contact.getPhone());
        viewHolder.dept.setText(contact.getDepartment());

        String image_string = contact.getImage();

        if (image_string.equals("R.drawable.avatar_m_3")){
            viewHolder.profilepic.setImageDrawable(getContext().getResources().getDrawable(R.drawable.avatar_m_3));
        } else if (image_string.equals("R.drawable.avatar_m_2")){
            viewHolder.profilepic.setImageDrawable(getContext().getResources().getDrawable(R.drawable.avatar_m_2));
        } else if (image_string.equals("R.drawable.avatar_m_1")){
            viewHolder.profilepic.setImageDrawable(getContext().getResources().getDrawable(R.drawable.avatar_m_1));
        } else if (image_string.equals("R.drawable.avatar_f_3")){
            viewHolder.profilepic.setImageDrawable(getContext().getResources().getDrawable(R.drawable.avatar_f_3));
        } else if (image_string.equals("R.drawable.avatar_f_2")){
            viewHolder.profilepic.setImageDrawable(getContext().getResources().getDrawable(R.drawable.avatar_f_2));
        } else if (image_string.equals("R.drawable.avatar_f_1")){
            viewHolder.profilepic.setImageDrawable(getContext().getResources().getDrawable(R.drawable.avatar_f_1));
        }

        return convertView;
    }

    private static class ViewHolder{
        TextView name;
        TextView email;
        TextView phone;
        TextView dept;
        ImageView profilepic;
    }
}

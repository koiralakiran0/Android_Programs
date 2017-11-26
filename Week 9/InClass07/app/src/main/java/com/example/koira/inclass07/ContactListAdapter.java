package com.example.koira.inclass07;

import android.content.Context;
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
 * Created by koira on 10/30/2017.
 */

public class ContactListAdapter extends ArrayAdapter<Contact> {
    Context context;

    public ContactListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contact contact = getItem(position);
        ViewHolder viewHolder;

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.listName);
            viewHolder.email =(TextView) convertView.findViewById(R.id.listEmail);
            viewHolder.dept = (TextView)convertView.findViewById(R.id.list_Dept);
            viewHolder.phone = (TextView)convertView.findViewById(R.id.listPhone);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.listImage);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(contact.getName());
        viewHolder.email.setText(contact.getEmail());
        viewHolder.dept.setText(contact.getDepartment());
        viewHolder.phone.setText(contact.getPhone());
        //viewHolder.image.setImageBitmap();
        viewHolder.picture
        return convertView;
    }

    private static class ViewHolder{
        TextView name;
        TextView email;
        TextView phone;
        TextView dept;
        ImageView image;
    }
}

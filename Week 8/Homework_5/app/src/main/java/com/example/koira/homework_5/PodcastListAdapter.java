package com.example.koira.homework_5;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by koira on 10/28/2017.
 */

public class PodcastListAdapter extends ArrayAdapter<Result>{
    Context context;

    public PodcastListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Result> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Result result = getItem(position);
        ViewHolder viewHolder;

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.result_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView_title = (TextView) convertView.findViewById(R.id.textView_title);
            viewHolder.imageView_image = (ImageView) convertView.findViewById(R.id.imageView_picture);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        convertView.setBackgroundColor(Color.WHITE);
        String editText_string = ((MainActivity)context).search.getText().toString().trim();
        if(result.getTitle().contains(editText_string) && !(editText_string.isEmpty())) {
            convertView.setBackgroundColor(Color.GREEN);
        }

        viewHolder.textView_title.setText(result.getTitle());
        Picasso.with(context).load(result.getSmallImage().trim()).into(viewHolder.imageView_image);
        return convertView;
    }

    private static class ViewHolder{
        TextView textView_title;
        ImageView imageView_image;
    }
}

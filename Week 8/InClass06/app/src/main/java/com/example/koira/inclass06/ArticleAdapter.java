package com.example.koira.inclass06;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by koira on 10/23/2017.
 */

public class ArticleAdapter extends ArrayAdapter<Article> {
    public ArticleAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Article> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Article article =  getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.article_item, parent, false);
        }

        TextView textViewTitle = convertView.findViewById(R.id.textViewTitle);
        TextView textViewAuthor = convertView.findViewById(R.id.textViewAuthor);
        TextView textViewPublishedAt = convertView.findViewById(R.id.textViewPublishedAt);
        ImageView imageView = convertView.findViewById(R.id.imageView_displayImage);

        textViewTitle.setText(article.getTitle());
        textViewAuthor.setText(article.getAuthor());
        textViewPublishedAt.setText(article.getPublishedAt());
        imageView.setImageBitmap(null);

        return convertView;
    }
}

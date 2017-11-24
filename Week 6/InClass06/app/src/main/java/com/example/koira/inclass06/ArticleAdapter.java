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

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by koira on 11/22/2017.
 */


public class ArticleAdapter extends ArrayAdapter<Article> {
    public ArticleAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Article> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Article article =  getItem(position);

        ViewHolder viewHolder;

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.article_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txtTitle = convertView.findViewById(R.id.textViewTitle);
            viewHolder.txtAuthor = convertView.findViewById(R.id.textViewAuthor);
            viewHolder.txtDate = convertView.findViewById(R.id.textViewPublishedAt);
            viewHolder.imageView = convertView.findViewById(R.id.imageView_displayImage);

            viewHolder.txtTitle.setText(article.title);

            viewHolder.txtAuthor.setText(article.author);
            viewHolder.txtDate.setText(article.publishedAt);
            if (article.urlToImage.equals("") || article.urlToImage == null){

            }else {
                Picasso.with(getContext()).load(article.urlToImage).into(viewHolder.imageView);
            }

        }


        return convertView;
    }

    private static class ViewHolder{
        TextView txtTitle;
        TextView txtAuthor;
        TextView txtDate;
        ImageView imageView;
    }
}

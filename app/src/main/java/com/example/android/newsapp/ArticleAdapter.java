package com.example.android.newsapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ArticleAdapter extends ArrayAdapter<Article> {

    public ArticleAdapter(Context context, ArrayList<Article> articles) {
        super(context, 0, articles);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

        Article currentArticle = getItem(position);
        TextView articleHeader = (TextView) listItemView.findViewById(R.id.header_view);
        articleHeader.setText(currentArticle.getHeader());
        TextView articleContents = (TextView) listItemView.findViewById(R.id.contents_view);
        articleContents.setText(currentArticle.getContents());
        //TextView articleAuthor = (TextView) listItemView.findViewById(R.id.author_view);
       // if (currentArticle.hasAuthor()) {
        //    articleAuthor.setText(currentArticle.getAuthor());
       //     articleAuthor.setVisibility(View.VISIBLE);
       // } else {
      //      articleAuthor.setVisibility(View.GONE);
       // }

        Date dateObject = new Date(currentArticle.getTimeInMilliseconds());

        TextView dateView = (TextView) listItemView.findViewById(R.id.date_view);
        String formattedDate = formatDate(dateObject);
        dateView.setText(formattedDate);

        return listItemView;
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }
}
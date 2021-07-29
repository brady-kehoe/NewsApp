package com.example.android.newsapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

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
        TextView articleAuthor = (TextView) listItemView.findViewById(R.id.author_view);
         if (currentArticle.hasAuthor() == 0) {
            articleAuthor.setText(currentArticle.getAuthor());
            articleAuthor.setVisibility(View.VISIBLE);
            } else {
             articleAuthor.setVisibility(View.GONE);
            }
        TextView articlePublishDateView = listItemView.findViewById(R.id.date_view);
        articlePublishDateView.setText(currentArticle.getTime());

        return listItemView;
    }
}

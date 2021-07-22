package com.example.android.newsapp;

import android.content.Context;

import androidx.loader.content.AsyncTaskLoader;

import java.util.Collection;
import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<Article>> {


    private String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Article> loadInBackground() {
        if (mUrl == null) {
            return null;
        }


        List<Article> articles = Utilities.ArticleData(mUrl);
        return articles;
    }
}
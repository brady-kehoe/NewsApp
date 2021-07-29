package com.example.android.newsapp;

import android.view.View;

public class Article {

    private final String mCategory;

    private final String mTitle;

    private final String mTime;

    private String mAuthor;

    private final String mUrl;


    public Article(String category, String title, String time, String author, String url) {
        mCategory = category;
        mTitle = title;
        mTime = time;
        mAuthor = author;
        mUrl = url;
    }

    public String getHeader()
    {
        return mCategory;
    }

    public String getContents()
    {
        return mTitle;
    }

    public String getTime()
    {
        return mTime;
    }

    public String getAuthor()
    {
        return mAuthor;
    }

    public int hasAuthor()
    {
        String auth = mAuthor;
        if (!auth.equals(""))
            return View.VISIBLE;
        else
            return View.GONE;

    }

    public String getUrl() {
        return mUrl;
    }
}
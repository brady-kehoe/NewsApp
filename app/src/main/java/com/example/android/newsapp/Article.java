package com.example.android.newsapp;

public class Article {

    private final String mCategory;

    private final String mTitle;

    private final String mTime;

    private String mAuthor;


    private final String mUrl;

    public Article(String category, String title, String time, String url) {
        mCategory = category;
        mTitle = title;
        mTime = time;
        mUrl = url;
    }

    /*public Article(String category, String title, long timeInMilliseconds, String author, String url) {
        mCategory = category;
        mTitle = title;
        mTimeInMilliseconds = timeInMilliseconds;
        mAuthor = author;
        mUrl = url;
    }*/

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

    /*public String getAuthor()
    {
        return mAuthor;
    }

    public boolean hasAuthor()
    {
        String auth = mAuthor;
        if (auth != null)
            return true;
        else
            return false;

    }*/

    public String getUrl() {
        return mUrl;
    }
}
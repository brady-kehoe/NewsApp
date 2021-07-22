package com.example.android.newsapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Utilities {

    private static final String LOG_TAG = Utilities.class.getSimpleName();

    private Utilities() {

    }

    public static List<Article> ArticleData(String requestUrl){
        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error During HTTP Request.", e);
        }

        List<Article> articles = extractFeatureFromJson(jsonResponse);

        return articles;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e){
            Log.e(LOG_TAG, "Problem building the URL.", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


    private static List<Article> extractFeatureFromJson(String articleJSON) {
        if (TextUtils.isEmpty(articleJSON)) {
            return null;
        }

        List<Article> articles = new ArrayList<>();

        try {

            JSONObject baseJsonResponse = new JSONObject(articleJSON);

            JSONArray articleArray = baseJsonResponse.getJSONArray("features");

            for (int i = 0; i < articleArray.length(); i++) {

                JSONObject currentEarthquake = articleArray.getJSONObject(i);

                JSONObject properties = currentEarthquake.getJSONObject("properties");

                String category = properties.getString("sectionName");

                String location = properties.getString("webTitle");

                long time = properties.getLong("webPublicationDate");

                //String author = properties.getString("firstName" +" " + "lastName");

                String url = properties.getString("webUrl");

                Article article = new Article(category, location, time, url);

                articles.add(article);
            }

        } catch (JSONException e) {
            Log.e("Utilities", "Problem parsing the earthquake JSON results", e);
        }

        return articles;
    }
}

package com.sharma.deepak.newsapp.network;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.sharma.deepak.newsapp.model.Article;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by deepak on 08-07-2017.
 */

public class NewsLoader extends AsyncTaskLoader<ArrayList<Article>> {

    public NewsLoader(Context context) {
        super(context);
    }

    @Override
    public ArrayList<Article> loadInBackground() {
        ArrayList<Article> bookList = null;
        try {
            String responseString = NetworkConnection.getResponseFromHttpUrl(NetworkConnection.buildUrl());
            bookList = NewsResponseData.getArticleData(responseString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookList;
    }
}

package com.sharma.deepak.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sharma.deepak.newsapp.adapter.ArticleAdapter;
import com.sharma.deepak.newsapp.model.Article;
import com.sharma.deepak.newsapp.network.NetworkConnection;
import com.sharma.deepak.newsapp.network.NewsLoader;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ArticleAdapter.OnArticleClickListener, LoaderManager.LoaderCallbacks<ArrayList<Article>> {

    private RecyclerView mRvArticle;
    private ProgressBar mPbProgress;
    private TextView mTvListMsg;
    private static final int LOADER_CONSTANT = 1;
    private LinearLayoutManager mLinearLayoutManager;

    /*
    * @author deepak
    * @created 8/7/2017
    * @description method to initialise and perform the logic of app
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewComponents();
        if (NetworkConnection.isNetworkConnected(this)) {
            mTvListMsg.setVisibility(View.INVISIBLE);
            mRvArticle.setVisibility(View.VISIBLE);
            getSupportLoaderManager().initLoader(LOADER_CONSTANT, null, this).forceLoad();
        } else {
            mTvListMsg.setText(getString(R.string.no_conncetion));
            mTvListMsg.setVisibility(View.VISIBLE);
            mRvArticle.setVisibility(View.INVISIBLE);
        }
    }

    /*
    * @author deepak
    * @created 8/7/2017
    * @description method to initialise view components
    * */
    private void initViewComponents() {
        mRvArticle = (RecyclerView) findViewById(R.id.rv_news_list);
        mPbProgress = (ProgressBar) findViewById(R.id.pb_loading);
        mTvListMsg = (TextView) findViewById(R.id.tv_no_list_item);
    }


    /*
    * @author deepak
    * @created 8/7/2017
    * @description method to open url in browser
    * */
    @Override
    public void OnArticleClick(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    /*
    * @author deepak
    * @created 8/7/2017
    * @description method to create the loader
    * */
    @Override
    public Loader<ArrayList<Article>> onCreateLoader(int id, Bundle args) {
        mPbProgress.setVisibility(View.VISIBLE);
        return new NewsLoader(this);
    }


    /*
    * @author deepak
    * @created 8/7/2017
    * @description method called when loading is finished
    * */
    @Override
    public void onLoadFinished(Loader<ArrayList<Article>> loader, ArrayList<Article> data) {
        mPbProgress.setVisibility(View.INVISIBLE);
        if (data != null && data.size() > 0) {
            mTvListMsg.setVisibility(View.INVISIBLE);
            mRvArticle.setVisibility(View.VISIBLE);
            mLinearLayoutManager = new LinearLayoutManager(MainActivity.this);
            mRvArticle.setHasFixedSize(true);
            mRvArticle.setLayoutManager(mLinearLayoutManager);
            ArticleAdapter rcAdapter = new ArticleAdapter(MainActivity.this, this, data);
            mRvArticle.setAdapter(rcAdapter);

        } else {
            mTvListMsg.setText(getString(R.string.no_items_in_list));
            mTvListMsg.setVisibility(View.VISIBLE);
            mRvArticle.setVisibility(View.INVISIBLE);
        }
    }

    /*
    * @author deepak
    * @created 8/7/2017
    * @description method called when loader is reset
    * */
    @Override
    public void onLoaderReset(Loader<ArrayList<Article>> loader) {

    }
}

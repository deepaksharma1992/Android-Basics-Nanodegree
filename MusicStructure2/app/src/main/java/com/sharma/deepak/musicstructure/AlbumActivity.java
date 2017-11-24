package com.sharma.deepak.musicstructure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

public class AlbumActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        initViewElements();
    }

    private void initViewElements() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageButton mIbvArtistButton, mIbHomeButton, mIbGenreButton, mIbPlaylistButton;
        mIbvArtistButton = (ImageButton) findViewById(R.id.ib_artist);
        mIbvArtistButton.setOnClickListener(this);
        mIbHomeButton = (ImageButton) findViewById(R.id.ib_home);
        mIbHomeButton.setOnClickListener(this);
        mIbGenreButton = (ImageButton) findViewById(R.id.ib_genre);
        mIbGenreButton.setOnClickListener(this);
        mIbPlaylistButton = (ImageButton) findViewById(R.id.ib_playlist);
        mIbPlaylistButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_artist:
                Intent artistIntent = new Intent(this, ArtistActivity.class);
                startActivity(artistIntent);
                break;
            case R.id.ib_home:
                Intent homeIntent = new Intent(this, MainActivity.class);
                startActivity(homeIntent);
                break;
            case R.id.ib_genre:
                Intent genreIntent = new Intent(this, GenreActivity.class);
                startActivity(genreIntent);
                break;
            case R.id.ib_playlist:
                Intent playListIntent = new Intent(this, PlaylistActivity.class);
                startActivity(playListIntent);
                break;
        }
    }

}

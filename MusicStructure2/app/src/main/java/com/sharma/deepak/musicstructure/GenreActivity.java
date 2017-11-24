package com.sharma.deepak.musicstructure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

public class GenreActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);
        initViewElements();
    }

    private void initViewElements() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageButton mIbvArtistButton, mIbAlbumButton, mIbHomeButton, mIbPlaylistButton;
        mIbvArtistButton = (ImageButton) findViewById(R.id.ib_artist);
        mIbvArtistButton.setOnClickListener(this);
        mIbAlbumButton = (ImageButton) findViewById(R.id.ib_album);
        mIbAlbumButton.setOnClickListener(this);
        mIbHomeButton = (ImageButton) findViewById(R.id.ib_home);
        mIbHomeButton.setOnClickListener(this);
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
            case R.id.ib_album:
                Intent albumIntent = new Intent(this, AlbumActivity.class);
                startActivity(albumIntent);
                break;
            case R.id.ib_home:
                Intent homeIntent = new Intent(this, MainActivity.class);
                startActivity(homeIntent);
                break;
            case R.id.ib_playlist:
                Intent playListIntent = new Intent(this, PlaylistActivity.class);
                startActivity(playListIntent);
                break;
        }
    }
}


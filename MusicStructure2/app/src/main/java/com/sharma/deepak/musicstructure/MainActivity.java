package com.sharma.deepak.musicstructure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewElements();
    }

    private void initViewElements() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageButton mIbvArtistButton, mIbAlbumButton, mIbGenreButton, mIbPlaylistButton;
        mIbvArtistButton = (ImageButton) findViewById(R.id.ib_artist);
        mIbvArtistButton.setOnClickListener(this);
        mIbAlbumButton = (ImageButton) findViewById(R.id.ib_album);
        mIbAlbumButton.setOnClickListener(this);
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
            case R.id.ib_album:
                Intent albumIntent = new Intent(this, AlbumActivity.class);
                startActivity(albumIntent);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_pay:
                Intent payIntent = new Intent(this, PaymentActivity.class);
                startActivity(payIntent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

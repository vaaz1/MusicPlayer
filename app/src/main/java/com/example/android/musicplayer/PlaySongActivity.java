package com.example.android.musicplayer;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class PlaySongActivity extends AppCompatActivity {

    boolean playing;
    boolean repeat, shuffle;
    int currentIdx;
    String playlistName;
    ImageView iv_repeat;
    ImageView iv_shuffle;
    ImageView pausePlay;
    ImageView songArt;
    TextView songTitle;
    TextView songArtist;
    ArrayList<Song> songList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);

        Intent intent = getIntent();
        songArt = findViewById(R.id.iv_song_img);
        songTitle = findViewById(R.id.tv_song_title);
        songArtist = findViewById(R.id.tv_artist);
        currentIdx = intent.getIntExtra("CurrentIndex", 0);
        repeat = intent.getBooleanExtra("RepeatMode", false);
        shuffle = intent.getBooleanExtra("ShuffleMode", false);
        playlistName = intent.getStringExtra("PlaylistName");
        songList = intent.getParcelableArrayListExtra(Song.TYPE);

        TextView tv_playlistName = findViewById(R.id.tv_playlist_title);
        tv_playlistName.setText(playlistName);

        iv_repeat = findViewById(R.id.iv_repeat);
        displayRepeat();
        iv_shuffle = findViewById(R.id.iv_shuffle);
        displayShuffle();

        setSong(currentIdx);

        ImageView arrowBack = findViewById(R.id.iv_screen_back);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(PlaySongActivity.this, SongsActivity.class);
                backIntent.putExtra("PlaylistName", playlistName);
                backIntent.putExtra("repeatState", repeat);
                backIntent.putExtra("shuffleState", shuffle);
                startActivity(backIntent);
            }
        });

        pausePlay = findViewById(R.id.iv_play_pause);
        pausePlay.setImageResource(R.drawable.ic_pause);
        playing = true;
        pausePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playing = !playing; // toggle state
                displayPlaying();
            }
        });

        ImageView next = findViewById(R.id.iv_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentIdx < songList.size() - 1) {
                    currentIdx += 1;
                    setSong(currentIdx);
                } else {
                    if (repeat) {
                        currentIdx = 0;
                        setSong(currentIdx);
                    } else {
                        Toast toast = Toast.makeText(PlaySongActivity.this, "Last song playing", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });

        ImageView prev = findViewById(R.id.iv_prev);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentIdx > 0) {
                    currentIdx -= 1;
                    setSong(currentIdx);
                } else {
                    if (repeat) {
                        currentIdx = songList.size() - 1;
                        setSong(currentIdx);
                    } else {
                        Toast toast = Toast.makeText(PlaySongActivity.this, "First song playing", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        shuffle = savedInstanceState.getBoolean("shuffleState");
        repeat = savedInstanceState.getBoolean("repeatState");
        playing = savedInstanceState.getBoolean("playingState");
        currentIdx = savedInstanceState.getInt("songIdx");
        displayShuffle();
        displayRepeat();
        displayPlaying();
        setSong(currentIdx);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("shuffleState", shuffle);
        outState.putBoolean("repeatState", repeat);
        outState.putBoolean("playingState", playing);
        outState.putInt("songIdx", currentIdx);
    }

    private void setSong(int index) {
        //sets ImgageResource, Title and Artist of to current Song
        songArt.setImageResource(songList.get(index).getmImageResource());
        songTitle.setText(songList.get(index).getmTitle());
        songArtist.setText(songList.get(index).getmArtist());
    }

    public void displayShuffle() {
        if (shuffle) {
            iv_shuffle.setImageResource(R.drawable.ic_action_playback_schuffle);
        } else {
            iv_shuffle.setImageResource(R.drawable.ic_action_playback_schuffle_white);
        }
    }

    public void displayRepeat() {
        if (repeat) {
            iv_repeat.setImageResource(R.drawable.ic_action_playback_repeat);
        } else {
            iv_repeat.setImageResource(R.drawable.ic_action_playback_repeat_white);
        }
    }

    public void displayPlaying() {
        if (playing) {
            pausePlay.setImageResource(R.drawable.ic_pause);
        } else {
            pausePlay.setImageResource(R.drawable.ic_play_arrow);
        }
    }
}


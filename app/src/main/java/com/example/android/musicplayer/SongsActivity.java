package com.example.android.musicplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class SongsActivity extends AppCompatActivity {

    boolean repeatON, shuffleON;
    ArrayList<Song> songs = new ArrayList<Song>();
    ImageView iv_shuffle;
    ImageView iv_repeat;
    TextView tvPlaylist;
    String playlistName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);

        Intent intent = getIntent();
        playlistName = intent.getStringExtra("PlaylistName");
        tvPlaylist = findViewById(R.id.tv_playlist_title);
        tvPlaylist.setText(playlistName);

        ImageView arrowBack = findViewById(R.id.iv_screen_back);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(SongsActivity.this, MainActivity.class);
                startActivity(backIntent);
            }
        });

        Button playAllButton = findViewById(R.id.bt_play_all);
        playAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPlaying(songs.get(0));
            }
        });

        iv_shuffle = findViewById(R.id.iv_shuffle);
        iv_repeat = findViewById(R.id.iv_repeat);

        //set iv_repeat
        repeatON = intent.getBooleanExtra("repeatState", false);
        displayRepeat();

        //set iv_shuffle
        shuffleON = intent.getBooleanExtra("shuffleState", false);
        displayShuffle();

        //set song list
        songs = fillSongList(songs);
        SongAdapter adapter = new SongAdapter(this, songs);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Song currentSong = songs.get(pos);
                startPlaying(currentSong);
            }
        });

        iv_repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repeatON = !repeatON;
                displayRepeat();
            }
        });

        iv_shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shuffleON = !shuffleON;
                displayShuffle();
            }
        });
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        shuffleON = savedInstanceState.getBoolean("shuffleState");
        repeatON = savedInstanceState.getBoolean("repeatState");
        displayShuffle();
        displayRepeat();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("shuffleState", shuffleON);
        outState.putBoolean("repeatState", repeatON);
    }

    private ArrayList<Song> fillSongList(ArrayList<Song> songs) {
        if (songs.size() != 0) {
            songs.clear();
        }
        songs.add(new Song(R.drawable.ic_music_note, "Sun is shining", "Bob Marley"));
        songs.add(new Song(R.drawable.ic_music_note, "Halo", "Beyonce"));
        songs.add(new Song(R.drawable.ic_music_note, "Hymn for the weekend", "Coldplay"));
        songs.add(new Song(R.drawable.ic_music_note, "Los Abrazos Rotos", "Alberto Iglesias"));
        songs.add(new Song(R.drawable.ic_music_note, "Closer", "Collage"));
        songs.add(new Song(R.drawable.ic_music_note, "Many Men", "50 cent"));
        songs.add(new Song(R.drawable.ic_music_note, "Stupid girls", "Pink"));
        songs.add(new Song(R.drawable.ic_music_note, "Hips don't lie", "Shakira"));
        songs.add(new Song(R.drawable.ic_music_note, "Smoke on the water", "Deep Purple"));
        songs.add(new Song(R.drawable.ic_music_note, "The unforgiven", "Metallica"));
        songs.add(new Song(R.drawable.ic_music_note, "Friend of mine", "Avicii"));

        return songs;
    }

    public void startPlaying(Song currentSong) {
        Intent intent = new Intent(SongsActivity.this, PlaySongActivity.class);
        intent.putExtra("CurrentIndex", songs.indexOf(currentSong));
        intent.putExtra("RepeatMode", repeatON);
        intent.putExtra("ShuffleMode", shuffleON);
        intent.putExtra("PlaylistName", playlistName);
        intent.putParcelableArrayListExtra(Song.TYPE, songs);
        startActivity(intent);
    }

    public void displayShuffle() {
        if (shuffleON) {
            iv_shuffle.setImageResource(R.drawable.ic_action_playback_schuffle);
            Collections.shuffle(songs);
        } else {
            iv_shuffle.setImageResource(R.drawable.ic_action_playback_schuffle_white);
            songs = fillSongList(songs);
        }
    }

    public void displayRepeat() {
        if (repeatON) {
            iv_repeat.setImageResource(R.drawable.ic_action_playback_repeat);
        } else {
            iv_repeat.setImageResource(R.drawable.ic_action_playback_repeat_white);
        }
    }
}

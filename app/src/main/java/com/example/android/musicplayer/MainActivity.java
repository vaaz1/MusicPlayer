package com.example.android.musicplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<Playlist> playlists = new ArrayList<Playlist>();
        playlists.add(new Playlist(R.drawable.freestocks_org_73101_unsplash, "Electronic", "Various Artist")); //Photo by freestocks.org on Unsplash
        playlists.add(new Playlist(R.drawable.wesley_tingey_223061_unsplash, "Rock", "Various Artists")); //Photo by Wesley Tingey on Unsplash
        playlists.add(new Playlist(R.drawable.jamille_queiroz_1032_unsplash, "Acoustic", "Various Artists")); //Photo by Jamille Queiroz on Unsplash
        playlists.add(new Playlist(R.drawable.kristen_sturdivant_473430_unsplash, "Reggae", "Bob Marley")); //Photo by Kristen Sturdivant on Unsplash
        playlists.add(new Playlist(R.drawable.levi_alvarez_485073_unsplash, "Rap", "Various Artist")); //Photo by Levi Alvarez on Unsplash
        playlists.add(new Playlist(R.drawable.slim_emcee_ug_the_poet_truth_from_africa_photography_462233_unsplash, "R&B", "Various Artists")); //Photo by Slim Emcee ug the poet on Unsplash
        playlists.add(new Playlist(R.drawable.ic_music_note, "My favorites", "Various Artists"));
        playlists.add(new Playlist(R.drawable.ic_music_note, "Pink", "I'm not dead"));

        PlaylistAdapter adapter = new PlaylistAdapter(this, playlists);
        GridView gridView = (GridView) findViewById(R.id.grid);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Playlist currentPlaylist = playlists.get(pos);
                String title = currentPlaylist.getmTitle();
                Intent intent = new Intent(MainActivity.this, SongsActivity.class);
                intent.putExtra("PlaylistName", title);
                startActivity(intent);
            }
        });
    }

}

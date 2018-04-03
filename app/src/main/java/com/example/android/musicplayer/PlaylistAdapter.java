package com.example.android.musicplayer;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Vera on 28.03.2018.
 */

public class PlaylistAdapter extends ArrayAdapter<Playlist> {
    public PlaylistAdapter(Activity context, ArrayList<Playlist> playlist) {
        super(context, 0, playlist);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View gridItemView = convertView;
        if (gridItemView == null) {
            gridItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.playlist_item, parent, false);
        }

        Playlist currentPlaylist = getItem(position);

        ImageView playlistImageView = (ImageView) gridItemView.findViewById(R.id.playlist_image_view);
        playlistImageView.setImageResource(currentPlaylist.getmImageResource());

        TextView titleTextView = (TextView) gridItemView.findViewById(R.id.song_title_text_view);
        titleTextView.setText(currentPlaylist.getmTitle());

        TextView artistTextView = (TextView) gridItemView.findViewById(R.id.song_artist_text_view);
        artistTextView.setText(currentPlaylist.getmArtist());

        return gridItemView;
    }
}

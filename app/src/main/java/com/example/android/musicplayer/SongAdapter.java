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

public class SongAdapter extends ArrayAdapter<Song> {

    public SongAdapter(Activity context, ArrayList<Song> song) {
        super(context, 0, song);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.song_item, parent, false);
        }

        Song currentSong = getItem(position);

        ImageView songImageView = (ImageView) listItemView.findViewById(R.id.image_play);
        songImageView.setImageResource(currentSong.getmImageResource());

        TextView titleTextView = (TextView) listItemView.findViewById(R.id.song_title_text_view);
        titleTextView.setText(currentSong.getmTitle());

        TextView artistTextView = (TextView) listItemView.findViewById(R.id.song_artist_text_view);

        artistTextView.setText(currentSong.getmArtist());

        return listItemView;
    }
}

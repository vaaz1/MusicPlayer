package com.example.android.musicplayer;

/**
 * Created by Vera on 27.03.2018.
 */

public class Playlist {
    private int mImageResource;
    private String mTitle;
    private String mArtist;

    public Playlist(int imageResource, String title, String artist) {
        mImageResource = imageResource;
        mTitle = title;
        mArtist = artist;
    }

    public Playlist(int imageResource, String title) {
        mImageResource = imageResource;
        mTitle = title;
    }

    public int getmImageResource() {
        return mImageResource;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmArtist() {
        return mArtist;
    }

}

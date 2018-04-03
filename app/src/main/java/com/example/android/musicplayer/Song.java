package com.example.android.musicplayer;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Vera on 27.03.2018.
 */

public class Song implements Parcelable {

    public static final Parcelable.Creator<Song> CREATOR = new Parcelable.Creator<Song>() {
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        public Song[] newArray(int size) {
            return new Song[size];
        }
    };
    public static String TYPE = "songObject";
    private int mImageResource;
    private String mTitle;
    private String mArtist;

    public Song(int imageResource, String title, String artist) {
        mImageResource = imageResource;
        mTitle = title;
        mArtist = artist;
    }

    public Song(String title, String artist) {
        mTitle = title;
        mArtist = artist;
    }

    public Song(Parcel in) {
        mImageResource = in.readInt();
        mTitle = in.readString();
        mArtist = in.readString();
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

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeInt(mImageResource);
        out.writeString(mTitle);
        out.writeString(mArtist);
    }

    @Override
    public int describeContents() {
        return 0;
    }

}

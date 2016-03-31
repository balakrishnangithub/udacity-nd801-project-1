package com.krishnaentertainment.nd801.popularmovies.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by BK on 30-03-2016.
 */
public class Movie implements Parcelable {

    public Integer id;
    public Boolean adult;
    public String backdrop_path;
    public List<Integer> genres_ids;
    public String original_title;
    public String overview;
    public Double popularity;
    public String poster_path;
    public String release_date;
    public String title;
    public Double vote_average;
    public Integer vote_count;

    protected Movie(Parcel in) {
        id = in.readInt();
        adult = in.readByte() == 1;
        backdrop_path = in.readString();
        in.readList(genres_ids, Integer.class.getClassLoader());
        original_title = in.readString();
        overview = in.readString();
        popularity = in.readDouble();
        poster_path = in.readString();
        release_date = in.readString();
        title = in.readString();
        vote_average = in.readDouble();
        vote_count = in.readInt();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeByte((byte) (adult ? 1 : 0));
        dest.writeString(backdrop_path);
        dest.writeList(genres_ids);
        dest.writeString(original_title);
        dest.writeString(overview);
        dest.writeDouble(popularity);
        dest.writeString(poster_path);
        dest.writeString(release_date);
        dest.writeString(title);
        dest.writeDouble(vote_average);
        dest.writeInt(vote_count);
    }
}

package com.krishnaentertainment.nd801.popularmovies.utils;

import com.krishnaentertainment.nd801.popularmovies.models.GlobalConstants;
import com.krishnaentertainment.nd801.popularmovies.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class JsonParser {
    public static ArrayList<Movie> parseMoviesList(String jsonString) {
        ArrayList<Movie> moviesList = new ArrayList<>();
        if (jsonString == null)
            return moviesList;
        try {
            JSONArray moviesArray = new JSONObject(jsonString).getJSONArray(GlobalConstants.TMDB_MOVIE_RESULTS);
            int length = moviesArray.length();
            for (int i = 0; i < length; i++) {
                Movie movie = new Movie();
                JSONObject jsonObject = moviesArray.getJSONObject(i);
                movie.id = jsonObject.getInt(GlobalConstants.TMDB_PARAM_ID);
                movie.title = jsonObject.getString(GlobalConstants.TMDB_PARAM_TITLE);
                movie.posterPath = jsonObject.getString(GlobalConstants.TMDB_PARAM_POSTER_PATH);
                // TMDB bug: older movies don't have images
                if (Objects.equals(movie.posterPath, "null"))
                    continue;
                movie.backdropPath = jsonObject.getString(GlobalConstants.TMDB_PARAM_BACKDROP_PATH);
                movie.overview = jsonObject.getString(GlobalConstants.TMDB_PARAM_OVERVIEW);

                Date releaseDate = null;
                try {
                    releaseDate = new SimpleDateFormat("yyyy-MM-dd").parse(jsonObject.getString(GlobalConstants.TMDB_PARAM_RELEASE_DATE));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                movie.releaseDate = releaseDate;

                movie.popularity = jsonObject.getDouble(GlobalConstants.TMDB_PARAM_POPULARITY);
                movie.voteAverage = jsonObject.getDouble(GlobalConstants.TMDB_PARAM_VOTE_AVERAGE);
                movie.voteCount = jsonObject.getInt(GlobalConstants.TMDB_PARAM_VOTE_COUNT);

                moviesList.add(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return moviesList;
    }
}

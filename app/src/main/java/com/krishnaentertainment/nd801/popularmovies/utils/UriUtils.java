package com.krishnaentertainment.nd801.popularmovies.utils;

import android.net.Uri;

import com.krishnaentertainment.nd801.popularmovies.BuildConfig;
import com.krishnaentertainment.nd801.popularmovies.models.GlobalConstants;

public class UriUtils {
    public static String buildTMDBMovieUrl(String page, String sortBy) {
        return Uri.parse(GlobalConstants.TMDB_MOVIE_BASE_URL).buildUpon()
                .appendQueryParameter(GlobalConstants.TMDB_PARAM_PAGE, page)
                .appendQueryParameter(GlobalConstants.TMDB_PARAM_SORT_BY, sortBy)
                .appendQueryParameter(GlobalConstants.TMDB_PARAM_API_KEY, BuildConfig.TMDB_API_KEY)
                .build()
                .toString();
    }

    public static String buildTMDBImageUrl(String imageSize, String posterPath) {
        return GlobalConstants.TMDB_IMAGE_BASE_URL + imageSize + posterPath;
    }
}

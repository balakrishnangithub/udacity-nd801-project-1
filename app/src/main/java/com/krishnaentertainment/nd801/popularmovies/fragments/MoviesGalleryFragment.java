package com.krishnaentertainment.nd801.popularmovies.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.krishnaentertainment.nd801.popularmovies.R;
import com.krishnaentertainment.nd801.popularmovies.models.GlobalConstants;
import com.krishnaentertainment.nd801.popularmovies.utils.UriUtils;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesGalleryFragment extends Fragment {


    View root;

    public MoviesGalleryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_movies_gallery, container, false);
        return root;
    }

    private void loadMovies(int page, Boolean isDescSort) {
        String url = UriUtils.buildTMDBMovieUrl(
                String.valueOf(page),
                GlobalConstants.TMDB_PARAM_POPULARITY +
                        (isDescSort ? GlobalConstants.TMDB_SORT_DESC : GlobalConstants.TMDB_SORT_ASC)
        ).toString();
    }
}

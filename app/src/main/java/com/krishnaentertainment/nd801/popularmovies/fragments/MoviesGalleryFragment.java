package com.krishnaentertainment.nd801.popularmovies.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.krishnaentertainment.nd801.popularmovies.R;
import com.krishnaentertainment.nd801.popularmovies.models.GlobalConstants;
import com.krishnaentertainment.nd801.popularmovies.models.Movie;
import com.krishnaentertainment.nd801.popularmovies.managers.VolleySingleton;
import com.krishnaentertainment.nd801.popularmovies.utils.UriUtils;
import com.krishnaentertainment.nd801.popularmovies.views.adapters.MoviesGalleryAdapter;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesGalleryFragment extends Fragment {

    View root;
    RequestQueue requestQueue;
    MoviesGalleryAdapter moviesGalleryAdapter;

    public MoviesGalleryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_movies_gallery, container, false);
        requestQueue = VolleySingleton.getInstance(getActivity().getApplicationContext()).getRequestQueue();
        List<Movie> movies = null;
        moviesGalleryAdapter = new MoviesGalleryAdapter(getActivity(), movies);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.movies_recycler_view);

        return root;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (requestQueue != null)
            requestQueue.cancelAll(MoviesGalleryFragment.class);
    }

    private void loadMovies(int page, Boolean isDescSort) {
        String url = UriUtils.buildTMDBMovieUrl(
                String.valueOf(page),
                GlobalConstants.TMDB_PARAM_POPULARITY +
                        (isDescSort ? GlobalConstants.TMDB_SORT_DESC : GlobalConstants.TMDB_SORT_ASC)
        );
    }
}

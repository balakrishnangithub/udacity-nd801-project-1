package com.krishnaentertainment.nd801.popularmovies.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.krishnaentertainment.nd801.popularmovies.R;
import com.krishnaentertainment.nd801.popularmovies.managers.VolleySingleton;
import com.krishnaentertainment.nd801.popularmovies.models.GlobalConstants;
import com.krishnaentertainment.nd801.popularmovies.models.Movie;
import com.krishnaentertainment.nd801.popularmovies.utils.JsonParser;
import com.krishnaentertainment.nd801.popularmovies.utils.UriUtils;
import com.krishnaentertainment.nd801.popularmovies.views.adapters.MoviesGalleryAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesGalleryFragment extends Fragment {

    View root;
    GridLayoutManager gridLayoutManager;
    RequestQueue requestQueue;
    MoviesGalleryAdapter moviesGalleryAdapter;
    public static String MOVIES_CACHE = "movies_cache";

    public MoviesGalleryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_movies_gallery, container, false);
        requestQueue = VolleySingleton.getInstance(getActivity().getApplicationContext()).getRequestQueue();

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.movies_recycler_view);
        gridLayoutManager = new GridLayoutManager(getActivity(),
                getActivity().getResources().getInteger(R.integer.gallery_column));
        recyclerView.setLayoutManager(gridLayoutManager);
        moviesGalleryAdapter = new MoviesGalleryAdapter(getActivity());
        recyclerView.setAdapter(moviesGalleryAdapter);

        if (savedInstanceState == null)
            loadMovies(1, true);
        else
            moviesGalleryAdapter.set(savedInstanceState.<Movie>getParcelableArrayList(MOVIES_CACHE));
        return root;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (requestQueue != null)
            requestQueue.cancelAll(MoviesGalleryFragment.class);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (moviesGalleryAdapter != null)
            outState.putParcelableArrayList(MOVIES_CACHE, moviesGalleryAdapter.getMoviesList());
        super.onSaveInstanceState(outState);
    }

    private void loadMovies(int page, Boolean isDescSort) {
        String url = UriUtils.buildTMDBMovieUrl(
                String.valueOf(page),
                GlobalConstants.TMDB_PARAM_POPULARITY +
                        (isDescSort ? GlobalConstants.TMDB_SORT_DESC : GlobalConstants.TMDB_SORT_ASC)
        );
        StringRequest request = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        moviesGalleryAdapter.set(JsonParser.parseMoviesList(response));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v(MoviesGalleryFragment.class.getSimpleName(), error.getMessage());
                    }
                });
        request.setTag(MoviesGalleryFragment.class);
        requestQueue.add(request);
    }
}

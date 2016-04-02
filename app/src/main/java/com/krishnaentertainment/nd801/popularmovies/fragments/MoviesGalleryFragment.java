package com.krishnaentertainment.nd801.popularmovies.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.krishnaentertainment.nd801.popularmovies.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesGalleryFragment extends Fragment {


    public MoviesGalleryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_gallery, container, false);
    }

}

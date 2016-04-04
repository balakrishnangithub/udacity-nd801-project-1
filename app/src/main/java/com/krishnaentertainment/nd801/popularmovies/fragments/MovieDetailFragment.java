package com.krishnaentertainment.nd801.popularmovies.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.krishnaentertainment.nd801.popularmovies.R;
import com.krishnaentertainment.nd801.popularmovies.activities.MovieDetailActivity;
import com.krishnaentertainment.nd801.popularmovies.managers.VolleySingleton;
import com.krishnaentertainment.nd801.popularmovies.models.Movie;
import com.krishnaentertainment.nd801.popularmovies.utils.UriUtils;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends Fragment {


    View root;
    Movie movie;
    ImageLoader imageLoader;
    NetworkImageView backdropImage;
    TextView title, releaseDate, voteAverage, voteCount, overview;

    public MovieDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        movie = bundle.getParcelable(MovieDetailActivity.class.toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        imageLoader = VolleySingleton.getInstance(getActivity()).getImageLoader();

        backdropImage = (NetworkImageView) root.findViewById(R.id.backdropImage);
        title = (TextView) root.findViewById(R.id.movieTitle);
        releaseDate = (TextView) root.findViewById(R.id.releaseDate);
        voteAverage = (TextView) root.findViewById(R.id.voteAverage);
        voteCount = (TextView) root.findViewById(R.id.voteCount);
        overview = (TextView) root.findViewById(R.id.overview);

        backdropImage.setImageUrl(
                UriUtils.buildTMDBImageUrl(getActivity().getResources().getString(R.string.tmdb_backdrop_size_detail),
                        movie.backdropPath), imageLoader);
        title.setText(movie.title);
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        releaseDate.setText(dateFormat.format(movie.releaseDate));
        voteAverage.setText(movie.voteAverage.toString() + "★");
        voteCount.setText("(" + NumberFormat.getNumberInstance(Locale.US).format(movie.voteCount).toString() + ")");
        overview.setText(movie.overview);
        return root;
    }

}

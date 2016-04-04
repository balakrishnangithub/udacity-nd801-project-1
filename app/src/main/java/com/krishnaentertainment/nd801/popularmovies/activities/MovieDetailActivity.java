package com.krishnaentertainment.nd801.popularmovies.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.krishnaentertainment.nd801.popularmovies.R;
import com.krishnaentertainment.nd801.popularmovies.fragments.MovieDetailFragment;
import com.krishnaentertainment.nd801.popularmovies.models.Movie;

public class MovieDetailActivity extends AppCompatActivity {
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        movie = getIntent().getParcelableExtra(MovieDetailActivity.class.toString());
        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(MovieDetailActivity.class.toString(), movie);
        movieDetailFragment.setArguments(bundle);
        // http://stackoverflow.com/a/27037935/1753174
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.movie_detail_container, movieDetailFragment, "detailfragment")
                .commit();
        setContentView(R.layout.activity_movie_detail);
    }
}

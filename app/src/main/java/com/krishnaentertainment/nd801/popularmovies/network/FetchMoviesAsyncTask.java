package com.krishnaentertainment.nd801.popularmovies.network;

import android.os.AsyncTask;

import com.krishnaentertainment.nd801.popularmovies.models.Movie;

public class FetchMoviesAsyncTask extends AsyncTask<String, Void, Movie[]> {

    public interface FetchMoviesAsyncResponse {
        void processFinish(Movie[] movies);
    }

    public FetchMoviesAsyncResponse delegate = null;

    public FetchMoviesAsyncTask(FetchMoviesAsyncResponse delegate) {
        this.delegate = delegate;
    }

    @Override
    protected Movie[] doInBackground(String... params) {
        return new Movie[0];
    }

    @Override
    protected void onPostExecute(Movie[] movies) {
        super.onPostExecute(movies);
        delegate.processFinish(movies);
    }
}

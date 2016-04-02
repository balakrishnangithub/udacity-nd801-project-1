package com.krishnaentertainment.nd801.popularmovies.network;

import android.content.Context;
import android.os.AsyncTask;

public class FetchMoviesAsyncTask extends AsyncTask<String, Void, Void> {
    private Context context;

    FetchMoviesAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(String... params) {
        return null;
    }
}

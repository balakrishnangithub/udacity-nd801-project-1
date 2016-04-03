package com.krishnaentertainment.nd801.popularmovies.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.NetworkImageView;
import com.krishnaentertainment.nd801.popularmovies.R;
import com.krishnaentertainment.nd801.popularmovies.managers.VolleySingleton;
import com.krishnaentertainment.nd801.popularmovies.models.GlobalConstants;
import com.krishnaentertainment.nd801.popularmovies.models.Movie;
import com.krishnaentertainment.nd801.popularmovies.utils.UriUtils;

import java.util.ArrayList;

public class MoviesGalleryAdapter extends RecyclerView.Adapter<MoviesGalleryAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Movie> mMoviesList;

    public interface AdapterCallback {
        void onAdapterItemClick(Movie movie, NetworkImageView posterImage, View v);
    }

    public MoviesGalleryAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void set(ArrayList<Movie> movies) {
        mMoviesList = movies;
        notifyDataSetChanged();
    }

    public ArrayList<Movie> getMoviesList() {
        return mMoviesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gallery_movie_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = mMoviesList.get(position);
        holder.posterImage.setImageUrl(
                UriUtils.buildTMDBImageUrl(GlobalConstants.TMDB_LARGE_POSTER, movie.posterPath),
                VolleySingleton.getInstance(mContext).getImageLoader());
    }

    @Override
    public int getItemCount() {
        return mMoviesList != null ? mMoviesList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public NetworkImageView posterImage;

        public ViewHolder(View view) {
            super(view);
            posterImage = (NetworkImageView) view.findViewById(R.id.gallery_poster_item);
        }

        @Override
        public void onClick(View v) {
            Movie movie = mMoviesList.get(getLayoutPosition());
            ((AdapterCallback) mContext).onAdapterItemClick(movie, posterImage, v);
        }
    }
}

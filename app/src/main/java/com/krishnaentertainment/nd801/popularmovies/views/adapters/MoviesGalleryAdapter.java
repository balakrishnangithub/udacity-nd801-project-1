package com.krishnaentertainment.nd801.popularmovies.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.krishnaentertainment.nd801.popularmovies.R;
import com.krishnaentertainment.nd801.popularmovies.managers.VolleySingleton;
import com.krishnaentertainment.nd801.popularmovies.models.Movie;
import com.krishnaentertainment.nd801.popularmovies.utils.UriUtils;

import java.util.ArrayList;

// TODO think about this http://stackoverflow.com/q/19721112/1753174
public class MoviesGalleryAdapter extends RecyclerView.Adapter<MoviesGalleryAdapter.CustomViewHolder> {
    private Context mContext;
    private ArrayList<Movie> mMoviesList;
    private ImageLoader mImageLoader;

    public interface AdapterCallback {
        void onGalleryItemClick(Movie movie, View v);
    }

    public MoviesGalleryAdapter(Context mContext) {
        this.mContext = mContext;
        mImageLoader = VolleySingleton.getInstance(mContext).getImageLoader();
    }

    public void set(ArrayList<Movie> movies) {
        mMoviesList = movies;
        notifyDataSetChanged();
    }

    public ArrayList<Movie> getMoviesList() {
        return mMoviesList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gallery_movie_item, parent, false);

        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Movie movie = mMoviesList.get(position);
        holder.posterImage.setImageUrl(
                UriUtils.buildTMDBImageUrl(mContext.getResources().getString(R.string.tmdb_poster_size_gallery), movie.posterPath),
                mImageLoader);
    }

    @Override
    public int getItemCount() {
        return mMoviesList != null ? mMoviesList.size() : 0;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public NetworkImageView posterImage;

        public CustomViewHolder(View view) {
            super(view);
            posterImage = (NetworkImageView) view.findViewById(R.id.gallery_poster_item);
        }

        @Override
        public void onClick(View v) {
            Movie movie = mMoviesList.get(getLayoutPosition());
            ((AdapterCallback) mContext).onGalleryItemClick(movie, v);
        }
    }
}

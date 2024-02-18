package com.example.tvshow.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.tvshow.database.TVShowsDatabase;
import com.example.tvshow.models.TVShow;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
//This class is for watchlist tvshows VIEWMODEL
public class Watchlist_V_Model extends AndroidViewModel {

    private final TVShowsDatabase tvShowsDatabase;

    public Watchlist_V_Model(@NonNull Application application) {
        super(application);
        tvShowsDatabase = TVShowsDatabase.getTvShowsDatabase(application);

    }

    public Flowable<List<TVShow>> loadWatchlist() {
        return tvShowsDatabase.tvShowDao().getWatchlist();
    }
public Completable removeTVShowFromWatchlist(TVShow tvShow){
    return tvShowsDatabase.tvShowDao().removeFromWatchlist(tvShow);
}

}

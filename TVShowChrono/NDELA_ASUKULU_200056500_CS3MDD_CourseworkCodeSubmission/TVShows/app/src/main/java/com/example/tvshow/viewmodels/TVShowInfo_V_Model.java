package com.example.tvshow.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tvshow.database.TVShowsDatabase;
import com.example.tvshow.models.TVShow;
import com.example.tvshow.repositories.TVShowInfoRepo;
import com.example.tvshow.responses.TVShowInfoResponse;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;

//This class is for tvshows details VIEWMODEL
public class TVShowInfo_V_Model extends AndroidViewModel {
    private final TVShowInfoRepo tvShowDetailsRepo;
    private final TVShowsDatabase tvShowsDatabase;

    public TVShowInfo_V_Model(@NonNull Application application) {
        super(application);
        tvShowDetailsRepo = new TVShowInfoRepo();
        tvShowsDatabase = TVShowsDatabase.getTvShowsDatabase(application);
    }

    public LiveData<TVShowInfoResponse> getTVShowInfo(String tvShowid) {
        return tvShowDetailsRepo.getTVShowInfo(tvShowid);
    }

    public Completable addToWatchlist(TVShow tvShow) {
        return tvShowsDatabase.tvShowDao().addToWatchlist(tvShow);
    }

    public Flowable<TVShow> getTVSHowFromWatchist(String tvShowId){
        return tvShowsDatabase.tvShowDao().getTVShowFromWatchlist(tvShowId);
    }
    public Completable removeTVShowFromWatchlist(TVShow tvShow){
        return tvShowsDatabase.tvShowDao().removeFromWatchlist(tvShow);
    }
}

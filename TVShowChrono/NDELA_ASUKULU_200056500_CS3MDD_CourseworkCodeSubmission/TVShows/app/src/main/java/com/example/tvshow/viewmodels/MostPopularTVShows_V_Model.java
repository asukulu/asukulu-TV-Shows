package com.example.tvshow.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tvshow.responses.TVShowsResponse;
import com.example.tvshow.repositories.MostPopularTVShowsRepo;

//This class is for Most popular tvshows VIEWMODEL
public class MostPopularTVShows_V_Model extends ViewModel {
    private final MostPopularTVShowsRepo mostPopularTVShowsRepository;

    public MostPopularTVShows_V_Model() {

        mostPopularTVShowsRepository = new MostPopularTVShowsRepo();
    }

    public LiveData<TVShowsResponse> getMostPopularTVShows(int page) {
        return mostPopularTVShowsRepository.getMostPopularTVShows(page);
    }
}

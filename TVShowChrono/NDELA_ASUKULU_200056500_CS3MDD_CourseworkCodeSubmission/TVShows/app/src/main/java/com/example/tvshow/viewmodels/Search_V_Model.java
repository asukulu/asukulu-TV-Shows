package com.example.tvshow.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tvshow.repositories.SearchTVShowRepo;
import com.example.tvshow.responses.TVShowsResponse;

//This class is for search tvshows VIEWMODEL
public class Search_V_Model extends ViewModel {
    private final SearchTVShowRepo searchTVShowRepository;

    public Search_V_Model(){
        searchTVShowRepository = new SearchTVShowRepo();
    }
public LiveData<TVShowsResponse> searchTVShow(String query, int page){
        return searchTVShowRepository.searchTVShow(query, page);
}
}

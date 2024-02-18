package com.example.tvshow.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tvshow.responses.TVShowsResponse;
import com.example.tvshow.network.ApiRestClientRetrofit;
import com.example.tvshow.network.ApiRestService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
//This class acts as repository for most popular tvshows
// creating a centralized repository to retrieve movies for the app, both from the TMDB API and from the Room database.
public class MostPopularTVShowsRepo {

    private final ApiRestService apiRestService;

    public MostPopularTVShowsRepo() {
        apiRestService = ApiRestClientRetrofit.getRetrofit().create(ApiRestService.class);
    }

    //Mutable live-data give multiple response, give old response from api and after give new response
    public LiveData<TVShowsResponse> getMostPopularTVShows(int page) {
        MutableLiveData<TVShowsResponse> data = new MutableLiveData<>();
        apiRestService.getMostPopularTVShows(page).enqueue(new Callback<TVShowsResponse>() {
            @Override
            public void onResponse(@NonNull Call<TVShowsResponse> call, @NonNull Response<TVShowsResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<TVShowsResponse> call, @NonNull Throwable t) {
                data.setValue(null);

            }
        });
        return data;
    }
}

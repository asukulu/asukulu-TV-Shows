package com.example.tvshow.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tvshow.network.ApiRestClientRetrofit;
import com.example.tvshow.network.ApiRestService;
import com.example.tvshow.responses.TVShowsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
// creating a centralized repository to retrieve search tvshows both from the TMDB API and from your Room database.
public class SearchTVShowRepo {
    private final ApiRestService apiService;

    public SearchTVShowRepo() {
        apiService = ApiRestClientRetrofit.getRetrofit().create(ApiRestService.class);

    }

    public LiveData<TVShowsResponse> searchTVShow(String query, int page) {

        MutableLiveData<TVShowsResponse> data = new MutableLiveData<>();
        apiService.searchTVShow(query, page).enqueue(new Callback<TVShowsResponse>() {
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

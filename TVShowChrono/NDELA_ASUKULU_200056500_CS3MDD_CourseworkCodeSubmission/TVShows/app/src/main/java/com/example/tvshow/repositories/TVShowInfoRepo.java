package com.example.tvshow.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tvshow.network.ApiRestClientRetrofit;
import com.example.tvshow.network.ApiRestService;
import com.example.tvshow.responses.TVShowInfoResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
//creating a centralized repository to retrieve tvshows details both from the TMDB API and from your Room database.
public class TVShowInfoRepo {

    private final ApiRestService apiService;

    public TVShowInfoRepo() {
        apiService = ApiRestClientRetrofit.getRetrofit().create(ApiRestService.class);

    }

    public LiveData<TVShowInfoResponse> getTVShowInfo(String tvShowId) {
        MutableLiveData<TVShowInfoResponse> data = new MutableLiveData<>();
        apiService.getTvshowDetails(tvShowId).enqueue(new Callback<TVShowInfoResponse>() {
            @Override
            public void onResponse(@NonNull Call<TVShowInfoResponse> call, @NonNull Response<TVShowInfoResponse> response) {
                data.postValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<TVShowInfoResponse> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}

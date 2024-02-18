package com.example.tvshow.network;

import com.example.tvshow.responses.TVShowInfoResponse;
import com.example.tvshow.responses.TVShowsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
//Retrieving data from REstAPI
public interface ApiRestService {

    //Get methods,
    @GET("most-popular")
    Call<TVShowsResponse> getMostPopularTVShows(@Query("page") int page);

    //Show TVShow
    @GET("show-details")
    Call<TVShowInfoResponse> getTvshowDetails(@Query("q") String tvShowid);

    //Search from TVShow
    @GET("search")
    Call<TVShowsResponse> searchTVShow(@Query("q") String query, @Query("page") int page);
}

package com.example.tvshow.responses;

import com.example.tvshow.models.TVShowInfoModel;
import com.google.gson.annotations.SerializedName;

//This class is for single TVshow request
public class TVShowInfoResponse {
//Finding the TVshowinfo Object
@SerializedName("tvShow")

    private final TVShowInfoModel tvShowInfo;

    public TVShowInfoResponse(TVShowInfoModel tvShowDetails) {
        this.tvShowInfo = tvShowDetails;
    }

    public TVShowInfoModel getTvShowInfo(){
    return tvShowInfo;
}

}

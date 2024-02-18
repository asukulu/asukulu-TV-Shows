package com.example.tvshow.models;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

//Model class for TVshow Info
public class TVShowInfoModel implements Serializable {


    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("runtime")
    @Expose
    private int runtime;

    @SerializedName("image_path")
    @Expose
    private String imagePath;

    @SerializedName("rating")
    @Expose
    private String rating;


    private String[] genres;
    @SerializedName("pictures")
    @Expose

    private String[] pictures;


    private List<EpisodeModel> episodes;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public String[] getPictures() {
        return pictures;
    }

    public void setPictures(String[] pictures) {
        this.pictures = pictures;
    }

    public List<EpisodeModel> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<EpisodeModel> episodes) {
        this.episodes = episodes;
    }


    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(TVShow.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');

        sb.append(((this.url == null)?"<null>":this.url));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');

        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        sb.append("runtime");
        sb.append('=');
        sb.append(this.runtime);
        sb.append(',');

        sb.append("imagePath");
        sb.append('=');
        sb.append(((this.imagePath == null)?"<null>":this.imagePath));
        sb.append(',');

        sb.append("rating");
        sb.append('=');
        sb.append(((this.rating == null)?"<null>":this.rating));
        sb.append(',');

        sb.append("genres");
        sb.append('=');
        sb.append(((this.genres == null)?"<null>": Arrays.toString(this.genres)));
        sb.append(',');
        sb.append("pictures");
        sb.append('=');
        sb.append(((this.pictures == null)?"<null>": Arrays.toString(this.pictures)));
        sb.append(',');
        sb.append("episodes");
        sb.append('=');
        sb.append(((this.episodes == null)?"<null>":this.episodes));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}


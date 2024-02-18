
package com.example.tvshow.responses;

import androidx.annotation.NonNull;

import com.example.tvshow.models.TVShow;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


//This class is for getting multiple TVshows - most popular tvshows
public class TVShowsResponse implements Serializable {
//Finding the TVshow Object

    @SerializedName("page")
    @Expose
    private int page;

    @SerializedName("pages")
    @Expose
    private int totalPages;

    @SerializedName("tv_shows")
    @Expose
    private List<TVShow> tvshows;


    //Getters and Setters
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<TVShow> getTvshows() {
        return tvshows;
    }

    public void setTvshows(List<TVShow> tvshows) {
        this.tvshows = tvshows;
    }

//toString method
    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(TVShow.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("total");
        sb.append('=');

        sb.append("page");
        sb.append('=');
        sb.append(this.page);
        sb.append(',');
        sb.append("pages");
        sb.append('=');
        sb.append(this.totalPages);
        sb.append(',');
        sb.append("tvShows");
        sb.append('=');
        sb.append(((this.tvshows == null) ? "<null>" : this.tvshows));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}



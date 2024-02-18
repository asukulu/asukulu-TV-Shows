
package com.example.tvshow.models;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

//Model class for tvshow episodes
public class EpisodeModel implements Serializable {

//The @SerializedName and @Expose annotations are from the Gson library, not from Room. The app uses Gson
// to convert JSON data from API web calls to Kotlin data objects
    @SerializedName("season")
    @Expose
    private String season;
    @SerializedName("episode")
    @Expose
    private String episode;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("air_date")
    @Expose
    private String airDate;
    // private final static long serialVersionUID = -409260526218357007L;

    /**
     * No args constructor for use in serialization
     */
    public EpisodeModel() {
    }

    /**
     * @param name
     * @param airDate
     * @param season
     * @param episode
     */
    public EpisodeModel(String season, String episode, String name, String airDate) {
        super();
        this.season = season;
        this.episode = episode;
        this.name = name;
        this.airDate = airDate;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public EpisodeModel withSeason(String season) {
        this.season = season;
        return this;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public EpisodeModel withEpisode(String episode) {
        this.episode = episode;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EpisodeModel withName(String name) {
        this.name = name;
        return this;
    }

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public EpisodeModel withAirDate(String airDate) {
        this.airDate = airDate;
        return this;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(EpisodeModel.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("season");
        sb.append('=');
        sb.append(this.season);
        sb.append(',');
        sb.append("episode");
        sb.append('=');
        sb.append(this.episode);
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null) ? "<null>" : this.name));
        sb.append(',');
        sb.append("airDate");
        sb.append('=');
        sb.append(((this.airDate == null) ? "<null>" : this.airDate));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}

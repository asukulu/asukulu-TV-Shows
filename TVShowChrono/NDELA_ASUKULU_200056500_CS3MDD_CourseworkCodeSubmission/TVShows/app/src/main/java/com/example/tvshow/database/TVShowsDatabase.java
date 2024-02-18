package com.example.tvshow.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.tvshow.dao.TVShowDao;
import com.example.tvshow.models.TVShow;

//this creates a table with the same name as the class. You can also give
// the table a different name by explicitly setting the tableName
@Database(entities = {TVShow.class}, version = 1, exportSchema = false)
public abstract class TVShowsDatabase extends RoomDatabase{

    private static TVShowsDatabase tvShowsDatabase;

    public static synchronized TVShowsDatabase getTvShowsDatabase(Context context){

        if (tvShowsDatabase == null) {
            tvShowsDatabase = Room.databaseBuilder(
                    context,
                    TVShowsDatabase.class,
                    "tv_shows_db"
            ).build();

        }
        return tvShowsDatabase;
    }

    public abstract TVShowDao tvShowDao();
}

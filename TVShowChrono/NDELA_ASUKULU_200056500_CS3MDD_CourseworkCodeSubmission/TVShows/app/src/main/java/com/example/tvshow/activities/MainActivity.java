package com.example.tvshow.activities;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvshow.R;
import com.example.tvshow.adapter.TVShowsAdapter;
import com.example.tvshow.databinding.ActivityMainBinding;
import com.example.tvshow.listeners.TVShowsListener;
import com.example.tvshow.models.TVShow;
import com.example.tvshow.viewmodels.MostPopularTVShows_V_Model;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements TVShowsListener {

    private ActivityMainBinding activityMainBinding;
    private MostPopularTVShows_V_Model viewModel;
    private final List<TVShow> tvShows = new ArrayList<>();
    private TVShowsAdapter tvShowsAdapter;
    private int currentPage = 1;
    private int totalAvailablePages = 1;
    BottomNavigationView nav;
    Menu menu;


    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        // setContentView(R.layout.activity_main);
        // viewModel_M = new ViewModelProvider(this).get(Movies_V_Model.class);


        nav = findViewById(R.id.navigation_view);
        // menu=(Menu) findViewById(R.id.home_page);
        Timer _timer = new Timer();


        _Animator(nav,"translationX",10,0);
        _Animator((View) menu,"alpha",0,0);




        TimerTask timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        };
        _timer.schedule(timer, 600);

        //timer for start linear
        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(nav,"translationX",-100,400);
                        _Animator(nav,"alpha",1,400);

                    }
                });
            }
        };
        _timer.schedule(timer, 1800);
        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        _Animator(nav,"translationX",1,400);
                        _Animator(nav,"alpha",1,200);
                    }
                });
            }
        };
        _timer.schedule(timer, 2200);




        doInitialization();

        nav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {

                case R.id.home_page:
                    // Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_LONG).show();

                    startActivity(new Intent(getApplicationContext()
                            , MainActivity.class));
                    break;

                case R.id.watchlist:
                    // Toast.makeText(MainActivity.this, "Watchlist", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext()
                            , WatchlistActivity.class));

                    break;

                case R.id.Search:
                    //Toast.makeText(MainActivity.this, "Movies", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext()
                            , SearchActivity.class));

                    break;

                default:
            }
            return true;
        });

    }


    private void doInitialization() {
        activityMainBinding.tvShowsRecyclerView.setHasFixedSize(true);
        viewModel = new ViewModelProvider(this).get(MostPopularTVShows_V_Model.class);
        tvShowsAdapter = new TVShowsAdapter(tvShows, this);
        activityMainBinding.tvShowsRecyclerView.setAdapter(tvShowsAdapter);
        activityMainBinding.tvShowsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!activityMainBinding.tvShowsRecyclerView.canScrollVertically(1)) {
                    if (currentPage <= totalAvailablePages) {
                        currentPage += 1;
                        getMostPopularTVShows();
                    }
                }
            }
        });
        activityMainBinding.imageWatchlist.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), WatchlistActivity.class)));
        activityMainBinding.imageSearch.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), SearchActivity.class)));
        getMostPopularTVShows();
    }

    private void getMostPopularTVShows() {
        toggleLoading();
        // activityMainBinding.setIsLoading(true);
        viewModel.getMostPopularTVShows(currentPage).observe(this, mostPopularTVShowsResponse -> {
            toggleLoading();
            //activityMainBinding.setIsLoading(false);
            if (mostPopularTVShowsResponse != null) {
                totalAvailablePages = mostPopularTVShowsResponse.getTotalPages();
                if (mostPopularTVShowsResponse.getTvshows() != null) {
                    int oldCount = tvShows.size();
                    tvShows.addAll(mostPopularTVShowsResponse.getTvshows());
                    tvShowsAdapter.notifyItemRangeInserted(oldCount, tvShows.size());
                }
            }
        });
    }

    //This code is used to display a loading indicator in the UI when data is being fetched
    // from a server or some other source. When the data has been loaded, the loading state is toggled off and the data is displayed in the UI.
    private void toggleLoading() {
        if (currentPage == 1) {
            activityMainBinding.setIsLoading(activityMainBinding.getIsLoading() == null || !activityMainBinding.getIsLoading());
        } else {
            if (activityMainBinding.getIsLoadingMore() != null && activityMainBinding.getIsLoadingMore()) {
                activityMainBinding.getIsLoadingMore();
                //activityMainBinding.getIsLoadingMore(false);
            } else {
                activityMainBinding.getIsLoadingMore();

            }
        }
    }

    @Override
    public void onTVShowClicked(TVShow tvShow) {
        Intent intent = new Intent(getApplicationContext(), TVShowDetailsActivity.class);
        intent.putExtra("tvShow", tvShow);
        startActivity(intent);

    }




    public void _Animator (final View _view, final String _propertyName, final double _value, final double _duration) {
        ObjectAnimator anim = new ObjectAnimator();
        anim.setTarget(_view);
        anim.setPropertyName(_propertyName);
        anim.setFloatValues((float)_value);
        anim.setDuration((long)_duration);
        anim.setInterpolator(new android.view.animation.AccelerateDecelerateInterpolator());
        anim.start();
    }
}

package com.example.tvshow.activities;

import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.tvshow.R;
import com.example.tvshow.adapter.WatchlistAdapter;
import com.example.tvshow.databinding.ActivityWatchlistBinding;
import com.example.tvshow.listeners.WatchlistListener;
import com.example.tvshow.models.TVShow;
import com.example.tvshow.utilities.TempDataHolder;
import com.example.tvshow.viewmodels.Watchlist_V_Model;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class WatchlistActivity extends AppCompatActivity implements WatchlistListener {
    private ActivityWatchlistBinding activityWatchlistBinding;
    private Watchlist_V_Model viewModel;
    private WatchlistAdapter watchlistAdapter;
    private List<TVShow> watchlist;
    BottomNavigationView nav;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityWatchlistBinding = DataBindingUtil.setContentView(this, R.layout.activity_watchlist);

        nav = findViewById(R.id.navigation_view);
        // menu=(Menu) findViewById(R.id.home_page);
        Timer _timer = new Timer();


       // _Animator((View) menu,"translationX",400,0);
        // _Animator((View) menu,"alpha",0,0);




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

                        _Animator(nav,"translationX",0,400);
                        _Animator(nav,"alpha",1,400);
                    }
                });
            }
        };
        _timer.schedule(timer, 2200);




        doInitialization();

        nav = findViewById(R.id.navigation_view);

        nav.setOnItemReselectedListener(item -> {
            switch (item.getItemId()) {

                case R.id.home_page:
                    //Toast.makeText(WatchlistActivity.this, "Home", Toast.LENGTH_LONG).show();

                    startActivity(new Intent(getApplicationContext()
                            , MainActivity.class));
                    break;

                case R.id.watchlist:
                    //Toast.makeText(WatchlistActivity.this, "Watchlist", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext()
                            , WatchlistActivity.class));

                    break;

                case R.id.Search:
                   // Toast.makeText(WatchlistActivity.this, "Movies", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext()
                            , SearchActivity.class));

                    break;

                default:
            }
        });

    }





    private void doInitialization() {
        viewModel = new ViewModelProvider(this).get(Watchlist_V_Model.class);
        activityWatchlistBinding.imageBack.setOnClickListener(v -> onBackPressed());
        watchlist = new ArrayList<>();
       // doInitialization();
        loadwatchlist();
    }

    private void loadwatchlist() {
        activityWatchlistBinding.setIsLoading(true);
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(viewModel.loadWatchlist().subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tvShows -> {
                    activityWatchlistBinding.setIsLoading(false);
                    if (watchlist.size() > 0) {
                        watchlist.clear();
                    }
                    //Toast.makeText(getApplicationContext(), "watchlist:" + tvShows.size(), Toast.LENGTH_SHORT).show();

                    watchlist.addAll(tvShows);
                    watchlistAdapter = new WatchlistAdapter(watchlist, this);
                    activityWatchlistBinding.watchlistRecycleView.setAdapter(watchlistAdapter);
                    activityWatchlistBinding.watchlistRecycleView.setVisibility(View.VISIBLE);
                    compositeDisposable.dispose();
                }));


    }


    @Override
    protected void onResume() {
        super.onResume();
        if(TempDataHolder.IS_WATCHLIST_UPDATED){
            loadwatchlist();
            TempDataHolder.IS_WATCHLIST_UPDATED = false;
        }

    }

    @Override
    public void onTVShowClicked(TVShow tvShow) {
        Intent intent = new Intent(getApplicationContext(), TVShowDetailsActivity.class);
        intent.putExtra("tvShow", tvShow);
        startActivity(intent);

    }
//This method is to remove the tvshow from the watchlist
    @Override
    public void removeTVShowFromWatchlist(TVShow tvShow, int position) {



        CompositeDisposable compositeDisposableForDelete = new CompositeDisposable();


        compositeDisposableForDelete.add(viewModel.removeTVShowFromWatchlist(tvShow)


               .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {

                    AlertDialog.Builder builder = new AlertDialog.Builder(this)
                            .setTitle("Remove from Watchlist")
                            .setMessage("Do you want to delete this TV show?")
                            .setNegativeButton("No,Cancel", new DialogInterface.OnClickListener() {
                                @Override public void onClick(DialogInterface dialogInterface,
                                                              int i) {
// Call back when "Cancel" button is clicked
                                }
                            })
                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    watchlist.remove(position);
                                    watchlistAdapter.notifyItemRemoved(position);

                                }

                            });
                    builder.show();


                    watchlistAdapter.notifyItemChanged(position, watchlistAdapter.getItemCount());
                    compositeDisposableForDelete.dispose();



                            }));

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



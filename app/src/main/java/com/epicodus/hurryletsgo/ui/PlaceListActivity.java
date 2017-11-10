package com.epicodus.hurryletsgo.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.epicodus.hurryletsgo.Constants;
import com.epicodus.hurryletsgo.R;
import com.epicodus.hurryletsgo.adapters.PlaceListAdapter;
import com.epicodus.hurryletsgo.models.Place;
import com.epicodus.hurryletsgo.services.APIService;
import com.epicodus.hurryletsgo.util.OnPlaceSelectedListener;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class PlaceListActivity extends AppCompatActivity implements OnPlaceSelectedListener {
    private Integer mPosition;
    ArrayList<Place> mPlaces;
    String mSource;

//    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private PlaceListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);
//        ButterKnife.bind(this);

        if (savedInstanceState != null) {

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                mPosition = savedInstanceState.getInt(Constants.EXTRA_KEY_POSITION);
                mPlaces = Parcels.unwrap(savedInstanceState.getParcelable(Constants.EXTRA_KEY_PLACE));
                mSource = savedInstanceState.getString(Constants.KEY_SOURCE);

                if (mPosition != null && mPlaces != null) {
                    Intent intent = new Intent(this, PlaceDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_KEY_POSITION, mPosition);
                    intent.putExtra(Constants.EXTRA_KEY_PLACE, Parcels.wrap(mPlaces));
                    intent.putExtra(Constants.KEY_SOURCE, mSource);
                    startActivity(intent);
                }

            }

        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mPosition != null && mPlaces != null) {
            outState.putInt(Constants.EXTRA_KEY_POSITION, mPosition);
            outState.putParcelable(Constants.EXTRA_KEY_PLACE, Parcels.wrap(mPlaces));
            outState.putString(Constants.KEY_SOURCE, mSource);
        }

    }

    @Override
    public void onPlaceSelected(Integer position, ArrayList<Place> places, String source) {
        mPosition = position;
        mPlaces = places;
        mSource = source;
    }


}

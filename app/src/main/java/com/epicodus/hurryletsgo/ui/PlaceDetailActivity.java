package com.epicodus.hurryletsgo.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.epicodus.hurryletsgo.Constants;
import com.epicodus.hurryletsgo.R;
import com.epicodus.hurryletsgo.adapters.PlacePagerAdapter;
import com.epicodus.hurryletsgo.models.Place;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PlaceDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;

    private PlacePagerAdapter adapterViewPager;
    ArrayList<Place> mPlaces = new ArrayList<>();
    private String mSource;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);
        ButterKnife.bind(this);

        mPlaces = Parcels.unwrap(getIntent().getParcelableExtra(Constants.EXTRA_KEY_PLACE));
        int startingPosition = getIntent().getIntExtra(Constants.EXTRA_KEY_POSITION, 0);
        mSource = getIntent().getStringExtra(Constants.KEY_SOURCE);

        adapterViewPager = new PlacePagerAdapter(getSupportFragmentManager(), mPlaces, mSource);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}

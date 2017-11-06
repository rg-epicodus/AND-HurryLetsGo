package com.epicodus.hurryletsgo.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);
        ButterKnife.bind(this);

        mPlaces = Parcels.unwrap(getIntent().getParcelableExtra("places"));

        int startingPosition = Integer.parseInt(getIntent().getStringExtra("position"));

        adapterViewPager = new PlacePagerAdapter(getSupportFragmentManager(), mPlaces);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}

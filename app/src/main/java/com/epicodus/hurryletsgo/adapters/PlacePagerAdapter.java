package com.epicodus.hurryletsgo.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.hurryletsgo.models.Place;
import com.epicodus.hurryletsgo.ui.PlaceDetailFragment;

import java.util.ArrayList;

/**
 * Created by Guest on 11/6/17.
 */

public class PlacePagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Place> mPlaces;
    private String mSource;

    public PlacePagerAdapter(FragmentManager fm, ArrayList<Place> places, String source) {
        super(fm);
        mPlaces = places;
        mSource = source;
    }

    @Override
    public Fragment getItem(int position) {
        return PlaceDetailFragment.newInstance(mPlaces, position, mSource);
    }

    @Override
    public int getCount() {
        return mPlaces.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPlaces.get(position).getName();
    }
}

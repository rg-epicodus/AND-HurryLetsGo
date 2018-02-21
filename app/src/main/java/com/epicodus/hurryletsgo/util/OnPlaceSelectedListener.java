package com.epicodus.hurryletsgo.util;

import com.epicodus.hurryletsgo.models.Place;

import java.util.ArrayList;

/**
 * Created by Guest on 11/9/17.
 */

public interface OnPlaceSelectedListener {
    public void onPlaceSelected(Integer position, ArrayList<Place> places);
}

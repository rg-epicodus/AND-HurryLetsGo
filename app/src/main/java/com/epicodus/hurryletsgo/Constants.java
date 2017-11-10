package com.epicodus.hurryletsgo;

/**
 * Created by Guest on 11/6/17.
 */

public class Constants {
    public static final String YELP_TOKEN = BuildConfig.YELP_TOKEN;
    public static final String YELP_FOOD_URL = "https://api.yelp.com/v3/businesses/search?categories=restaurants";
    public static final String YELP_DRINK_URL = "https://api.yelp.com/v3/businesses/search?categories=nightlife";
    public static final String YELP_ENTERTAINMENT_URL = "https://api.yelp.com/v3/businesses/search?categories=active,cannabis,danceclubs,arts";

    public static final String YELP_LOCATION_QUERY_PARAMETER = "location";
    public static final String PREFERENCES_LOCATION_KEY = "location";
    public static final String FIREBASE_CHILD_PLACE = "places";
    public static final String FIREBASE_QUERY_INDEX = "index";
    public static final String EXTRA_KEY_POSITION = "position";
    public static final String EXTRA_KEY_PLACE = "places";
    public static final String KEY_SOURCE = "source";
    public static final String SOURCE_SAVED = "saved";
    public static final String SOURCE_FIND = "find";
}

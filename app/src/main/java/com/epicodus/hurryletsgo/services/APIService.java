package com.epicodus.hurryletsgo.services;

import com.epicodus.hurryletsgo.Constants;
import com.epicodus.hurryletsgo.models.Place;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Guest on 11/6/17.
 */

public class APIService {

    public static void findFood(String location, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.YELP_FOOD_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.YELP_LOCATION_QUERY_PARAMETER, location);
        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .url(url)
                .header("Authorization", Constants.YELP_TOKEN)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }

    public static void findDrink(String location, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.YELP_DRINK_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.YELP_LOCATION_QUERY_PARAMETER, location);
        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .url(url)
                .header("Authorization", Constants.YELP_TOKEN)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }

    public static void findEntertainment(String location, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.YELP_ENTERTAINMENT_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.YELP_LOCATION_QUERY_PARAMETER, location);
        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .url(url)
                .header("Authorization", Constants.YELP_TOKEN)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }


    public ArrayList<Place> processResults(Response response) {
        ArrayList<Place> places = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            JSONObject yelpJSON = new JSONObject(jsonData);
            JSONArray businessesJSON = yelpJSON.getJSONArray("businesses");
            for (int i = 0; i < businessesJSON.length(); i++) {
                JSONObject placeJSON = businessesJSON.getJSONObject(i);
                String name = placeJSON.getString("name");
                String phone = placeJSON.optString("display_phone", "Phone not available");
                String website = placeJSON.getString("url");
                double rating = placeJSON.getDouble("rating");
                String imageUrl = placeJSON.getString("image_url");
                double latitude = (double) placeJSON.getJSONObject("coordinates").getDouble("latitude");
                double longitude = (double) placeJSON.getJSONObject("coordinates").getDouble("longitude");
                ArrayList<String> address = new ArrayList<>();
                JSONArray addressJSON = placeJSON.getJSONObject("location")
                        .getJSONArray("display_address");
                for (int y = 0; y < addressJSON.length(); y++) {
                    address.add(addressJSON.get(y).toString());
                }
                ArrayList<String> categories = new ArrayList<>();
                JSONArray categoriesJSON = placeJSON.getJSONArray("categories");
                for (int y = 0; y < categoriesJSON.length(); y++) {
                    categories.add(categoriesJSON.getJSONObject(y).getString("title"));
                }
                Place place = new Place(name, phone, website, rating,
                        imageUrl, address, latitude, longitude, categories);
                places.add(place);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return places;
    }
}

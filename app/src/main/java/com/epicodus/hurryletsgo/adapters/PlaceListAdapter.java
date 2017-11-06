package com.epicodus.hurryletsgo.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.hurryletsgo.R;
import com.epicodus.hurryletsgo.models.Place;
import com.epicodus.hurryletsgo.ui.PlaceDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 11/6/17.
 */

public class PlaceListAdapter extends RecyclerView.Adapter<PlaceListAdapter.PlaceViewHolder> {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    private ArrayList<Place> mPlaces = new ArrayList<>();
    private Context mContext;

    public PlaceListAdapter(Context context, ArrayList<Place> places) {
        mContext = context;
        mPlaces = places;
    }

    @Override
    public PlaceListAdapter.PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_list_item, parent, false);
        PlaceViewHolder viewHolder = new PlaceViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PlaceListAdapter.PlaceViewHolder holder, int position) {
        holder.bindPlace(mPlaces.get(position));
    }

    @Override
    public int getItemCount() {
        return mPlaces.size();
    }


    public class PlaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.placeImageView) ImageView mPlaceImageView;
        @Bind(R.id.placeNameTextView) TextView mNameTextView;
        @Bind(R.id.categoryTextView) TextView mCategoryTextView;
        @Bind(R.id.ratingTextView) TextView mRatingTextView;

        private Context mContext;

        public PlaceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindPlace(Place place) {

            Picasso.with(mContext)
                    .load(place.getImageUrl())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mPlaceImageView);

            mNameTextView.setText(place.getName());
            mCategoryTextView.setText(place.getCategories().get(0));
            mRatingTextView.setText("Rating: " + place.getRating() + "/5");
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();

            Intent intent = new Intent(mContext, PlaceDetailActivity.class);
            intent.putExtra("position", itemPosition + "");
            intent.putExtra("places", Parcels.wrap(mPlaces));

            mContext.startActivity(intent);
        }
    }
}

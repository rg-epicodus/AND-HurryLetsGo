package com.epicodus.hurryletsgo.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.hurryletsgo.Constants;
import com.epicodus.hurryletsgo.R;
import com.epicodus.hurryletsgo.models.Place;
import com.epicodus.hurryletsgo.ui.PlaceDetailActivity;
import com.epicodus.hurryletsgo.ui.PlaceDetailFragment;
import com.epicodus.hurryletsgo.util.OnPlaceSelectedListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PlaceListAdapter extends RecyclerView.Adapter<PlaceListAdapter.PlaceViewHolder> {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    private ArrayList<Place> mPlaces = new ArrayList<>();
    private Context mContext;
    private OnPlaceSelectedListener mOnPlaceSelectedListener;

    public PlaceListAdapter(Context context, ArrayList<Place> places, OnPlaceSelectedListener placeSelectedListener) {
        mContext = context;
        mPlaces = places;
        mOnPlaceSelectedListener = placeSelectedListener;
    }

    @Override
    public PlaceListAdapter.PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_list_item, parent, false);
        PlaceViewHolder viewHolder = new PlaceViewHolder(view, mPlaces, mOnPlaceSelectedListener);
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
        private int mOrientation;
        private ArrayList<Place> mPlaces = new ArrayList<>();
        private OnPlaceSelectedListener mPlaceSelectedListener;

        public PlaceViewHolder(View itemView, ArrayList<Place> places, OnPlaceSelectedListener placeSelectedListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mContext = itemView.getContext();
            mOrientation = itemView.getResources().getConfiguration().orientation;
            mPlaces = places;
            mPlaceSelectedListener = placeSelectedListener;

            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE){
                createDetailFragment(0);
            }

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

        private void createDetailFragment(int position) {
            PlaceDetailFragment detailFragment = PlaceDetailFragment.newInstance(mPlaces, position, Constants.SOURCE_FIND);
            FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.placeDetailContainer, detailFragment);
            ft.commit();
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();

            mPlaceSelectedListener.onPlaceSelected(itemPosition, mPlaces);

            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(itemPosition);
            } else {
                Intent intent = new Intent(mContext, PlaceDetailActivity.class);
                intent.putExtra(Constants.EXTRA_KEY_POSITION, itemPosition);
                intent.putExtra(Constants.EXTRA_KEY_PLACE, Parcels.wrap(mPlaces));
//                intent.putExtra(Constants.KEY_SOURCE, Constants.SOURCE_FIND);
                mContext.startActivity(intent);
        }
    }

    }

}

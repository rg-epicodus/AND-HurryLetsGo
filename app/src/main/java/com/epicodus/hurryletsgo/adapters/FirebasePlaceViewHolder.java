package com.epicodus.hurryletsgo.adapters;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.hurryletsgo.R;
import com.epicodus.hurryletsgo.models.Place;
import com.epicodus.hurryletsgo.util.ItemTouchHelperViewHolder;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import static com.epicodus.hurryletsgo.R.id.categoryTextView;
import static com.epicodus.hurryletsgo.R.id.placeImageView;
import static com.epicodus.hurryletsgo.R.id.ratingTextView;

/**
 * Created by Guest on 11/9/17.
 */

public class FirebasePlaceViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;
    public ImageView mPlaceImageView;


    public FirebasePlaceViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindPlace(Place place) {
        mPlaceImageView = (ImageView) mView.findViewById(placeImageView);
        TextView mNameTextView = (TextView) mView.findViewById(R.id.placeNameTextView);
        TextView mCategoryTextView = (TextView) mView.findViewById(categoryTextView);
        TextView mRatingTextView = (TextView) mView.findViewById(ratingTextView);

        if (!place.getImageUrl().contains("http")) {
            try {
                Bitmap imageBitmap = decodeFromFirebaseBase64(place.getImageUrl());
                mPlaceImageView.setImageBitmap(imageBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Picasso.with(mContext)
                    .load(place.getImageUrl())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mPlaceImageView);
        }

        mNameTextView.setText(place.getName());
        mCategoryTextView.setText(place.getCategories().get(0));
        mRatingTextView.setText("Rating: " + place.getRating() + "/5");
    }

    public static Bitmap decodeFromFirebaseBase64(String image) throws IOException {
        byte[] decodedByteArray = android.util.Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
    }

    @Override
    public void onItemSelected() {
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,
                R.animator.drag_scale_on);
        set.setTarget(itemView);
        set.start();
    }

    @Override
    public void onItemClear() {
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,
                R.animator.drag_scale_off);
        set.setTarget(itemView);
        set.start();
    }



}

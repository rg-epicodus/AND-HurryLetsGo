package com.epicodus.hurryletsgo.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.hurryletsgo.Constants;
import com.epicodus.hurryletsgo.R;
import com.epicodus.hurryletsgo.models.Place;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaceDetailFragment extends Fragment implements View.OnClickListener{
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;

    @Bind(R.id.placeImageView) ImageView mImageLabel;
    @Bind(R.id.placeNameTextView) TextView mNameLabel;
    @Bind(R.id.cuisineTextView) TextView mCategoriesLabel;
    @Bind(R.id.ratingTextView) TextView mRatingLabel;
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.phoneTextView) TextView mPhoneLabel;
    @Bind(R.id.addressTextView) TextView mAddressLabel;
    @Bind(R.id.savePlaceButton) TextView mSavePlaceButton;

    private Place mPlace;

    public static PlaceDetailFragment newInstance(Place place) {
        PlaceDetailFragment placeDetailFragment = new PlaceDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("place", Parcels.wrap(place));
        placeDetailFragment.setArguments(args);
        return placeDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPlace = Parcels.unwrap(getArguments().getParcelable("place"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_place_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext())
                .load(mPlace.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mImageLabel);

        mNameLabel.setText(mPlace.getName());
        mCategoriesLabel.setText(android.text.TextUtils.join(", ", mPlace.getCategories()));
        mRatingLabel.setText(Double.toString(mPlace.getRating()) + "/5");
        mPhoneLabel.setText(mPlace.getPhone());
        mAddressLabel.setText(android.text.TextUtils.join(", ", mPlace.getAddress()));

        mWebsiteLabel.setOnClickListener(this);
        mPhoneLabel.setOnClickListener(this);
        mAddressLabel.setOnClickListener(this);

        mSavePlaceButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mWebsiteLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mPlace.getWebsite()));
            startActivity(webIntent);
        }
        if (v == mPhoneLabel) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + mPlace.getPhone()));
            startActivity(phoneIntent);
        }
        if (v == mAddressLabel) {
            Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("geo:" + mPlace.getLatitude()
                            + "," + mPlace.getLongitude()
                            + "?q=(" + mPlace.getName() + ")"));
            startActivity(mapIntent);
        }
        if (v == mSavePlaceButton) {
            DatabaseReference placeRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_PLACE);
            placeRef.push().setValue(mPlace);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }


}

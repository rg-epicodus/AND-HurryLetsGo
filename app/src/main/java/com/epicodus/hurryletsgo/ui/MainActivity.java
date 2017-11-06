package com.epicodus.hurryletsgo.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.epicodus.hurryletsgo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.findDrinksButton) Button mFindDrinksButton;
    @Bind(R.id.findFoodButton) Button mFindFoodButton;
    @Bind(R.id.findEntertainmentButton) Button mFindEntertainmentButton;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;
    @Bind(R.id.savedPlacesButton) Button mSavedPlacesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Typeface ostrichFont = Typeface.createFromAsset(getAssets(), "fonts/ostrich-regular.ttf");
        mAppNameTextView.setTypeface(ostrichFont);
        mFindDrinksButton.setOnClickListener(this);
        mFindFoodButton.setOnClickListener(this);
        mFindEntertainmentButton.setOnClickListener(this);
        mSavedPlacesButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v == mFindDrinksButton) {
            Intent intent = new Intent(MainActivity.this, FindDrinksActivity.class);
            startActivity(intent);
        }

        if(v == mFindFoodButton) {
            Intent intent = new Intent(MainActivity.this, FindFoodActivity.class);
            startActivity(intent);
        }

        if(v == mFindEntertainmentButton) {
            Intent intent = new Intent(MainActivity.this, FindEntertainmentActivity.class);
            startActivity(intent);
        }

        if (v == mSavedPlacesButton) {
            Intent intent = new Intent(MainActivity.this, SavedPlaceListActivity.class);
            startActivity(intent);
        }

    }
}

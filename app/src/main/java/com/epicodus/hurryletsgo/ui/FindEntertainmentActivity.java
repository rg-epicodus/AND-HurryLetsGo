package com.epicodus.hurryletsgo.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.epicodus.hurryletsgo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FindEntertainmentActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.submitButton) Button mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_drinks);
        ButterKnife.bind(this);
        mSubmitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v == mSubmitButton) {
            Intent intent = new Intent(FindEntertainmentActivity.this, PlaceListActivity.class);
            startActivity(intent);
        }

    }

}

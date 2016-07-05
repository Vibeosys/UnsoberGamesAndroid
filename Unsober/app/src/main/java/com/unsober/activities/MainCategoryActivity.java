package com.unsober.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.unsober.R;

public class MainCategoryActivity extends AppCompatActivity {

    LinearLayout layoutGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_category);
        ActionBar actionBar = getSupportActionBar();
        layoutGame = (LinearLayout) findViewById(R.id.layoutGame);
        layoutGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SubCategoryActivity.class));
            }
        });
    }
}

package com.unsober.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.unsober.R;

public class MainCategoryActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout mLayoutGame, mLayoutCocktails, mLayoutCures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_category);
        ActionBar actionBar = getSupportActionBar();
        mLayoutGame = (LinearLayout) findViewById(R.id.layoutGame);
        mLayoutCocktails = (LinearLayout) findViewById(R.id.layoutCocktails);
        mLayoutCures = (LinearLayout) findViewById(R.id.layoutCures);

        mLayoutGame.setOnClickListener(this);
        mLayoutCocktails.setOnClickListener(this);
        mLayoutCures.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_details_search, menu);
        return true;

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.layoutGame:
                Intent gameIntent = new Intent(getApplicationContext(), SubCategoryActivity.class);
                gameIntent.putExtra("TabId", R.id.gameLay);
                startActivity(gameIntent);
                finish();
                break;
            case R.id.layoutCocktails:
                Intent cocktailIntent = new Intent(getApplicationContext(), SubCategoryActivity.class);
                cocktailIntent.putExtra("TabId", R.id.cocktailLay);
                startActivity(cocktailIntent);
                finish();
                break;
            case R.id.layoutCures:
                Intent curesIntent = new Intent(getApplicationContext(), SubCategoryActivity.class);
                curesIntent.putExtra("TabId", R.id.curesLay);
                startActivity(curesIntent);
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

package com.unsober.activities;

import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.unsober.R;
import com.unsober.fragments.CocktailsFragment;
import com.unsober.fragments.CuresFragment;
import com.unsober.fragments.GamesFragment;
import com.unsober.fragments.SearchFragment;

public class SubCategoryActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout mGameLay, mCocktailsLay, mCuresLay, mSearchLay, mParentLay;
    private TextView mTxtGames, mTxtCocktails, mTxtCures, mTxtSearch;
    private int selectedId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        try {
            selectedId = getIntent().getExtras().getInt("TabId");
        } catch (NumberFormatException e) {

        } catch (Exception e) {
        }

        mParentLay = (LinearLayout) findViewById(R.id.parentLay);
        mGameLay = (LinearLayout) findViewById(R.id.gameLay);
        mCocktailsLay = (LinearLayout) findViewById(R.id.cocktailLay);
        mCuresLay = (LinearLayout) findViewById(R.id.curesLay);
        mSearchLay = (LinearLayout) findViewById(R.id.searchLay);
        mTxtGames = (TextView) findViewById(R.id.txtGame);
        mTxtCocktails = (TextView) findViewById(R.id.txtCocktails);
        mTxtCures = (TextView) findViewById(R.id.txtCures);
        mTxtSearch = (TextView) findViewById(R.id.txtSearch);
        setUpFragment(selectedId);
        mGameLay.setOnClickListener(this);
        mCocktailsLay.setOnClickListener(this);
        mCuresLay.setOnClickListener(this);
        mSearchLay.setOnClickListener(this);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.keyboardHidden == Configuration.KEYBOARDHIDDEN_NO) {
            Toast.makeText(getApplicationContext(), "keyboard visible", Toast.LENGTH_SHORT).show();
        } else if (newConfig.keyboardHidden == Configuration.KEYBOARDHIDDEN_YES) {
            Toast.makeText(getApplicationContext(), "keyboard hidden", Toast.LENGTH_SHORT).show();
        }
    }

    private void setUpFragment(int i) {

        switch (i) {
            case R.id.gameLay:
                GamesFragment gamesFragment = new GamesFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_frame_lay, gamesFragment).commit();
                mTxtGames.setTextColor(getResources().getColor(R.color.accentText));
                mTxtCocktails.setTextColor(getResources().getColor(R.color.secondaryText));
                mTxtCures.setTextColor(getResources().getColor(R.color.secondaryText));
                mTxtSearch.setTextColor(getResources().getColor(R.color.secondaryText));
                break;
            case R.id.cocktailLay:
                CocktailsFragment cocktailsFragment = new CocktailsFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_frame_lay, cocktailsFragment).commit();
                mTxtGames.setTextColor(getResources().getColor(R.color.secondaryText));
                mTxtCocktails.setTextColor(getResources().getColor(R.color.accentText));
                mTxtCures.setTextColor(getResources().getColor(R.color.secondaryText));
                mTxtSearch.setTextColor(getResources().getColor(R.color.secondaryText));
                break;
            case R.id.curesLay:
                CuresFragment curesFragment = new CuresFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_frame_lay, curesFragment).commit();
                mTxtGames.setTextColor(getResources().getColor(R.color.secondaryText));
                mTxtCocktails.setTextColor(getResources().getColor(R.color.secondaryText));
                mTxtCures.setTextColor(getResources().getColor(R.color.accentText));
                mTxtSearch.setTextColor(getResources().getColor(R.color.secondaryText));
                break;
            case R.id.searchLay:
                SearchFragment searchFragment = new SearchFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_frame_lay, searchFragment).commit();
                mTxtGames.setTextColor(getResources().getColor(R.color.secondaryText));
                mTxtCocktails.setTextColor(getResources().getColor(R.color.secondaryText));
                mTxtCures.setTextColor(getResources().getColor(R.color.secondaryText));
                mTxtSearch.setTextColor(getResources().getColor(R.color.accentText));
                break;
            default:
                GamesFragment gamesFragmentDefault = new GamesFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_frame_lay, gamesFragmentDefault).commit();
                mTxtGames.setTextColor(getResources().getColor(R.color.accentText));
                mTxtCocktails.setTextColor(getResources().getColor(R.color.secondaryText));
                mTxtCures.setTextColor(getResources().getColor(R.color.secondaryText));
                mTxtSearch.setTextColor(getResources().getColor(R.color.secondaryText));
                break;
        }
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        setUpFragment(id);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_details_search, menu);
        return true;

    }
}

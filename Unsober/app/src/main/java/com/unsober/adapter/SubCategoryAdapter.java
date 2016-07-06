package com.unsober.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.unsober.fragments.CocktailsFragment;
import com.unsober.fragments.CuresFragment;
import com.unsober.fragments.GameDetailsFragment;
import com.unsober.fragments.GamesFragment;
import com.unsober.fragments.SearchFragment;

/**
 * Created by akshay on 05-07-2016.
 */
public class SubCategoryAdapter extends FragmentPagerAdapter {

    private int itemCount;

    public SubCategoryAdapter(FragmentManager fm, int count) {
        super(fm);
        this.itemCount = count;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                GamesFragment gamesFragment = new GamesFragment();
                return gamesFragment;
            case 1:
                CocktailsFragment cocktailsFragment = new CocktailsFragment();
                return cocktailsFragment;
            case 2:
                CuresFragment curesFragment = new CuresFragment();
                return curesFragment;
            case 3:
               /* SearchFragment searchFragment = new SearchFragment();
                return searchFragment;*/
                GameDetailsFragment gameDetailsFragment = new GameDetailsFragment();
                return gameDetailsFragment;

            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return itemCount;
    }
}

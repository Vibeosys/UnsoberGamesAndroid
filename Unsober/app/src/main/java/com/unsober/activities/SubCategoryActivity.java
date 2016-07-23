package com.unsober.activities;

import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.unsober.R;
import com.unsober.fragments.BaseFragment;
import com.unsober.fragments.CocktailsFragment;
import com.unsober.fragments.CuresFragment;
import com.unsober.fragments.GamesFragment;
import com.unsober.fragments.SearchFragment;
import com.google.android.gms.ads.InterstitialAd;
import com.unsober.utils.NetworkUtils;

public class SubCategoryActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = SubCategoryActivity.class.getSimpleName();
    private LinearLayout mGameLay, mCocktailsLay, mCuresLay, mSearchLay, mParentLay;
    private TextView mTxtGames, mTxtCocktails, mTxtCures, mTxtSearch;
    private ImageView mGameImageView, mCocktailsImageView, mCuresImageView, mSearchImageView;
    private int selectedId = 0;
    public static SearchClickListener searchClickListener;
    public static BackPressListener backPress;


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
        mGameImageView = (ImageView) findViewById(R.id.imgGames);
        mCocktailsImageView = (ImageView) findViewById(R.id.imgCocktails);
        mCuresImageView = (ImageView) findViewById(R.id.imgCures);
        mSearchImageView = (ImageView) findViewById(R.id.imgSearch);

        setUpFragment(selectedId);
        mGameLay.setOnClickListener(this);
        mCocktailsLay.setOnClickListener(this);
        mCuresLay.setOnClickListener(this);
        mSearchLay.setOnClickListener(this);


    }

    @Override
    protected View getMainView() throws NullPointerException {
        return null;
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
                BaseFragment.stackFragment.clear();
                getFragmentManager().beginTransaction().replace(R.id.fragment_frame_lay, gamesFragment).commit();
                mTxtGames.setTextColor(getResources().getColor(R.color.accentText));
                mTxtCocktails.setTextColor(getResources().getColor(R.color.secondaryText));
                mTxtCures.setTextColor(getResources().getColor(R.color.secondaryText));
                mTxtSearch.setTextColor(getResources().getColor(R.color.secondaryText));
                mGameImageView.setImageDrawable(getResources().getDrawable(R.drawable.games_button_orange));
                mCocktailsImageView.setImageDrawable(getResources().getDrawable(R.drawable.cocktails_button_black));
                mCuresImageView.setImageDrawable(getResources().getDrawable(R.drawable.cures_button_black));
                mSearchImageView.setImageDrawable(getResources().getDrawable(R.drawable.search_button_black));
                break;
            case R.id.cocktailLay:
                CocktailsFragment cocktailsFragment = new CocktailsFragment();
                BaseFragment.stackFragment.clear();
                getFragmentManager().beginTransaction().replace(R.id.fragment_frame_lay, cocktailsFragment).commit();
                mTxtGames.setTextColor(getResources().getColor(R.color.secondaryText));
                mTxtCocktails.setTextColor(getResources().getColor(R.color.accentText));
                mTxtCures.setTextColor(getResources().getColor(R.color.secondaryText));
                mTxtSearch.setTextColor(getResources().getColor(R.color.secondaryText));
                mGameImageView.setImageDrawable(getResources().getDrawable(R.drawable.games_button_black));
                mCocktailsImageView.setImageDrawable(getResources().getDrawable(R.drawable.cocktails_button_orange));
                mCuresImageView.setImageDrawable(getResources().getDrawable(R.drawable.cures_button_black));
                mSearchImageView.setImageDrawable(getResources().getDrawable(R.drawable.search_button_black));
                break;
            case R.id.curesLay:
                CuresFragment curesFragment = new CuresFragment();
                BaseFragment.stackFragment.clear();
                getFragmentManager().beginTransaction().replace(R.id.fragment_frame_lay, curesFragment).commit();
                mTxtGames.setTextColor(getResources().getColor(R.color.secondaryText));
                mTxtCocktails.setTextColor(getResources().getColor(R.color.secondaryText));
                mTxtCures.setTextColor(getResources().getColor(R.color.accentText));
                mTxtSearch.setTextColor(getResources().getColor(R.color.secondaryText));
                mGameImageView.setImageDrawable(getResources().getDrawable(R.drawable.games_button_black));
                mCocktailsImageView.setImageDrawable(getResources().getDrawable(R.drawable.cocktails_button_black));
                mCuresImageView.setImageDrawable(getResources().getDrawable(R.drawable.cures_button_orange));
                mSearchImageView.setImageDrawable(getResources().getDrawable(R.drawable.search_button_black));
                break;
            case R.id.searchLay:

                SearchFragment searchFragment = new SearchFragment();
                BaseFragment.stackFragment.clear();
                getFragmentManager().beginTransaction().replace(R.id.fragment_frame_lay, searchFragment).commit();
                mTxtGames.setTextColor(getResources().getColor(R.color.secondaryText));
                mTxtCocktails.setTextColor(getResources().getColor(R.color.secondaryText));
                mTxtCures.setTextColor(getResources().getColor(R.color.secondaryText));
                mTxtSearch.setTextColor(getResources().getColor(R.color.accentText));
                mGameImageView.setImageDrawable(getResources().getDrawable(R.drawable.games_button_black));
                mCocktailsImageView.setImageDrawable(getResources().getDrawable(R.drawable.cocktails_button_black));
                mCuresImageView.setImageDrawable(getResources().getDrawable(R.drawable.cures_button_black));
                mSearchImageView.setImageDrawable(getResources().getDrawable(R.drawable.search_button_orange));

                break;
            default:
                GamesFragment gamesFragmentDefault = new GamesFragment();
                BaseFragment.stackFragment.clear();
                getFragmentManager().beginTransaction().replace(R.id.fragment_frame_lay, gamesFragmentDefault).commit();
                mTxtGames.setTextColor(getResources().getColor(R.color.accentText));
                mTxtCocktails.setTextColor(getResources().getColor(R.color.secondaryText));
                mTxtCures.setTextColor(getResources().getColor(R.color.secondaryText));
                mTxtSearch.setTextColor(getResources().getColor(R.color.secondaryText));
                mGameImageView.setImageDrawable(getResources().getDrawable(R.drawable.games_button_orange));
                mCocktailsImageView.setImageDrawable(getResources().getDrawable(R.drawable.cocktails_button_black));
                mCuresImageView.setImageDrawable(getResources().getDrawable(R.drawable.cures_button_black));
                mSearchImageView.setImageDrawable(getResources().getDrawable(R.drawable.search_button_black));
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
        //Put the code to detect the user is subscriber or not
        int i = 1;
        if (i == 1) {
            inflater.inflate(R.menu.app_purchase_opt, menu);

            return true;
        } else {
            inflater.inflate(R.menu.game_details_search, menu);
            SearchView searchView = (SearchView) menu.findItem(R.id.gameDetails_search).getActionView();
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    if (searchClickListener != null)
                        searchClickListener.OnSearchClickListener(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    //searchText = newText;
                    return true;
                }
            });
            searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    if (searchClickListener != null)
                        searchClickListener.OnSearchClickListener("");
                    return false;
                }
            });
            return true;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Toast.makeText(getApplicationContext(), "Back button clicked", Toast.LENGTH_SHORT).show();
                if (this.backPress != null)
                    backPress.OnBackPress();
                break;
        }
        return true;

    }

    public static void setOnSearchClickListener(SearchClickListener listener) {
        searchClickListener = listener;
    }

    public interface SearchClickListener {
        public void OnSearchClickListener(String query);
    }

    public static void setOnBackPress(BackPressListener listener) {
        backPress = listener;
    }

    public interface BackPressListener {
        public void OnBackPress();
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "## Back Pressed");
        if (this.backPress != null)
            backPress.OnBackPress();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseFragment.stackFragment.clear();
    }


}

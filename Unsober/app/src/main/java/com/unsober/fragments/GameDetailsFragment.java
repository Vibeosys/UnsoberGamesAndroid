package com.unsober.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ScrollingView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unsober.R;

/**
 * Created by shrinivas on 06-07-2016.
 */
public class GameDetailsFragment extends Fragment {
    private TextView mGameDescription;
    private ScrollingView mScrollingView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_game_details,container,false);
      //  mScrollingView = (ScrollingView) view.findViewById(R.id.gameScrollview);

        mGameDescription =(TextView) view.findViewById(R.id.txtDescription);
        mGameDescription.setText(Html.fromHtml(getResources().getString(R.string.game_description)));
        /*mGameDescription.setFocusable(true);*/

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
       /* super.onCreateOptionsMenu(menu, inflater);*/
        inflater.inflate(R.menu.game_details_search,menu);
        MenuItem menuItem = menu.findItem(R.id.gameDetails_search);


    }
}

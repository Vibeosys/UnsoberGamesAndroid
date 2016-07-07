package com.unsober.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;


import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.unsober.R;

/**
 * Created by shrinivas on 06-07-2016.
 */
public class GameDetailsFragment extends Fragment {
    private TextView mGameDescription;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_details_layout, container, false);
        getActivity().setTitle("Game Title#1");
        mGameDescription = (TextView) view.findViewById(R.id.txtDescription);
        mGameDescription.setText(Html.fromHtml(getResources().getString(R.string.game_description)));
        /*mGameDescription.setFocusable(true);*/

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
       /* super.onCreateOptionsMenu(menu, inflater);*/
        inflater.inflate(R.menu.game_details_search, menu);
        MenuItem menuItem = menu.findItem(R.id.gameDetails_search);


    }
}

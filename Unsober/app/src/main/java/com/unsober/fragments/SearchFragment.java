package com.unsober.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.List;

import com.unsober.R;
import com.unsober.adapter.SearchSpinnerAdapter;

import java.util.ArrayList;

/**
 * Created by akshay on 05-07-2016.
 */
public class SearchFragment extends Fragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_search_advance, container, false);
        Spinner spinner1 = (Spinner) view.findViewById(R.id.spinner1);
        Spinner spinner2 = (Spinner) view.findViewById(R.id.spinner2);
        Spinner spinner3 = (Spinner) view.findViewById(R.id.spinner3);
        Button btnSearch = (Button) view.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("Football");
        categories.add("Table tennis ");
        categories.add("chess");
        categories.add("Boxing");
        categories.add("Snooker");


        SearchSpinnerAdapter mSearchSpinnerAdapter = new SearchSpinnerAdapter(getActivity().getApplication(), categories);
        spinner1.setAdapter(mSearchSpinnerAdapter);
        spinner2.setAdapter(mSearchSpinnerAdapter);
        spinner3.setAdapter(mSearchSpinnerAdapter);
        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnSearch:
                ItemsListFragment itemsListFragment = new ItemsListFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_frame_lay, itemsListFragment).commit();
                break;
        }
    }
}

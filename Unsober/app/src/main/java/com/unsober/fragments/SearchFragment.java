package com.unsober.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;
import com.unsober.R;
import com.unsober.adapter.SearchSpinnerAdapter;

import java.util.ArrayList;

/**
 * Created by akshay on 05-07-2016.
 */
public class SearchFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_search_advance,container,false);
        Spinner spinner1 = (Spinner)view.findViewById(R.id.spinner1);
        Spinner spinner2 = (Spinner)view.findViewById(R.id.spinner2);
        Spinner spinner3 = (Spinner)view.findViewById(R.id.spinner3);
        List<String> categories = new ArrayList<String>();
        categories.add("Football");
        categories.add("Table tennis ");
        categories.add("chess");
        categories.add("Boxing");
        categories.add("Snooker");

       /* ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity().getApplication(), android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinner1.setAdapter(dataAdapter);
        spinner2.setAdapter(dataAdapter);
        spinner3.setAdapter(dataAdapter);*/


        SearchSpinnerAdapter mSearchSpinnerAdapter = new SearchSpinnerAdapter(getActivity().getApplication(),categories);
        spinner1.setAdapter(mSearchSpinnerAdapter);
        spinner2.setAdapter(mSearchSpinnerAdapter);
        spinner3.setAdapter(mSearchSpinnerAdapter);
        return view;
    }
}

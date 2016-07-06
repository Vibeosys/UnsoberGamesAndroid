package com.unsober.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.unsober.R;
import com.unsober.adapter.GridSubCategoryAdapter;

import java.util.ArrayList;

/**
 * Created by akshay on 05-07-2016.
 */
public class CuresFragment extends Fragment {

    public CuresFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cures, container, false);
        GridView gridView = (GridView) view.findViewById(R.id.subCategoryGrid);
        ArrayList<Integer> data = new ArrayList<>();
        data.add(1);
        data.add(1);
        data.add(1);
        data.add(1);
        data.add(1);
        data.add(1);
        data.add(1);
        data.add(1);
        data.add(1);
        data.add(1);
        GridSubCategoryAdapter adapter = new GridSubCategoryAdapter(data, getActivity().getApplicationContext());
        gridView.setAdapter(adapter);
        return view;
    }
}

package com.unsober.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.unsober.R;
import com.unsober.adapter.GridSubCategoryAdapter;

import java.util.ArrayList;

/**
 * Created by akshay on 06-07-2016.
 */
public class ItemsListFragment extends Fragment {



    public ItemsListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
       /* GridView gridView = (GridView) view.findViewById(R.id.subCategoryGrid);
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
        GridSubCategoryAdapter adapter = new GridSubCategoryAdapter(data, getContext());
        gridView.setAdapter(adapter);*/
        return view;
    }
}

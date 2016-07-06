package com.unsober.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.unsober.R;
import com.unsober.adapter.GridSubCategoryAdapter;
import com.unsober.adapter.ItemsListAdapter;

import java.util.ArrayList;
import java.util.List;

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
        ListView listView = (ListView) view.findViewById(R.id.list_item);
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
        ItemsListAdapter adapter = new ItemsListAdapter(data, getActivity().getApplicationContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GameDetailsFragment gameDetailsFragment = new GameDetailsFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_frame_lay, gameDetailsFragment).commit();
            }
        });
        return view;
    }
}

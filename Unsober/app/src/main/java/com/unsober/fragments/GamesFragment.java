package com.unsober.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.unsober.R;
import com.unsober.adapter.GridSubCategoryAdapter;
import com.unsober.data.ParentCategory;
import com.unsober.data.adapterdata.CategoryDataDTO;

import java.util.ArrayList;

/**
 * Created by akshay on 05-07-2016.
 */
public class GamesFragment extends BaseFragment {
    private String key="key";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        GridView gridView = (GridView) view.findViewById(R.id.subCategoryGrid);
        getActivity().setTitle(getResources().getString(R.string.str_game_title));

        final ArrayList<CategoryDataDTO> data = mDbRepository.getCategoryList(ParentCategory.Games.getValue());
        GridSubCategoryAdapter adapter = new GridSubCategoryAdapter(data, getActivity().getApplicationContext());
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               CategoryDataDTO dataDTO= data.get(position);
                dataDTO.getCategoryName();
                dataDTO.getCategoryId();
               /* Toast.makeText(getActivity().getApplicationContext(),"Category Id "+ dataDTO.getCategoryId()+"Category Name "+dataDTO.getCategoryName(),Toast.LENGTH_LONG).show();*/
                ItemsListFragment itemsListFragment = new ItemsListFragment();
                Bundle bundle = new Bundle();
                bundle.putLong(key,dataDTO.getCategoryId());
                itemsListFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragment_frame_lay, itemsListFragment).commit();
            }
        });
        return view;
    }
}

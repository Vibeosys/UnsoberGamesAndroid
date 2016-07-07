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
import com.unsober.data.ParentCategory;
import com.unsober.data.adapterdata.CategoryDataDTO;

import java.util.ArrayList;

/**
 * Created by akshay on 05-07-2016.
 */
public class CocktailsFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cocktail, container, false);
        GridView gridView = (GridView) view.findViewById(R.id.subCategoryGrid);
        getActivity().setTitle(getResources().getString(R.string.str_title_cocktails));
        ArrayList<CategoryDataDTO> data = new ArrayList<>();
        data = mDbRepository.getCategoryList(ParentCategory.Cocktails.getValue());
        GridSubCategoryAdapter adapter = new GridSubCategoryAdapter(data, getActivity().getApplicationContext());
        gridView.setAdapter(adapter);
        return view;
    }
}

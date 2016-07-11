package com.unsober.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.unsober.R;
import com.unsober.adapter.GridSubCategoryAdapter;
import com.unsober.data.ParentCategory;
import com.unsober.data.adapterdata.CategoryDataDTO;

import java.util.ArrayList;

/**
 * Created by akshay on 05-07-2016.
 */
public class CocktailsFragment extends GridBaseFragment {

    private String key = "categoryId";
    private ArrayList<CategoryDataDTO> mData;
    private GridSubCategoryAdapter mAdapter;
    private TextView mTxtSearchError;
    private GridView mGridView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cocktail, container, false);
        mGridView = (GridView) view.findViewById(R.id.subCategoryGrid);
        mTxtSearchError = (TextView) view.findViewById(R.id.txtSearchError);
        getActivity().setTitle(getResources().getString(R.string.str_title_cocktails));

        mData = mDbRepository.getCategoryList(ParentCategory.Cocktails.getValue());
        mAdapter = new GridSubCategoryAdapter(mData, getActivity().getApplicationContext());
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CategoryDataDTO dataDTO = (CategoryDataDTO) mAdapter.getItem(position);
                callNextFrag(dataDTO);
            }
        });
        return view;
    }

    private void callNextFrag(CategoryDataDTO dataDTO) {
        dataDTO.getCategoryName();
        dataDTO.getCategoryId();
               /* Toast.makeText(getActivity().getApplicationContext(),"Category Id "+ dataDTO.getCategoryId()+"Category Name "+dataDTO.getCategoryName(),Toast.LENGTH_LONG).show();*/
        ItemsListFragment itemsListFragment = new ItemsListFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(key, dataDTO.getCategoryId());
        itemsListFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.fragment_frame_lay, itemsListFragment).commit();
        BaseFragment.stackFragment.push(this);
    }

    @Override
    protected ArrayList<CategoryDataDTO> getList() {
        return mData;
    }

    @Override
    protected GridSubCategoryAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    protected int getParentId() {
        return ParentCategory.Cocktails.getValue();
    }

    @Override
    protected GridView getGridView() {
        return mGridView;
    }

    @Override
    protected TextView getErrorView() {
        return mTxtSearchError;
    }
}

package com.unsober.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.unsober.R;
import com.unsober.activities.SubCategoryActivity;
import com.unsober.adapter.GridSubCategoryAdapter;
import com.unsober.data.adapterdata.CategoryDataDTO;

import java.util.ArrayList;

/**
 * Created by akshay on 09-07-2016.
 */
public abstract class GridBaseFragment extends BaseFragment implements SubCategoryActivity.SearchClickListener {


    private static final String TAG = GridBaseFragment.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SubCategoryActivity.setOnSearchClickListener(this);
    }

    @Override
    public void OnSearchClickListener(String query) {

        Log.d(TAG, "## Search button On Click");
        searchList(query);
    }

    private void searchList(String query) {
        ArrayList<CategoryDataDTO> categoryDataDTOs = getList();
        ArrayList<CategoryDataDTO> searchedList = new ArrayList<>();
        if (!TextUtils.isEmpty(query)) {
            for (CategoryDataDTO category : categoryDataDTOs) {
                if (category.getCategoryName().toLowerCase().contains(query.toLowerCase())) {
                    searchedList.add(category);
                } else {

                }
            }
        } else if (query.equals("")) {
            searchedList = mDbRepository.getCategoryList(getParentId());
        }
        if (searchedList.size() <= 0) {
            getGridView().setVisibility(View.INVISIBLE);
            getErrorView().setVisibility(View.VISIBLE);
        } else {
            getGridView().setVisibility(View.VISIBLE);
            getErrorView().setVisibility(View.GONE);
            getAdapter().refresh(searchedList);
        }

    }


    protected abstract ArrayList<CategoryDataDTO> getList();

    protected abstract GridSubCategoryAdapter getAdapter();

    protected abstract int getParentId();

    protected abstract GridView getGridView();

    protected abstract TextView getErrorView();


    protected void showNoResult(String categoryName) {
        if (getList().size() <= 0) {
            getGridView().setVisibility(View.INVISIBLE);
            getErrorView().setVisibility(View.VISIBLE);
            getErrorView().setText(getResources().getString(R.string.str_no_item_found) + " " + categoryName);
        } else {
            getGridView().setVisibility(View.VISIBLE);
            getErrorView().setVisibility(View.GONE);
        }
    }
}

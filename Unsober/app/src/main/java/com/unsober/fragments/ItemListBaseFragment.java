package com.unsober.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.unsober.R;
import com.unsober.activities.SubCategoryActivity;
import com.unsober.adapter.ItemsListAdapter;
import com.unsober.data.adapterdata.CategoryDataDTO;
import com.unsober.data.adapterdata.GameListDataDTO;

import java.util.ArrayList;

/**
 * Created by akshay on 09-07-2016.
 */
public abstract class ItemListBaseFragment extends BaseFragment implements SubCategoryActivity.SearchClickListener {

    private static final String TAG = ItemListBaseFragment.class.getSimpleName();

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
        ArrayList<GameListDataDTO> gameListDataDTOs = getList();
        ArrayList<GameListDataDTO> searchedList = new ArrayList<>();
        if (!TextUtils.isEmpty(query)) {
            for (GameListDataDTO gameListDataDTO : gameListDataDTOs) {
                if (gameListDataDTO.getGameTitle().toLowerCase().contains(query.toLowerCase())) {
                    searchedList.add(gameListDataDTO);
                } else if (gameListDataDTO.getGameDescription().toLowerCase().contains(query.toLowerCase())) {
                    searchedList.add(gameListDataDTO);

                } else {
                }
            }
        } else if (query.equals("")) {
            searchedList = mDbRepository.getGameList(getCategoryId());
        }
        if (searchedList.size() <= 0) {
            getListView().setVisibility(View.INVISIBLE);
            getErrorView().setVisibility(View.VISIBLE);
        } else {
            getListView().setVisibility(View.VISIBLE);
            getErrorView().setVisibility(View.GONE);
            getAdapter().refresh(searchedList);
        }
    }

    protected abstract ArrayList<GameListDataDTO> getList();

    protected abstract ItemsListAdapter getAdapter();

    protected abstract long getCategoryId();

    protected abstract ListView getListView();

    protected abstract TextView getErrorView();

    protected void showNoResult(String categoryName) {
        if (getList().size() <= 0) {
            getListView().setVisibility(View.INVISIBLE);
            getErrorView().setVisibility(View.VISIBLE);
            getErrorView().setText("No " + categoryName + " available");
        } else {
            getListView().setVisibility(View.VISIBLE);
            getErrorView().setVisibility(View.GONE);
        }
    }
}

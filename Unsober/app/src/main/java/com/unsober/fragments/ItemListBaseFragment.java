package com.unsober.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

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
        getAdapter().refresh(searchedList);
    }

    protected abstract ArrayList<GameListDataDTO> getList();

    protected abstract ItemsListAdapter getAdapter();

    protected abstract long getCategoryId();
}

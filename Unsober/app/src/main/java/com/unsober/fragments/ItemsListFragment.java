package com.unsober.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.unsober.R;
import com.unsober.adapter.ItemsListAdapter;
import com.unsober.data.adapterdata.GameListDataDTO;

import java.util.ArrayList;

/**
 * Created by akshay on 06-07-2016.
 */
public class ItemsListFragment extends ItemListBaseFragment {

    private AdView mAdView;
    private long mCategoryId;
    private ItemsListAdapter mAdapter;
    private ArrayList<GameListDataDTO> mGameListDataDTO;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        ListView listView = (ListView) view.findViewById(R.id.list_item);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mCategoryId = getArguments().getLong("categoryId");
            mGameListDataDTO = mDbRepository.getGameList(mCategoryId);
            mAdapter = new ItemsListAdapter(mGameListDataDTO, getActivity().getApplicationContext());
            listView.setAdapter(mAdapter);
            getActivity().setTitle(mDbRepository.getCategoryName(mCategoryId));
        } else {
            Log.e("ItemList", "Cannot get category Id");
        }


        mAdView = (AdView) view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("0C1256C41F20A2DA8E3751E2E9B38809")
                .addTestDevice("DC7854A3ADFE5403F956AFB5B83C7391")
                .addTestDevice("61626A327E33DC376127B6762DAFAE0C")
                .build();
        mAdView.loadAd(adRequest);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GameListDataDTO gameListDataDTO = (GameListDataDTO) mAdapter.getItem(position);
                callToNextFragment(gameListDataDTO);
            }
        });


        return view;
    }

    private void callToNextFragment(GameListDataDTO gameListDataDTO) {
        GameDetailsFragment gameDetailsFragment = new GameDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("itemId", gameListDataDTO.getItemId());
        gameDetailsFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.fragment_frame_lay, gameDetailsFragment).commit();
        BaseFragment.stackFragment.push(this);
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

    @Override
    protected ArrayList<GameListDataDTO> getList() {
        return mGameListDataDTO;
    }

    @Override
    protected ItemsListAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    protected long getCategoryId() {
        return mCategoryId;
    }
}

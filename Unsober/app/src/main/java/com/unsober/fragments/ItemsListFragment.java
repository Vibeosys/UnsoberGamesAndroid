package com.unsober.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.unsober.R;
import com.unsober.adapter.GridSubCategoryAdapter;
import com.unsober.adapter.ItemsListAdapter;
import com.unsober.data.adapterdata.GameListDataDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akshay on 06-07-2016.
 */
public class ItemsListFragment extends BaseFragment {

    private AdView mAdView;
    private Long mCategoryId;

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
        getActivity().setTitle(getResources().getString(R.string.str_sport_games));
        Bundle bundle = this.getArguments();
        if(bundle != null) {
            mCategoryId = getArguments().getLong("key");
            ArrayList<GameListDataDTO> gameListDataDTO = mDbRepository.getGameList(mCategoryId);
            ItemsListAdapter adapter = new ItemsListAdapter(gameListDataDTO, getActivity().getApplicationContext());
            listView.setAdapter(adapter);
        }
        else
        {
            Log.e("ItemList","Cannot get category Id");
        }

       /* ArrayList<Integer> data = new ArrayList<>();
        data.add(1);
        data.add(1);
        data.add(1);
        data.add(1);
        data.add(1);
        data.add(1);
        data.add(1);
        data.add(1);
        data.add(1);
        data.add(1);*/
        mAdView = (AdView) view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("0C1256C41F20A2DA8E3751E2E9B38809")
                .addTestDevice("DC7854A3ADFE5403F956AFB5B83C7391")
                .addTestDevice("61626A327E33DC376127B6762DAFAE0C")
                .build();
        mAdView.loadAd(adRequest);
        /*ItemsListAdapter adapter = new ItemsListAdapter(data, getActivity().getApplicationContext());
        listView.setAdapter(adapter);*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GameDetailsFragment gameDetailsFragment = new GameDetailsFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_frame_lay, gameDetailsFragment).commit();
            }
        });
        return view;
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
}

package com.unsober.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.unsober.R;
import com.unsober.adapter.SearchSpinnerAdapter;
import com.unsober.data.responsedata.ResponseCategoryDTO;
import com.unsober.data.responsedata.ResponseItemDTO;
import com.unsober.database.SqlContract;
import com.unsober.utils.AppConstants;

import java.util.ArrayList;

/**
 * Created by akshay on 05-07-2016.
 */
public class SearchFragment extends BaseFragment implements View.OnClickListener {
    private SearchSpinnerAdapter mSearchSpinnerAdapter1, mSearchSpinnerAdapter2, mSearchSpinnerAdapter3;
    private String mWhereClause = "";
    private EditText mSearchText;
    private Spinner spinner1, spinner2, spinner3;
    private Button btnSearch;
    private String KeyBundle = "BundleKey";
    private String mTag1 = "", mTag2 = "", mTag3 = "";
    InterstitialAd mInterstitialAd;
    AdRequest mAdRequest;
    private boolean isTagSelected = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        if (mSessionManager.getIsPurchased() == AppConstants.ITEM_PURCHASED) {

        } else {
            mInterstitialAd = new InterstitialAd(getActivity().getApplicationContext());
            mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_id));
            mAdRequest = new AdRequest.Builder()
               /* .addTestDevice("0C1256C41F20A2DA8E3751E2E9B38809")
                .addTestDevice("DC7854A3ADFE5403F956AFB5B83C7391")
                .addTestDevice("61626A327E33DC376127B6762DAFAE0C")*/
                    .build();
            // Load ads into Interstitial Ads
            mInterstitialAd.loadAd(mAdRequest);
            mInterstitialAd.setAdListener(new AdListener() {
                public void onAdLoaded() {
                    showInterstitial();
                }
            });
        }
        View view = inflater.inflate(R.layout.fragment_search_advance, container, false);
        spinner1 = (Spinner) view.findViewById(R.id.spinner1);
        spinner2 = (Spinner) view.findViewById(R.id.spinner2);
        spinner3 = (Spinner) view.findViewById(R.id.spinner3);
        btnSearch = (Button) view.findViewById(R.id.btnSearch);
        mSearchText = (EditText) view.findViewById(R.id.firstEditText);

        getActivity().setTitle(getResources().getString(R.string.str_advanced_search));
        btnSearch.setOnClickListener(this);

      /*  ResponseItemDTO obj1 = new ResponseItemDTO(30, "Test Title game 1", "Test Description wine", "test.png", "", "4", "wine", "rum", "shot", 12, 1, "12 12 12", 10);
        ResponseItemDTO obj2 = new ResponseItemDTO(31, "Test Title game 2", "Test Description rum", "test.png", "", "4", "wine", "rum", "shot", 12, 1, "12 12 12", 10);
        ResponseItemDTO obj3 = new ResponseItemDTO(32, "Test Title game 3", "Test Description WINE", "test.png", "", "4", "WINE", "rum", "shot", 12, 1, "12 12 12", 10);
        ResponseItemDTO obj4 = new ResponseItemDTO(33, "Test Title game 4", "Test Description shot", "test.png", "", "4", "Wine", "rum", "shot", 12, 1, "12 12 12", 10);
        ResponseItemDTO obj5 = new ResponseItemDTO(34, "Test Title game 5", "Test Description", "test.png", "", "4", "takila", "rum", "shot", 12, 1, "12 12 12", 10);
        ResponseItemDTO obj6 = new ResponseItemDTO(35, "Test Title game 6", "Test Description jin", "test.png", "", "4", "jin", "rum", "shot", 12, 1, "12 12 12", 10);
        ResponseItemDTO obj7 = new ResponseItemDTO(36, "Test Title game 7", "Test Description", "test.png", "", "4", "wine", "rum", "shot", 12, 1, "12 12 12", 10);
        ResponseItemDTO obj8 = new ResponseItemDTO(37, "Milk Test", "Milk Test Description", "test.png", "", "4", "milk", "butter", "Paneer", 12, 1, "12 12 12", 10);
        ResponseItemDTO obj9 = new ResponseItemDTO(38, "cold drinks games ", "cold drinks games  Description", "test.png", "", "4", "sprite", "coca", "pepsie", 12, 1, "12 12 12", 10);
        List<ResponseItemDTO> responseItemDTOs = new ArrayList<>();
        responseItemDTOs.add(obj1);
        responseItemDTOs.add(obj2);
        responseItemDTOs.add(obj3);
        responseItemDTOs.add(obj4);
        responseItemDTOs.add(obj5);
        responseItemDTOs.add(obj6);
        responseItemDTOs.add(obj7);
        responseItemDTOs.add(obj8);
        responseItemDTOs.add(obj9);
        mDbRepository.insertItems(responseItemDTOs); // Search Test Data*/

        ArrayList<String> getTag1 = mDbRepository.getFirstTag(SqlContract.SqlItems.TAG1);
        getTag1.add(0, "#Tag 1");
        ArrayList<String> getTag2 = mDbRepository.getFirstTag(SqlContract.SqlItems.TAG2);
        getTag2.add(0, "#Tag 2");
        ArrayList<String> getTag3 = mDbRepository.getFirstTag(SqlContract.SqlItems.TAG3);
        getTag3.add(0, "#Tag 3");

        mSearchSpinnerAdapter1 = new SearchSpinnerAdapter(getActivity().getApplication(), getTag1);
        mSearchSpinnerAdapter2 = new SearchSpinnerAdapter(getActivity().getApplication(), getTag2);
        mSearchSpinnerAdapter3 = new SearchSpinnerAdapter(getActivity().getApplication(), getTag3);
        spinner1.setAdapter(mSearchSpinnerAdapter1);
        spinner2.setAdapter(mSearchSpinnerAdapter2);
        spinner3.setAdapter(mSearchSpinnerAdapter3);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    String value = (String) mSearchSpinnerAdapter1.getItem(position);
                    mTag1 = SqlContract.SqlItems.TAG1 + "='" + value + "'";
                    isTagSelected = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    String value = (String) mSearchSpinnerAdapter2.getItem(position);
                    mTag2 = SqlContract.SqlItems.TAG2 + "='" + value + "'";
                    isTagSelected = true;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    String value = (String) mSearchSpinnerAdapter3.getItem(position);
                    mTag3 = SqlContract.SqlItems.TAG3 + "='" + value + "'";
                    isTagSelected = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnSearch:
                String titleOrDesc = mSearchText.getText().toString();
                if (TextUtils.isEmpty(titleOrDesc) && !isTagSelected) {
                    mWhereClause = "";
                } else if (!TextUtils.isEmpty(titleOrDesc) && !isTagSelected) {
                    mWhereClause = "where " + SqlContract.SqlItems.TITLE + " LIKE '%" + titleOrDesc + "%' OR "
                            + SqlContract.SqlItems.DESC + " LIKE '%" + titleOrDesc + "%'";
                } else if (!TextUtils.isEmpty(titleOrDesc) && isTagSelected) {
                    mWhereClause = "where " + SqlContract.SqlItems.TITLE + " LIKE '%" + titleOrDesc + "%' OR "
                            + SqlContract.SqlItems.DESC + " LIKE '%" + titleOrDesc + "%' and " + createTagClause();
                } else if (TextUtils.isEmpty(titleOrDesc) && isTagSelected) {
                    mWhereClause = "where " + createTagClause();
                }
                callToNextFragment();
                break;
        }
    }

    private void callToNextFragment() {
        Bundle bundle = new Bundle();
        bundle.putString(KeyBundle, mWhereClause);

        AdvancedSearchFragment advancedSearchFragment = new AdvancedSearchFragment();
        advancedSearchFragment.setArguments(bundle);
                /*ItemsListFragment itemsListFragment = new ItemsListFragment();*/
        getFragmentManager().beginTransaction().replace(R.id.fragment_frame_lay, advancedSearchFragment).commit();
        BaseFragment.stackFragment.push(this);
    }

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();


        } else if (mInterstitialAd.isLoading()) {
            mInterstitialAd.show();

        }


    }

    private String createTagClause() {
        String whereTag = "";
        if (!TextUtils.isEmpty(mTag1)) {
            whereTag = mTag1;
        }
        if (!TextUtils.isEmpty(mTag2)) {
            if (TextUtils.isEmpty(whereTag)) {
                whereTag = mTag2;
            } else {
                whereTag = whereTag + " OR " + mTag2;
            }
        }
        if (!TextUtils.isEmpty(mTag3)) {
            if (TextUtils.isEmpty(whereTag)) {
                whereTag = mTag3;
            } else {
                whereTag = whereTag + " OR " + mTag3;
            }

        }
        return whereTag;
    }
}

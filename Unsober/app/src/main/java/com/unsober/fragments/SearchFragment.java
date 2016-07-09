package com.unsober.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.List;

import com.unsober.R;
import com.unsober.adapter.SearchSpinnerAdapter;
import com.unsober.data.responsedata.ResponseCategoryDTO;
import com.unsober.data.responsedata.ResponseItemDTO;
import com.unsober.database.SqlContract;

import java.util.ArrayList;

/**
 * Created by akshay on 05-07-2016.
 */
public class SearchFragment extends BaseFragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_search_advance, container, false);
        Spinner spinner1 = (Spinner) view.findViewById(R.id.spinner1);
        Spinner spinner2 = (Spinner) view.findViewById(R.id.spinner2);
        Spinner spinner3 = (Spinner) view.findViewById(R.id.spinner3);
        Button btnSearch = (Button) view.findViewById(R.id.btnSearch);
        getActivity().setTitle("Advanced Search");
        btnSearch.setOnClickListener(this);
       /* ResponseItemDTO obj1 = new ResponseItemDTO(30,"Test Title","Test Description","test.png","","4","wine","rum","shot",12,1,"12 12 12",10);
        ResponseItemDTO obj2 = new ResponseItemDTO(31,"Test Title","Test Description","test.png","","4","wine","rum","shot",12,1,"12 12 12",10);
        ResponseItemDTO obj3 = new ResponseItemDTO(32,"Test Title","Test Description","test.png","","4","WINE","rum","shot",12,1,"12 12 12",10);
        ResponseItemDTO obj4 = new ResponseItemDTO(33,"Test Title","Test Description","test.png","","4","Wine","rum","shot",12,1,"12 12 12",10);
        ResponseItemDTO obj5 = new ResponseItemDTO(34,"Test Title","Test Description","test.png","","4","takila","rum","shot",12,1,"12 12 12",10);
        ResponseItemDTO obj6 = new ResponseItemDTO(35,"Test Title","Test Description","test.png","","4","jin","rum","shot",12,1,"12 12 12",10);
        ResponseItemDTO obj7 = new ResponseItemDTO(36,"Test Title","Test Description","test.png","","4","wine","rum","shot",12,1,"12 12 12",10);

        List<ResponseItemDTO> responseItemDTOs = new ArrayList<>();
        responseItemDTOs.add(obj1);
        responseItemDTOs.add(obj2);
        responseItemDTOs.add(obj3);
        responseItemDTOs.add(obj4);
        responseItemDTOs.add(obj5);
        responseItemDTOs.add(obj6);
        responseItemDTOs.add(obj7);  this is test data
        mDbRepository.insertItems(responseItemDTOs);*/

        ArrayList<String>getTag1= mDbRepository.getFirstTag(SqlContract.SqlItems.TAG1);
        ArrayList<String>getTag2= mDbRepository.getFirstTag(SqlContract.SqlItems.TAG2);
        ArrayList<String>getTag3= mDbRepository.getFirstTag(SqlContract.SqlItems.TAG3);



        List<String> categories = new ArrayList<String>();
        categories.add("Football");
        categories.add("Table tennis ");
        categories.add("chess");
        categories.add("Boxing");
        categories.add("Snooker");


        SearchSpinnerAdapter mSearchSpinnerAdapter1 = new SearchSpinnerAdapter(getActivity().getApplication(), getTag1);
        SearchSpinnerAdapter mSearchSpinnerAdapter2 = new SearchSpinnerAdapter(getActivity().getApplication(), getTag2);
        SearchSpinnerAdapter mSearchSpinnerAdapter3 = new SearchSpinnerAdapter(getActivity().getApplication(), getTag3);
        spinner1.setAdapter(mSearchSpinnerAdapter1);
        spinner2.setAdapter(mSearchSpinnerAdapter2);
        spinner3.setAdapter(mSearchSpinnerAdapter3);

        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnSearch:
                ItemsListFragment itemsListFragment = new ItemsListFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_frame_lay, itemsListFragment).commit();
                break;
        }
    }
}

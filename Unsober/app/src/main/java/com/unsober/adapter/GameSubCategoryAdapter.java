package com.unsober.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.unsober.R;

import java.util.ArrayList;

/**
 * Created by akshay on 05-07-2016.
 */
public class GameSubCategoryAdapter extends BaseAdapter {

    private ArrayList<Integer> data;
    private Context mContext;

    public GameSubCategoryAdapter(ArrayList<Integer> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder viewHolder = null;
        if (row == null) {

            LayoutInflater theLayoutInflator = (LayoutInflater) mContext.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            row = theLayoutInflator.inflate(R.layout.row_sub_category, null);
        } else
            viewHolder = (ViewHolder) convertView.getTag();
        return row;
    }

    private class ViewHolder {
    }
}

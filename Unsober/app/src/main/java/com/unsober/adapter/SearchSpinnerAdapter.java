package com.unsober.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.unsober.R;

import java.util.List;

/**
 * Created by shrinivas on 07-07-2016.
 */
public class SearchSpinnerAdapter extends BaseAdapter{
    private Context mContext;
    List<String> categories;
    public SearchSpinnerAdapter(Context mContext,  List<String> categories)
    {
        this.mContext =mContext;
        this.categories = categories;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
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
            row = theLayoutInflator.inflate(R.layout.search_spinner_child_element, null);
            viewHolder = new ViewHolder();
            viewHolder.spinnerChild = (TextView) row.findViewById(R.id.txtSpinnerChild);
            row.setTag(viewHolder);

        } else
            viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.spinnerChild.setText(categories.get(position));

        return row;
    }

    private class ViewHolder {
        TextView spinnerChild;
    }
   /* public void addItem(final TypeDataDTO item) {
        mCategories.add(item);
        notifyDataSetChanged();
    }

    public void clear() {
        mCategories.clear();
    }*/
}

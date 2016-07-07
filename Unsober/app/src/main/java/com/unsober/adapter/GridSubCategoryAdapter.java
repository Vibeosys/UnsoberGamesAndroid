package com.unsober.adapter;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.unsober.R;
import com.unsober.data.adapterdata.CategoryDataDTO;
import com.unsober.utils.CustomVolleyRequestQueue;

import java.util.ArrayList;

/**
 * Created by akshay on 05-07-2016.
 */
public class GridSubCategoryAdapter extends BaseAdapter {

    private ArrayList<CategoryDataDTO> data;
    private Context mContext;
    private ImageLoader mImageLoader;

    public GridSubCategoryAdapter(ArrayList<CategoryDataDTO> data, Context mContext) {
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
            viewHolder = new ViewHolder();
            viewHolder.txtCategoryTitle = (TextView) row.findViewById(R.id.txtCategoryTitle);
            viewHolder.imgCategory = (NetworkImageView) row.findViewById(R.id.imgCategory);
            row.setTag(viewHolder);

        } else
            viewHolder = (ViewHolder) convertView.getTag();
        CategoryDataDTO categoryDataDTO = data.get(position);
        viewHolder.txtCategoryTitle.setText(categoryDataDTO.getCategoryName());
        mImageLoader = CustomVolleyRequestQueue.getInstance(mContext)
                .getImageLoader();
        final String url = categoryDataDTO.getCategoryIcon();
        if (url != null && !url.isEmpty()) {
            try {
                mImageLoader.get(url, ImageLoader.getImageListener(viewHolder.imgCategory,
                        R.drawable.cooler, R.drawable.cooler));
                viewHolder.imgCategory.setImageUrl(url, mImageLoader);
            } catch (Exception e) {
                viewHolder.imgCategory.setImageResource(R.drawable.cooler);
            }
        } else {
            viewHolder.imgCategory.setImageResource(R.drawable.cooler);
        }
        Log.d("NewsAdapter", "## Category " + categoryDataDTO.getCategoryName());
        return row;
    }

    private class ViewHolder {
        TextView txtCategoryTitle;
        NetworkImageView imgCategory;
    }
}

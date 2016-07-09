package com.unsober.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.unsober.R;
import com.unsober.data.adapterdata.GameListDataDTO;
import com.unsober.utils.CustomVolleyRequestQueue;

import java.util.ArrayList;

/**
 * Created by akshay on 05-07-2016.
 */
public class ItemsListAdapter extends BaseAdapter {

    private ArrayList<GameListDataDTO> gameListDataDTO;
    private Context mContext;
    private ImageLoader mImageLoader;

    public ItemsListAdapter(ArrayList<GameListDataDTO> gameListDataDTO, Context mContext) {
        this.gameListDataDTO = gameListDataDTO;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return gameListDataDTO.size();
    }

    @Override
    public Object getItem(int position) {
        return gameListDataDTO.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder viewHolder = null;
        if (row == null) {

            LayoutInflater theLayoutInflator = (LayoutInflater) mContext.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            row = theLayoutInflator.inflate(R.layout.row_item_list, null);
            viewHolder = new ViewHolder();
            viewHolder.gameTitle = (TextView) row.findViewById(R.id.gameTitle);
            viewHolder.NumberOfPlayers = (TextView) row.findViewById(R.id.gameNumberOfPlayers);
            viewHolder.gameImage = (NetworkImageView) row.findViewById(R.id.gameImageView);
            row.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        GameListDataDTO gameList = gameListDataDTO.get(position);

        viewHolder.gameTitle.setText(gameList.getGameTitle());
        viewHolder.NumberOfPlayers.setText("Minimum of " + gameList.getNumberOfPlayers() + " Players");
        viewHolder.gameImage.setImageResource(R.drawable.ic_icon);
        mImageLoader = CustomVolleyRequestQueue.getInstance(mContext)
                .getImageLoader();
        final String url = gameList.getImageLink();
        if (url != null && !url.isEmpty()) {
            try {
                mImageLoader.get(url, ImageLoader.getImageListener(viewHolder.gameImage,
                        R.drawable.ic_icon, R.drawable.ic_icon));
                viewHolder.gameImage.setImageUrl(url, mImageLoader);
            } catch (Exception e) {
                viewHolder.gameImage.setImageResource(R.drawable.ic_icon);
            }
        } else {
            viewHolder.gameImage.setImageResource(R.drawable.ic_icon);
        }
        return row;
    }

    public void refresh(ArrayList<GameListDataDTO> searchedList) {
        this.gameListDataDTO.clear();
        this.gameListDataDTO.addAll(searchedList);
        notifyDataSetChanged();
    }

    private class ViewHolder {
        TextView gameTitle;
        TextView NumberOfPlayers;
        NetworkImageView gameImage;
    }
}

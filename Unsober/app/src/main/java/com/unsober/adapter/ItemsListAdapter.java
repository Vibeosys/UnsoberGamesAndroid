package com.unsober.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.unsober.R;
import com.unsober.data.adapterdata.GameListDataDTO;

import java.util.ArrayList;

/**
 * Created by akshay on 05-07-2016.
 */
public class ItemsListAdapter extends BaseAdapter {

    private ArrayList<Integer> data;
    private ArrayList<GameListDataDTO> gameListDataDTO;
    private Context mContext;

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
            row.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        GameListDataDTO gameList = gameListDataDTO.get(position);

        viewHolder.gameTitle.setText(gameList.getGameTitle());
        viewHolder.NumberOfPlayers.setText("Minimum of "+ gameList.getNumberOfPlayers()+" Players");

        return row;
    }

    private class ViewHolder {
        TextView gameTitle;
        TextView NumberOfPlayers;
        ImageView gameImage;
    }
}

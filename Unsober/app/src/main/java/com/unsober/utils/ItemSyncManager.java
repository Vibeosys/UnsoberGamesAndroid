package com.unsober.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.unsober.data.responsedata.ResponseGetItem;
import com.unsober.data.responsedata.ResponseItemDTO;
import com.unsober.database.DbRepository;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by shrinivas on 07-07-2016.
 */
public class ItemSyncManager implements ServerSyncManager.OnSuccessResultReceived, ServerSyncManager.OnErrorResultReceived {

    private static final String TAG = ItemSyncManager.class.getSimpleName();
    private DbRepository mDbRepository;
    private SessionManager mSessionManager;
    private Context mContext;
    private ServerSyncManager mSyncManager;


    public ItemSyncManager(Context context, SessionManager sessionManager, ServerSyncManager syncManager) {
        this.mSessionManager = sessionManager;
        this.mContext = context;
        this.mSyncManager = syncManager;
        mDbRepository = new DbRepository(mContext, mSessionManager);
        mSyncManager.setOnStringErrorReceived(this);
        mSyncManager.setOnStringResultReceived(this);
    }
    public void syncWithServer()
    {

        mSyncManager.uploadDataToServer(ServerRequestConstants.REQUEST_ITEM,
                mSessionManager.getItemUrl());
    }

    @Override
    public void onVolleyErrorReceived(@NonNull VolleyError error, int requestToken) {
        switch (requestToken) {
            case ServerRequestConstants.REQUEST_ITEM:
                Log.e(TAG, "##Volley Server error " + error.toString());
                break;
        }
    }

    @Override
    public void onResultReceived(@NonNull JSONObject data, int requestToken) {
        switch (requestToken) {
            case ServerRequestConstants.REQUEST_ITEM:
                try
                {
                    ResponseGetItem responseItem = new Gson().fromJson(data.toString(), ResponseGetItem.class);
                    FillDbData fillDbData = new FillDbData(responseItem.getResponseItemDTO());
                    fillDbData.execute();
                }catch (Exception e)
                {
                    Log.e(TAG,e.toString());
                }

                break;
        }
    }
    private class FillDbData extends AsyncTask<Void, Void, Void> {

        ArrayList<ResponseItemDTO> itmes;

        public FillDbData(ArrayList<ResponseItemDTO> itmes) {
            this.itmes = itmes;
        }

        @Override
        protected Void doInBackground(Void... params) {
            mDbRepository.inserItems(this.itmes);
            return null;
        }
    }
}

package com.unsober.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.unsober.data.responsedata.ResponseCategoryDTO;
import com.unsober.data.responsedata.ResponseGetCategory;
import com.unsober.database.DbRepository;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by akshay on 07-07-2016.
 */
public class CategorySyncManager implements ServerSyncManager.OnSuccessResultReceived, ServerSyncManager.OnErrorResultReceived {

    private static final String TAG = CategorySyncManager.class.getSimpleName();
    private DbRepository mDbRepository;
    private SessionManager mSessionManager;
    private Context mContext;
    private ServerSyncManager mSyncManager;

    public CategorySyncManager() {
    }

    public CategorySyncManager(Context context, SessionManager sessionManager, ServerSyncManager syncManager) {
        this.mSessionManager = sessionManager;
        this.mContext = context;
        this.mSyncManager = syncManager;
        mDbRepository = new DbRepository(mContext, mSessionManager);
        mSyncManager.setOnStringErrorReceived(this);
        mSyncManager.setOnStringResultReceived(this);
    }

    public void syncWithServer() {

        mSyncManager.uploadDataToServer(ServerRequestConstants.REQUEST_CATEGORY,
                mSessionManager.getCategoryUrl());
    }


    @Override
    public void onVolleyErrorReceived(@NonNull VolleyError error, int requestToken) {
        switch (requestToken) {
            case ServerRequestConstants.REQUEST_CATEGORY:
                Log.e(TAG, "##Volley Server error " + error.toString());
                break;
        }
    }

    @Override
    public void onResultReceived(@NonNull JSONObject data, int requestToken) {
        switch (requestToken) {
            case ServerRequestConstants.REQUEST_CATEGORY:
                ResponseGetCategory responseGetCategory = new Gson().fromJson(data.toString(), ResponseGetCategory.class);
                FillDbData fillDbData = new FillDbData(responseGetCategory.getCategories());
                fillDbData.execute();
                break;
        }
    }

    private class FillDbData extends AsyncTask<Void, Void, Void> {

        ArrayList<ResponseCategoryDTO> categories;

        public FillDbData(ArrayList<ResponseCategoryDTO> categories) {
            this.categories = categories;
        }

        @Override
        protected Void doInBackground(Void... params) {
            mDbRepository.insertCategory(this.categories);
            return null;
        }
    }
}

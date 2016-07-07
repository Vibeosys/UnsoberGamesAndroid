package com.unsober.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.volley.VolleyError;
import com.unsober.database.DbRepository;

import org.json.JSONObject;

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

       /* mSyncManager.uploadDataToServer(ServerRequestConstants.REQUEST_CATEGORY,
                mSessionManager.getSignalUrl());*/
    }


    @Override
    public void onVolleyErrorReceived(@NonNull VolleyError error, int requestToken) {

    }

    @Override
    public void onResultReceived(@NonNull JSONObject data, int requestToken) {

    }
}

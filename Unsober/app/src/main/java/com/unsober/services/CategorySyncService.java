package com.unsober.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.unsober.utils.AppConstants;
import com.unsober.utils.CategorySyncManager;
import com.unsober.utils.NetworkUtils;
import com.unsober.utils.ServerSyncManager;
import com.unsober.utils.SessionManager;


/**
 * Created by akshay on 07-07-2016.
 */
public class CategorySyncService extends IntentService {

    private static final String TAG = CategorySyncService.class.getSimpleName();

    public CategorySyncService() {
        super(CategorySyncService.class.getName());
    }

    boolean flagStop = false;

    @Override
    public void onCreate() {
        super.onCreate();
        flagStop = false;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SessionManager mSessionManager = SessionManager.getInstance(getApplicationContext());
        ServerSyncManager mServerSyncManager = new ServerSyncManager(getApplicationContext(),
                mSessionManager);
        CategorySyncManager categorySyncManager = new CategorySyncManager(getApplicationContext(),
                mSessionManager, mServerSyncManager);

        while (true) {
            synchronized (this) {
                try {
                    if (flagStop)
                        break;
                    if (NetworkUtils.isActiveNetworkAvailable(getApplicationContext()))
                        categorySyncManager.syncWithServer();

                    Log.d(TAG, "##In Signal sync service");
                    //TODO: Hardcoded time for now, need to read from properties
                    wait(AppConstants.SERVICE_TIME_OUT * 1000);
                } catch (Exception e) {
                    Log.e(TAG, "##Error occurred in Signal Sync service " + e.toString());
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        flagStop = true;
    }
}

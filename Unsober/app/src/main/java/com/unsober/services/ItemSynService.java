package com.unsober.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.unsober.utils.AppConstants;
import com.unsober.utils.ItemSyncManager;
import com.unsober.utils.NetworkUtils;
import com.unsober.utils.ServerSyncManager;
import com.unsober.utils.SessionManager;


/**
 * Created by shrinivas on 07-07-2016.
 */
public class ItemSynService extends IntentService {

    private static final String TAG_ITEM = CategorySyncService.class.getSimpleName();
    boolean flagStop = false;
   public ItemSynService()
    {
        super(ItemSynService.class.getName());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        flagStop =false;

    }

    @Override
    protected void onHandleIntent(Intent intent) {

        SessionManager mSessionManager = SessionManager.getInstance(getApplicationContext());
        ServerSyncManager mServerSyncManager = new ServerSyncManager(getApplicationContext(),
                mSessionManager);
        ItemSyncManager itemSyncManager = new ItemSyncManager(getApplicationContext(),mSessionManager,mServerSyncManager);

        while (true)
        {
            synchronized (this)
            {
                try
                {
                    if (flagStop)
                        break;
                    if (NetworkUtils.isActiveNetworkAvailable(getApplicationContext()))
                        itemSyncManager.syncWithServer();

                    Log.d(TAG_ITEM, "##In Signal sync service");
                    //TODO: Hardcoded time for now, need to read from properties
                    wait(AppConstants.SERVICE_TIME_OUT * 1000);
                }catch (Exception e)
                {
                    Log.e(TAG_ITEM,"##Error occurred in Signal Sync service " + e.toString());
                }
            }
        }


    }
}

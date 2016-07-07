package com.unsober.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by akshay on 07-07-2016.
 */
public class SessionManager {

    private static final String PROJECT_PREFERENCES = "com.unsober";
    private static final String TAG = SessionManager.class.getSimpleName();

    private static SessionManager mSessionManager;
    private static SharedPreferences mProjectSharedPref = null;
    private static Context mContext = null;
    private static PropertyFileReader mPropertyFileReader = null;

    public static SessionManager getInstance(Context context) {
        if (mSessionManager != null)
            return mSessionManager;
        mContext = context;
        mPropertyFileReader = mPropertyFileReader.getInstance(context);
        loadProjectSharedPreferences();
        mSessionManager = new SessionManager();
        return mSessionManager;
    }

    public static SessionManager Instance() throws IllegalArgumentException {
        if (mSessionManager != null)
            return mSessionManager;
        else
            throw new IllegalArgumentException("No instance is yet created");
    }

    private static void loadProjectSharedPreferences() {
        if (mProjectSharedPref == null) {
            mProjectSharedPref = mContext.getSharedPreferences(PROJECT_PREFERENCES, Context.MODE_PRIVATE);
        }

        String versionNumber = mProjectSharedPref.getString(PropertyTypeConstants.VERSION_NUMBER, null);
        Float versionNoValue = versionNumber == null ? 0 : Float.valueOf(versionNumber);

        if (mPropertyFileReader.getVersion() > versionNoValue) {
            boolean sharedPrefChange = addOrUdateSharedPreferences();
            if (!sharedPrefChange)
                Log.e(TAG, "No shared preferences are changed");
        }
    }

    private static boolean addOrUdateSharedPreferences() {

        SharedPreferences.Editor editor = mProjectSharedPref.edit();
        editor.putInt(PropertyTypeConstants.DATABASE_VERSION_NUMBER, mPropertyFileReader.getDbVersion());
        editor.putString(PropertyTypeConstants.GET_CATEGORY_URL, mPropertyFileReader.getCategoryUrl());
        editor.putString(PropertyTypeConstants.GET_ITEMS_URL, mPropertyFileReader.getItemUrl());
        editor.apply();
        return true;
    }

    private SessionManager() {
    }

    public String getDatabaseDeviceFullPath() {
        return mProjectSharedPref.getString(PropertyTypeConstants.DATABASE_DEVICE_FULLPATH, null);
    }

    public int getDatabaseVersion() {
        Log.d("DB version", "##" + mProjectSharedPref.getInt(PropertyTypeConstants.DATABASE_VERSION_NUMBER, 0));
        return mProjectSharedPref.getInt(PropertyTypeConstants.DATABASE_VERSION_NUMBER, 0);
    }

    public String getCategoryUrl() {
        return mProjectSharedPref.getString(PropertyTypeConstants.GET_CATEGORY_URL, null);
    }

    public String getItemUrl() {
        return mProjectSharedPref.getString(PropertyTypeConstants.GET_ITEMS_URL, null);
    }
}

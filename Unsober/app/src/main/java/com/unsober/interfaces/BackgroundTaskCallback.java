package com.unsober.interfaces;

/**
 * Callback for handling async task states
 * Created by akshay on 07-07-2016.
 */
public interface BackgroundTaskCallback {
    void onResultReceived(String downloadedJson);
}

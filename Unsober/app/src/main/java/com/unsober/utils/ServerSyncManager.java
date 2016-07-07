package com.unsober.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.unsober.database.DbRepository;
import com.unsober.interfaces.BackgroundTaskCallback;

import org.json.JSONObject;

/**
 * Created by akshay on 07-07-2016.
 */
public class ServerSyncManager implements BackgroundTaskCallback {

    private String TAG = ServerSyncManager.class.getSimpleName();

    private SessionManager mSessionManager;
    private Context mContext;
    private OnSuccessResultReceived mOnSuccessResultReceived;
    private OnErrorResultReceived mErrorReceived;

    public ServerSyncManager() {

    }

    public ServerSyncManager(@NonNull Context context, @NonNull SessionManager sessionManager) {
        mContext = context;
        mSessionManager = sessionManager;
    }

    public void uploadDataToServer(int requestToken, String url) {
        /*String uploadJson = prepareUploadJsonFromData(params);*/
        Log.i(TAG, "##" + url);
        AsyncDownloadData asyncDownloadData = new AsyncDownloadData(url, requestToken);
        asyncDownloadData.execute();
    }

    public void setOnStringResultReceived(OnSuccessResultReceived stringResultReceived) {
        mOnSuccessResultReceived = stringResultReceived;
    }

    public void setOnStringErrorReceived(OnErrorResultReceived stringErrorReceived) {
        mErrorReceived = stringErrorReceived;
    }


    private class AsyncDownloadData extends AsyncTask<Void, Void, Void> {

        private String url;
        private int requestToken;

        public AsyncDownloadData(String url, int requestToken) {
            this.url = url;
            this.requestToken = requestToken;
        }

        @Override
        protected Void doInBackground(Void... params) {
            uploadJsonToServer(this.url, this.requestToken);
            return null;
        }
    }

    private void uploadJsonToServer(String uploadUrl,
                                    final int requestToken) {
        RequestQueue vollyRequest = Volley.newRequestQueue(mContext);

        JsonObjectRequest uploadRequest = new JsonObjectRequest(Request.Method.POST,
                uploadUrl, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                if (response == null) {
                    Log.e(TAG, "Error to get the data from server");
                    return;
                }
                if (mOnSuccessResultReceived != null) {
                    mOnSuccessResultReceived.onResultReceived(response, requestToken);
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if (mErrorReceived != null)
                    mErrorReceived.onVolleyErrorReceived(error, requestToken);
            }
        });
        uploadRequest.setRetryPolicy(new DefaultRetryPolicy(15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        vollyRequest.add(uploadRequest);
    }

    public interface OnSuccessResultReceived {
        void onResultReceived(@NonNull JSONObject data, int requestToken);
    }


    public interface OnErrorResultReceived {
        void onVolleyErrorReceived(@NonNull VolleyError error, int requestToken);
    }

    @Override
    public void onResultReceived(String downloadedJson) {

    }


}

package com.unsober.activities;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.vending.billing.IInAppBillingService;
import com.unsober.R;
import com.unsober.utils.AppConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.UUID;

public class AppPurchaseActivity extends BaseActivity implements View.OnClickListener {

    private Button mBtnPurchase, mBtnCancel;
    private String mAppId = "AIzaSyAaO28Iu2WSBHOxiLtCQcfJ5tyHRZ_BFAA";
    IInAppBillingService mService;
    String mPrice;
    private UUID mTransactionId;
    ServiceConnection mServiceConn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name,
                                       IBinder service) {
            mService = IInAppBillingService.Stub.asInterface(service);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_purchase);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.inapp_purchase));
        mBtnPurchase = (Button) findViewById(R.id.purchaseProduct);
        mBtnCancel = (Button) findViewById(R.id.cancelProduct);
        mBtnPurchase.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);

        Intent serviceIntent =
                new Intent("com.android.vending.billing.InAppBillingService.BIND");
        serviceIntent.setPackage("com.android.vending");
        bindService(serviceIntent, mServiceConn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected View getMainView() throws NullPointerException {
        return null;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.purchaseProduct:
                int isBillingSupported = -1;
                try {
                    isBillingSupported = mService.isBillingSupported(3, getPackageName(), "inapp");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                if (isBillingSupported != 0) {

                }
                //Toast.makeText(getApplicationContext(), "The Billing is not supported", Toast.LENGTH_SHORT).show();
                else
                    getPurchases();
                break;
            case R.id.cancelProduct:
                finish();
                break;
        }
    }

    private void purchaseProducts() {
        ArrayList<String> skuList = new ArrayList<String>();
        skuList.add("com.unsober.proversion");
        Bundle querySkus = new Bundle();
        querySkus.putStringArrayList("ITEM_ID_LIST", skuList);
        AsynckGetSkuBundle skuBundle = new AsynckGetSkuBundle();
        skuBundle.execute(querySkus);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mService != null) {
            unbindService(mServiceConn);
        }
    }

    public void getPurchases() {
        try {
            Bundle purchaseItems = mService.getPurchases(3, getPackageName(), "inapp", null);
            int responseCode = purchaseItems.getInt("RESPONSE_CODE");
            if (responseCode == 0) {
                ArrayList<String> items = purchaseItems.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
                if (items.contains("com.unsober.proversion")) {
                    Toast.makeText(getApplicationContext(), "Product is already purchased", Toast.LENGTH_SHORT).show();
                } else {
                    purchaseProducts();
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private class AsynckGetSkuBundle extends AsyncTask<Bundle, Void, Bundle> {
        @Override
        protected Bundle doInBackground(Bundle... params) {
            Bundle skuDetails = null;
            try {
                skuDetails = mService.getSkuDetails(3,
                        getPackageName(), "inapp", params[0]);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return skuDetails;
        }

        @Override
        protected void onPostExecute(Bundle skuDetails) {
            super.onPostExecute(skuDetails);
            try {
                int response = skuDetails.getInt("RESPONSE_CODE");
                if (response == 0) {
                    ArrayList<String> responseList
                            = skuDetails.getStringArrayList("DETAILS_LIST");

                    for (String thisResponse : responseList) {
                        JSONObject object = new JSONObject(thisResponse);
                        String sku = object.getString("productId");
                        String price = object.getString("price");
                        if (sku.equals("com.unsober.proversion"))
                            mPrice = price;
                        mTransactionId = UUID.randomUUID();
                        Bundle buyIntentBundle = mService.getBuyIntent(3, getPackageName(),
                                sku, "inapp", mTransactionId.toString());

                        PendingIntent pendingIntent = buyIntentBundle.getParcelable("BUY_INTENT");

                        startIntentSenderForResult(pendingIntent.getIntentSender(), 1001,
                                null, Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0));
                    }
                   /* Bundle buyIntentBundle = mService.getBuyIntent(3, getPackageName(),
                            "com.unsober.proversion", "inapp", mAppId);
                    if (buyIntentBundle.getInt("RESPONSE_CODE") == 0) {
                        PendingIntent pendingIntent = buyIntentBundle.getParcelable("BUY_INTENT");
                        startIntentSenderForResult(pendingIntent.getIntentSender(), 1001,
                                new Intent(), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0));
                    }*/

                }
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1001) {
            int responseCode = data.getIntExtra("RESPONSE_CODE", 0);
            String purchaseData = data.getStringExtra("INAPP_PURCHASE_DATA");
            String dataSignature = data.getStringExtra("INAPP_DATA_SIGNATURE");

            if (resultCode == 0 && responseCode == 0) {
                try {
                    JSONObject jo = new JSONObject(purchaseData);
                    String sku = jo.getString("productId");
                    String payload = jo.getString("developerPayload");
                    if (sku.equals("com.unsober.proversion") && payload.equals(mTransactionId.toString()))
                        Toast.makeText(getApplicationContext(), "You have bought the " + sku +
                                ". Thank you!", Toast.LENGTH_LONG).show();
                    mSessionManager.setIsPurchased(AppConstants.ITEM_PURCHASED);
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Failed to parse purchase data.", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }
    }
}

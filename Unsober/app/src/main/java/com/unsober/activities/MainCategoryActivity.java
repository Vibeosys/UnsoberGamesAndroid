package com.unsober.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.android.vending.billing.IInAppBillingService;
import com.unsober.R;
import com.unsober.utils.AppConstants;

import java.util.ArrayList;

public class MainCategoryActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout mLayoutGame, mLayoutCocktails, mLayoutCures;
    IInAppBillingService mService;
    ServiceConnection mServiceConn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_category);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.main_category_title));
        if (mSessionManager.getIsAccepted() == 0) {
            callToDisclaimer();
            return;
        }
        mServiceConn = new ServiceConnection() {
            @Override
            public void onServiceDisconnected(ComponentName name) {
                mService = null;
            }

            @Override
            public void onServiceConnected(ComponentName name,
                                           IBinder service) {
                mService = IInAppBillingService.Stub.asInterface(service);
                isAppPurchased();
            }
        };
        mLayoutGame = (LinearLayout) findViewById(R.id.layoutGame);
        mLayoutCocktails = (LinearLayout) findViewById(R.id.layoutCocktails);
        mLayoutCures = (LinearLayout) findViewById(R.id.layoutCures);

        Intent serviceIntent =
                new Intent("com.android.vending.billing.InAppBillingService.BIND");
        serviceIntent.setPackage("com.android.vending");
        bindService(serviceIntent, mServiceConn, Context.BIND_AUTO_CREATE);

        mLayoutGame.setOnClickListener(this);
        mLayoutCocktails.setOnClickListener(this);
        mLayoutCures.setOnClickListener(this);

    }

    private void callToDisclaimer() {

        Intent iDisclaimer = new Intent(getApplicationContext(), DisclaimerActivity.class);
        iDisclaimer.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(iDisclaimer);
        finish();
    }

    private void isAppPurchased() {
        int isBillingSupported = -1;
        try {
            isBillingSupported = mService.isBillingSupported(3, getPackageName(), "inapp");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        if (isBillingSupported != 0) {

        }
        //Toast.makeText(getApplicationContext(), "The Billing is not supported", Toast.LENGTH_SHORT).show();
        else
            getPurchases();
    }

    public void getPurchases() {
        try {
            Bundle purchaseItems = mService.getPurchases(3, getPackageName(), "inapp", null);
            int responseCode = purchaseItems.getInt("RESPONSE_CODE");
            if (responseCode == 0) {
                ArrayList<String> items = purchaseItems.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
                if (items.contains("com.unsober.proversion")) {
                    //Toast.makeText(getApplicationContext(), "Product is already purchased", Toast.LENGTH_SHORT).show();
                    mSessionManager.setIsPurchased(AppConstants.ITEM_PURCHASED);
                } else {
                    mSessionManager.setIsPurchased(AppConstants.ITEM_NOT_PURCHASED);
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mService != null) {
            unbindService(mServiceConn);
        }
    }

    @Override
    protected View getMainView() throws NullPointerException {
        return null;
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_details_search, menu);
        return true;

    }*/

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.layoutGame:
                Intent gameIntent = new Intent(getApplicationContext(), SubCategoryActivity.class);
                gameIntent.putExtra("TabId", R.id.gameLay);
                startActivity(gameIntent);
                finish();
                break;
            case R.id.layoutCocktails:
                Intent cocktailIntent = new Intent(getApplicationContext(), SubCategoryActivity.class);
                cocktailIntent.putExtra("TabId", R.id.cocktailLay);
                startActivity(cocktailIntent);
                finish();
                break;
            case R.id.layoutCures:
                Intent curesIntent = new Intent(getApplicationContext(), SubCategoryActivity.class);
                curesIntent.putExtra("TabId", R.id.curesLay);
                startActivity(curesIntent);
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

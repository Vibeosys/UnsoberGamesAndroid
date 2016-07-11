package com.unsober.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.unsober.R;
import com.unsober.services.CategorySyncService;
import com.unsober.services.ItemSynService;

public class DisclaimerActivity extends BaseActivity implements View.OnClickListener {

    TextView txtAccept;
    Intent syncCategoryIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        syncCategoryIntent = new Intent(Intent.ACTION_SYNC, null, this, CategorySyncService.class);
        Intent syncItemIntent = new Intent(Intent.ACTION_SYNC, null, this, ItemSynService.class);
        startService(syncItemIntent);
        setContentView(R.layout.activity_disclaimer);
        getSupportActionBar().hide();
        txtAccept = (TextView) findViewById(R.id.txtAccept);
        txtAccept.setOnClickListener(this);
        startService(syncCategoryIntent);
    }

    @Override
    protected View getMainView() throws NullPointerException {
        return null;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.txtAccept:
                mSessionManager.setIsAccepted(1);
                startActivity(new Intent(getApplicationContext(), MainCategoryActivity.class));
                finish();
                break;
        }

    }
}

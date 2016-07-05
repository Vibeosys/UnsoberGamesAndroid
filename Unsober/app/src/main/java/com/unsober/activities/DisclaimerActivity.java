package com.unsober.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.unsober.R;

public class DisclaimerActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtAccept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclaimer);
        getSupportActionBar().hide();
        txtAccept = (TextView) findViewById(R.id.txtAccept);
        txtAccept.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.txtAccept:
                startActivity(new Intent(getApplicationContext(), MainCategoryActivity.class));
                finish();
                break;
        }

    }
}

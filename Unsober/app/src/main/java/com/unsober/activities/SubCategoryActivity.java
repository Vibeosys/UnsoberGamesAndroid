package com.unsober.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;

import com.unsober.R;
import com.unsober.adapter.SubCategoryAdapter;

public class SubCategoryActivity extends AppCompatActivity {
    private TabLayout tab_layout;
    private int selectedTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        tab_layout = (TabLayout) findViewById(R.id.tab_layout);

        tab_layout.addTab(tab_layout.newTab().setText("Games"));
        tab_layout.addTab(tab_layout.newTab().setText("Cocktails"));
        tab_layout.addTab(tab_layout.newTab().setText("Cures"));
        tab_layout.addTab(tab_layout.newTab().setText("Search"));

        tab_layout.setTabGravity(TabLayout.GRAVITY_FILL);
        tab_layout.setSelectedTabIndicatorHeight(4);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        final SubCategoryAdapter mainActivityAdapteradapter = new SubCategoryAdapter
                (getSupportFragmentManager(), tab_layout.getTabCount());
        viewPager.setAdapter(mainActivityAdapteradapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab_layout));
        tab_layout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                selectedTab = tab.getPosition();
                viewPager.setCurrentItem(selectedTab);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}

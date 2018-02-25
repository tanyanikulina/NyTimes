package com.hp.nytimes;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.hp.nytimes.adapters.TabsAdapter;

public class MainActivity extends AppCompatActivity  {

    TabsAdapter tabsAdapter;
    ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        final ActionBar actionBar = getActionBar();
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        vp = (ViewPager) findViewById(R.id.vp);
        tabsAdapter = new TabsAdapter(getSupportFragmentManager());
        vp.setAdapter(tabsAdapter);
//        tabsAdapter.getPageTitle(0);
//        vp.setCurrentItem(0);
    }



}

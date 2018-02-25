package com.hp.nytimes.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hp.nytimes.fragments.SectionFragment;
import com.hp.nytimes.templates.Urls;

/**
 * Created by Hp on 09.02.2018.
 */

public class TabsAdapter extends FragmentPagerAdapter {

    public TabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position){
            case 0:
                fragment = SectionFragment.newInstance( Urls.url_mostemailed);
                break;
            case 1:
                fragment = SectionFragment.newInstance( Urls.url_mostshared);
                break;
            case 2:
                fragment = SectionFragment.newInstance( Urls.url_mostviewed);
                break;
            case 3:
                fragment = SectionFragment.newInstance( Urls.url_favorite);
                break;
        }

        return fragment;

    }

    @Override
    public int getCount() {
        return 4;
    }
}

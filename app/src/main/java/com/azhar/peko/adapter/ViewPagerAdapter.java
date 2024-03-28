package com.azhar.peko.adapter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.azhar.peko.model.search.ModelSearchData;
import com.azhar.peko.ui.fragment.FragmentFollowers;
import com.azhar.peko.ui.fragment.FragmentFollowing;
import com.azhar.peko.ui.fragment.FragmentRepository;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    ModelSearchData modelSearchData;

    public ViewPagerAdapter(FragmentManager fragmentManager, ModelSearchData modelSearchData) {
        super(fragmentManager);
        this.modelSearchData = modelSearchData;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("modelSearchData", modelSearchData);
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragmentFollowers();
                fragment.setArguments(bundle);
                break;
            case 1:
                fragment = new FragmentFollowing();
                fragment.setArguments(bundle);
                break;
            case 2:
                fragment = new FragmentRepository();
                fragment.setArguments(bundle);
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Followers";
                break;
            case 1:
                title = "Following";
                break;
            case 2:
                title = "Repository";
                break;
        }
        return title;
    }

}

package com.example.decoration.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.decoration.ui.fragments.Third.CircleAttentionFragment;
import com.example.decoration.ui.fragments.Third.OtherPagerFragment;


/**
 * Created by fay on 20/8/1.
 */
public class PagerFragmentAdapter extends FragmentPagerAdapter {
    private String[] mTitles;

    public PagerFragmentAdapter(FragmentManager fm, String... titles) {
        super(fm);
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return CircleAttentionFragment.newInstance();
        } else {
            return OtherPagerFragment.newInstance(mTitles[position]);
        }
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}

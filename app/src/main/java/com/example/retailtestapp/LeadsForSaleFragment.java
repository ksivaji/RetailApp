package com.example.retailtestapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.retailtestapp.utils.SlidingTabLayout;

import butterknife.InjectView;

/**
 * Created by Ssurendran on 6/25/2015.
 */
public class LeadsForSaleFragment extends BaseFragment {

    private static final int PAGE_COUNT = 2;
    @InjectView(R.id.view_pager)
    ViewPager mViewPager;
    @InjectView(R.id.sliding_tab_layout)
    SlidingTabLayout mSlidingTabLayout;

    private int[] mTabTitleIds = new int[]{R.string.new_search, R.string.saved_leads};

    public static LeadsForSaleFragment newInstance() {
        return new LeadsForSaleFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewPager.setAdapter(new LeadPagerAdapter(getChildFragmentManager()));

        mSlidingTabLayout.setCustomTabView(R.layout.tab_layout, R.id.tab_title);
        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.tab_indicator_color));
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setViewPager(mViewPager);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.leads_for_sale_fragment;
    }

    private class LeadPagerAdapter extends FragmentPagerAdapter {

        public LeadPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch (pos) {
                case 0:
                    return NewSearchFragment.newInstance();
                case 1:
                    return SavedLeadsFragment.newInstance();
                default:
                    return NewSearchFragment.newInstance();
            }

        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return getActivity().getResources().getString(mTabTitleIds[position]);
        }

    }

}

package com.example.retailtestapp;

/**
 * Created by Ssurendran on 6/25/2015.
 */
public class SavedLeadsFragment extends BaseFragment {

    public static SavedLeadsFragment newInstance() {
        return new SavedLeadsFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.saved_leads_fragment;
    }
}

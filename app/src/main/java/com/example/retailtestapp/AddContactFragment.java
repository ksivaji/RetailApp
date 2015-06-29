package com.example.retailtestapp;

/**
 * Created by Ssurendran on 6/25/2015.
 */
public class AddContactFragment extends BaseFragment {

    public static AddContactFragment newInstance(){
        return new AddContactFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.add_contact;
    }
}

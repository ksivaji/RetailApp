package com.example.retailtestapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.retailtestapp.adapter.LeadsForSaleRecyclerAdapter;

import butterknife.InjectView;

/**
 * Created by Ssurendran on 6/25/2015.
 */
public class NewSearchFragment extends BaseFragment {

    @InjectView(R.id.leads_recycler_view)
    RecyclerView mLeadsRecyclerView;

    public static NewSearchFragment newInstance() {
        return new NewSearchFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mLeadsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLeadsRecyclerView.setAdapter(new LeadsForSaleRecyclerAdapter(getActivity()));
        mLeadsRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    protected int getLayoutId() {
        return R.layout.new_search_fragment;
    }

}

package com.example.retailtestapp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.retailtestapp.adapter.MyLeadRecyclerViewAdapter;

import butterknife.InjectView;

/**
 * Created by Ssurendran on 6/25/2015.
 */
public class MyLeadFragment extends BaseFragment {

    @InjectView(R.id.recyclerView)
    RecyclerView myLeadRecyclerView;

    public static MyLeadFragment newInstance() {
        return new MyLeadFragment();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.scrollToPosition(0);

        myLeadRecyclerView.setLayoutManager(linearLayoutManager);
        myLeadRecyclerView.setHasFixedSize(true);
        MyLeadRecyclerViewAdapter adapter = new MyLeadRecyclerViewAdapter(getActivity());
        myLeadRecyclerView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.my_lead_recycler_view;
    }

}

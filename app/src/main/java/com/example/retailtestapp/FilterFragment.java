package com.example.retailtestapp;

import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TextView;

import com.example.retailtestapp.utils.OpportunitySizeEvent;

import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * Created by Ssurendran on 6/25/2015.
 */
public class FilterFragment extends BaseFragment {

    public static FilterFragment newInstance() {
        return new FilterFragment();
    }

    @OnClick ({R.id.opportunity_size_1,R.id.opportunity_size_2,R.id.opportunity_size_3})
    public void pickOpportunitySize(TextView view){
        EventBus.getDefault().post(new OpportunitySizeEvent(view.getText().toString()));
    }
    @OnClick (R.id.select_date_textview)
    public void selectExpectedCloseDate(){
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }
    protected int getLayoutId() {
        return R.layout.filter_layout;
    }
}

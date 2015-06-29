package com.example.retailtestapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.retailtestapp.utils.LaunchFilterFragmentEvent;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * Created by Ssurendran on 6/25/2015.
 */
public class CreateLeadFragment extends BaseFragment {

    @InjectView(R.id.opportunity_size_editText)
    EditText opportunitySizeEditText;
    @InjectView(R.id.expected_close_date_editText)
    EditText expectedCloseDateEditText;
    EventBus bus = EventBus.getDefault();

    public static CreateLeadFragment newInstance() {
        return new CreateLeadFragment();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /* @OnClick (R.id.load_contacts)
     void loadContacts(){
         Intent loadContactIntent = new Intent(getActivity(),ContactsActivity.class);
         getActivity().startActivity(loadContactIntent);
     }*/
   /* @OnClick(R.id.opportunity_size)
    public void getOpportunitySize() {
       bus.post(new LaunchFilterFragmentEvent());
    }*/

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.create_lead;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    /*public void onEvent(OpportunitySizeEvent event) {
        opportunitySizeEditText.setText(event.opportunitySize);

    }*/

    public void setOpportunitySize(String size){
        opportunitySizeEditText.setText(size);
    }

   /* public void onEvent(ExpectedCloseEvent event){
        DateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        String date = formatter.format(event.expectedCloseDate);
        expectedCloseDateEditText.setText(date);
    }*/

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}

package com.example.retailtestapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.retailtestapp.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Ssurendran on 6/26/2015.
 */
public class LeadsForSaleRecyclerAdapter extends RecyclerView.Adapter<LeadsForSaleRecyclerAdapter.ViewHolder> {

    private static int DUMMY_ITEM_COUNT = 30;
    private static String DUMMY_LEAD_NAME = "Demo name";
    private static String DUMMY_PRICE = "$300";
    private static String DUMMY_CONTACT_INTRO_VALUE = "Yes";
    private static String DUMMY_CUSTOMER_VERIFICATION_VALUE = "No";
    private static String DUMMY_OPPORTUNITY_SIZE = "30";
    private static float DUMMY_RATING = 4f;

    private Context mContext;

    public LeadsForSaleRecyclerAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.leads_for_sale_recycler_item_layout, null);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        itemLayoutView.setTag(viewHolder);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        if(position % 2 != 0){
            viewHolder.mListItemContainer.setBackgroundColor(mContext.getResources().getColor(R.color.light_grey));
        }else {
            viewHolder.mListItemContainer.setBackgroundColor(mContext.getResources().getColor(R.color.background_color));
        }

        viewHolder.mLeadName.setText(DUMMY_LEAD_NAME);
        viewHolder.mContactIntroValue.setText(DUMMY_CONTACT_INTRO_VALUE);
        viewHolder.mCustomerVerificationValue.setText(DUMMY_CUSTOMER_VERIFICATION_VALUE);
        viewHolder.mOpportunitySizeValue.setText(DUMMY_OPPORTUNITY_SIZE);
        viewHolder.mPrice.setText(DUMMY_PRICE);
        viewHolder.mSellerRatingBar.setRating(DUMMY_RATING);
    }

    @Override
    public int getItemCount() {
        return DUMMY_ITEM_COUNT;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.list_item_container)
        LinearLayout mListItemContainer;
        @InjectView(R.id.lead_name)
        TextView mLeadName;
        @InjectView(R.id.contact_intro_value)
        TextView mContactIntroValue;
        @InjectView(R.id.price)
        TextView mPrice;
        @InjectView(R.id.customer_verification_value)
        TextView mCustomerVerificationValue;
        @InjectView(R.id.opportunity_size_value)
        TextView mOpportunitySizeValue;
        @InjectView(R.id.seller_rating_bar)
        RatingBar mSellerRatingBar;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            ButterKnife.inject(this, itemLayoutView);
        }
    }
}

package com.example.retailtestapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.retailtestapp.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by ksivaji on 26-Jun-15.
 */
public class MyLeadRecyclerViewAdapter extends RecyclerView.Adapter<MyLeadRecyclerViewAdapter.MyLeadItemViewHolder> {
    public MyLeadRecyclerViewAdapter(Context context) {
    // Create an ArrayAdapter using the string array and a default spinner layout
        adapter = ArrayAdapter.createFromResource(context,
                R.array.lead_type, android.R.layout.simple_spinner_item);
    // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        formatter = new SimpleDateFormat("dd MMMM yyyy");
        todayDate = formatter.format(new Date());
    }

    private ArrayAdapter<CharSequence> adapter;
    DateFormat formatter;
    String todayDate;
    @Override
    public MyLeadItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View leadItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_leads_item_view, parent, false);
        return new MyLeadItemViewHolder(leadItemView);
    }

    @Override
    public void onBindViewHolder(MyLeadItemViewHolder holder, int position) {
        holder.mLeadName.setText("Demo name " + position);
        holder.spinner.setAdapter(adapter);
        holder.expectedCloseDate.setText(todayDate);
        if (position%2 ==0){
            holder.itemView.setBackgroundColor(Color.parseColor("#D0D0D0"));
        }else{
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    public static final class MyLeadItemViewHolder extends RecyclerView.ViewHolder {
        TextView mLeadName;
        Spinner spinner;
        TextView expectedCloseDate;

        public MyLeadItemViewHolder(View itemView) {
            super(itemView);
            mLeadName = (TextView) itemView.findViewById(R.id.leadName);
            spinner = (Spinner) itemView.findViewById(R.id.spinner);
            expectedCloseDate = (TextView) itemView.findViewById(R.id.date);
        }
    }
}

package com.example.retailtestapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.retailtestapp.R;
import com.example.retailtestapp.utils.DrawerItemClickEvent;
import com.readystatesoftware.viewbadger.BadgeView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;

/**
 * Created by Ssurendran on 6/27/2015.
 */
public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.SimpleViewHolder> {

    private final Context mContext;
    private List<String> mData;
    private EventBus bus = EventBus.getDefault();

    public void add(String s, int position) {
        position = position == -1 ? getItemCount() : position;
        mData.add(position, s);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        if (position < getItemCount()) {
            mData.remove(position);
            notifyItemRemoved(position);
        }
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.list_item_title)
        TextView mTitle;
        @InjectView(R.id.icon)
        ImageView mIcon;
        @InjectView(R.id.container_for_badging)
        LinearLayout mContainer;
        BadgeView badge;

        public SimpleViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }

    public SimpleAdapter(Context context, String[] data) {
        mContext = context;
        if (data != null)
            mData = new ArrayList<String>(Arrays.asList(data));
        else mData = new ArrayList<String>();
    }

    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.drawer_list_item, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, final int position) {

//        BadgeView badge = new BadgeView(mContext, holder.mContainer);
        int imageResourceId;
        holder.mTitle.setText(mData.get(position));
        holder.badge = new BadgeView(mContext, holder.mContainer);

        switch (position){
            case 9:
                imageResourceId = R.drawable.notifications;
                break;
            case 12:
                imageResourceId = R.drawable.notes;
                break;
            case 13:
                imageResourceId = R.drawable.settings;
                break;
            case 14:
                imageResourceId = R.drawable.avatar;
                break;
            case 15:
                imageResourceId = R.drawable.notifications;
                break;
            default:
                imageResourceId = R.drawable.empty_drawable;
                break;
        }

        holder.mIcon.setImageResource(imageResourceId);

        if(position == 9 || position == 11){
            holder.badge.setText("3");
            holder.badge.setTextSize(10);
            holder.badge.setBadgeMargin(30, 40);
            holder.badge.setTextColor(Color.WHITE);
            holder.badge.setBadgeBackgroundColor(mContext.getResources().getColor(R.color.red));
            holder.badge.show();
        }else{
            holder.badge.hide();
        }

        holder.mTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bus.post(new DrawerItemClickEvent(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}

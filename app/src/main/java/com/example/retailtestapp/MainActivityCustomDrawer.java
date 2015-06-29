package com.example.retailtestapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.retailtestapp.adapter.SimpleAdapter;
import com.example.retailtestapp.utils.DrawerItemClickEvent;
import com.example.retailtestapp.utils.LaunchFilterFragmentEvent;
import com.example.retailtestapp.utils.OpportunitySizeEvent;
import com.example.retailtestapp.utils.SimpleSectionedRecyclerViewAdapter;
import com.readystatesoftware.viewbadger.BadgeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * Created by Ssurendran on 6/27/2015.
 */
public class MainActivityCustomDrawer extends ActionBarActivity {

    public enum DrawerItem {
        LOGIN_SIGN_UP, BROWSE_LEADS_FOR_SALE, MY_PUBLISHED_LEADS, CREATE_LEAD, MY_LEADS, CREATE_LEADS,
        MY_IMPORTED_LEADS, MY_CONTACTS, CREATE_CONTACT, NOTIFICATIONS, MESSAGES, FOLLOW_UPS, NOTES,
        ACCOUNT_SETUP_PREFERENCES, INVITE_TO_APP, ALL_MY_ACTIVITY
    }

    private static final String LEADS_FOR_SALE_FRAGMENT_TAG = "LeadsForSaleFragment";
    private static final String CREATE_LEAD_FRAGMENT_TAG = "CreateLeadFragment";
    private static final String MY_LEAD_FRAGMENT_TAG = "MyLeadFragment";
    private static final String ADD_CONTACT_FRAGMENT_TAG = "AddContactFragment";
    private static final String FILTER_FRAGMENT_TAG = "FilterFragment";
    private static final String NOTES_FRAGMENT_TAG = "NotesFragment";
    @InjectView(R.id.fragment_container)
    FrameLayout mFragmentContainer;
    @InjectView(R.id.drawer_recycler_view)
    RecyclerView mDrawerList;
    @InjectView(R.id.drawer)
    DrawerLayout mDrawerLayout;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @InjectView(R.id.hamburger_icon)
    ImageView mHamburgerIcon;
    @InjectView(R.id.drawer_layout)
    LinearLayout mLinearDrawer;

    private EventBus bus = EventBus.getDefault();
    private SimpleAdapter mSimpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_drawer);
        ButterKnife.inject(this);
        bus.register(this);

        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setTitle(null);
        }

        BadgeView badge = new BadgeView(this, mHamburgerIcon);
        badge.setText("6");
        badge.setTextSize(10);
        badge.setTextColor(Color.WHITE);
        badge.setBadgePosition(BadgeView.POSITION_BOTTOM_RIGHT);
        badge.setBadgeBackgroundColor(getResources().getColor(R.color.red));
        badge.show();

        replaceFragment(LeadsForSaleFragment.newInstance(), LEADS_FOR_SALE_FRAGMENT_TAG);
        setToolbarTitle(getString(R.string.toolbar_title_leads_for_sale));

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        mDrawerList.setHasFixedSize(true);
        mDrawerList.setLayoutManager(new LinearLayoutManager(this));
        mDrawerList.setItemAnimator(new DefaultItemAnimator());

        mSimpleAdapter = new SimpleAdapter(this, getResources().getStringArray(R.array.drawer_items));

        List<SimpleSectionedRecyclerViewAdapter.Section> sections =
                new ArrayList<SimpleSectionedRecyclerViewAdapter.Section>();

        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(1,getResources().getString(R.string.subheader_leads_market_place)));
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(4,getResources().getString(R.string.subheader_leads_management)));
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(7,getResources().getString(R.string.subheader_contact_management)));
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(9,getResources().getString(R.string.subheader_more)));

        //Add your adapter to the sectionAdapter
        SimpleSectionedRecyclerViewAdapter.Section[] dummy = new SimpleSectionedRecyclerViewAdapter.Section[sections.size()];
        SimpleSectionedRecyclerViewAdapter mSectionedAdapter = new
                SimpleSectionedRecyclerViewAdapter(this,R.layout.drawer_sub_section,R.id.section_text,mSimpleAdapter);
        mSectionedAdapter.setSections(sections.toArray(dummy));

        //Apply this adapter to the RecyclerView
        mDrawerList.setAdapter(mSectionedAdapter);


    }

    @OnClick(R.id.hamburger_icon)
    public void onHamburgerIconClick() {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        bus.unregister(this);
        super.onDestroy();
    }

    public void onEvent(DrawerItemClickEvent event){
        onDrawerItemClick(event.mItemPosition);
    }

  /*  public void onEvent(LaunchFilterFragmentEvent event){
        FragmentManager fm = getSupportFragmentManager();
        FilterFragment fragment = (FilterFragment)fm.findFragmentByTag(FILTER_FRAGMENT_TAG);
        if (fragment == null){
            fragment = FilterFragment.newInstance();
        }
        replaceFragment(fragment, FILTER_FRAGMENT_TAG);
        setToolbarTitle(getString(R.string.toolbar_title_leads_for_sale));
    }*/
  /*  public void onEvent(OpportunitySizeEvent event) {
        FragmentManager fm = getSupportFragmentManager();
        CreateLeadFragment fragment = (CreateLeadFragment)fm.findFragmentByTag(CREATE_LEAD_FRAGMENT_TAG);
        if (fragment == null){
            fragment = CreateLeadFragment.newInstance();
        }
        replaceFragment(fragment, CREATE_LEAD_FRAGMENT_TAG);
        setToolbarTitle(getString(R.string.toolbar_title_create_lead));
        fragment.setOpportunitySize(event.opportunitySize);
    }*/

    public void replaceFragment(Fragment fragment, String tag) {
        FragmentTransaction fragTransaction = getSupportFragmentManager().beginTransaction();
        fragTransaction.replace(R.id.fragment_container, fragment, tag);
        fragTransaction.commit();

    }

    public void setToolbarTitle(String title) {
        mToolbarTitle.setText(title);
    }

    public void onDrawerItemClick(int position){
        switch(DrawerItem.values()[position]){
            case BROWSE_LEADS_FOR_SALE:
                replaceFragment(LeadsForSaleFragment.newInstance(), LEADS_FOR_SALE_FRAGMENT_TAG);
                setToolbarTitle(getString(R.string.toolbar_title_leads_for_sale));
                break;
            case CREATE_LEAD:
                replaceFragment(CreateLeadFragment.newInstance(), CREATE_LEAD_FRAGMENT_TAG);
                setToolbarTitle(getString(R.string.toolbar_title_create_lead));
                break;
            case MY_LEADS:
                replaceFragment(MyLeadFragment.newInstance(), MY_LEAD_FRAGMENT_TAG);
                setToolbarTitle(getString(R.string.toolbar_title_my_leads));
                break;
            case CREATE_CONTACT:
                replaceFragment(AddContactFragment.newInstance(), ADD_CONTACT_FRAGMENT_TAG);
                setToolbarTitle(getString(R.string.toolbar_title_add_contact));
                break;
            case NOTES:
                replaceFragment(FilterFragment.newInstance(), NOTES_FRAGMENT_TAG);
                setToolbarTitle("Filter");
            default:
                break;
        }

        mDrawerLayout.closeDrawer(mLinearDrawer);

    }

}


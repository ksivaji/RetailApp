package com.example.retailtestapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.readystatesoftware.viewbadger.BadgeView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends ActionBarActivity {

    private static final String LEADS_FOR_SALE_FRAGMENT_TAG = "LeadsForSaleFragment";
    @InjectView(R.id.fragment_container)
    FrameLayout mFragmentContainer;
    @InjectView(R.id.navigation_view)
    NavigationView mNavigationView;
    @InjectView(R.id.drawer)
    DrawerLayout mDrawerLayout;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @InjectView(R.id.hamburger_icon)
    ImageView mHamburgerIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setTitle(null);
        }

        BadgeView badge = new BadgeView(this, mHamburgerIcon);
        badge.setText("6");
        badge.setTextSize(10);
        badge.setBadgeMargin(0, 0);
        badge.setTextColor(Color.WHITE);
        badge.setBadgePosition(BadgeView.POSITION_BOTTOM_RIGHT);
        badge.setBadgeBackgroundColor(getResources().getColor(R.color.red));
        badge.show();

        replaceFragment(LeadsForSaleFragment.newInstance(), LEADS_FOR_SALE_FRAGMENT_TAG);
        setToolbarTitle(getString(R.string.toolbar_title_leads_for_sale));

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                menuItem.setChecked(true);

                switch (menuItem.getItemId()) {
                    case R.id.leads_market_place_sub_item_1:
                        replaceFragment(LeadsForSaleFragment.newInstance(), LEADS_FOR_SALE_FRAGMENT_TAG);
                        setToolbarTitle(getString(R.string.toolbar_title_leads_for_sale));
                        break;
                    default:
                        break;
                }

                mDrawerLayout.closeDrawer(mNavigationView);
                return true;
            }
        });

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
    public boolean onPrepareOptionsMenu(Menu menu) {

//        ImageView drawerIcon = new ImageView(this, null);
//
//        drawerIcon.setImageDrawable(mNavigationView.getMenu().getItem(4).getSubMenu().getItem(0).getIcon());
//
//        LinearLayout mDrawerMenuItemContainer = new LinearLayout(this);
//        mDrawerMenuItemContainer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        mDrawerMenuItemContainer.addView(drawerIcon);
//
//        BadgeView badge2 = new BadgeView(this,drawerIcon);
//        badge2.setText("6");
//        badge2.setTextSize(10);
//        badge2.setBadgeMargin(0, 0);
//        badge2.setTextColor(Color.WHITE);
//        badge2.setBadgePosition(BadgeView.POSITION_BOTTOM_RIGHT);
//        badge2.setBadgeBackgroundColor(getResources().getColor(R.color.red));
//        badge2.show();
//        mNavigationView.getMenu().getItem(4).getSubMenu().getItem(0).setActionView(mDrawerMenuItemContainer);

        return true;

    }

    public void replaceFragment(Fragment fragment, String tag) {
        FragmentTransaction fragTransaction = getSupportFragmentManager().beginTransaction();
        fragTransaction.replace(R.id.fragment_container, fragment, tag);
        fragTransaction.commit();

    }

    public void setToolbarTitle(String title) {
        mToolbarTitle.setText(title);
    }

}

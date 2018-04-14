package com.deepaks.com.delhiguide.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.deepaks.com.delhiguide.R;
import com.deepaks.com.delhiguide.bean.LocationBean;
import com.deepaks.com.delhiguide.fragment.LocationFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    // reference url https://www.tripsavvy.com/top-delhi-attractions-and-places-to-visit-1539209

    @BindView(R.id.vp_location)
    ViewPager mVpLocation;
    @BindView(R.id.tb_sliding)
    TabLayout mTbLocation;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    public static final String LOCATION_EXTRA = "location extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpActivityComponents();
        setUpViewPager();
    }

    /**
     * @author deepaks
     * @date 12 april 2018
     * @description method to set up the view pager
     */
    private void setUpViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new LocationFragment()
                , new LocationBean(getString(R.string.red_name)
                        , getString(R.string.red_location)
                        , getString(R.string.red_cost)
                        , getString(R.string.red_opening_hour)
                        , getString(R.string.red_description)
                        , R.drawable.red_fort
                ));

        adapter.addFragment(new LocationFragment()
                , new LocationBean(getString(R.string.jama_name)
                        , getString(R.string.jama_location)
                        , getString(R.string.jama_cost)
                        , getString(R.string.jama_opening_hour)
                        , getString(R.string.jama_description)
                        , R.drawable.jama_masjid
                ));

        adapter.addFragment(new LocationFragment()
                , new LocationBean(getString(R.string.akshardham_name)
                        , getString(R.string.akshardham_location)
                        , getString(R.string.akshardham_cost)
                        , getString(R.string.akshardham_opening_hour)
                        , getString(R.string.akshardham_description)
                        , R.drawable.akshardham
                ));

        adapter.addFragment(new LocationFragment()
                , new LocationBean(getString(R.string.tomb_name)
                        , getString(R.string.tomb_location)
                        , getString(R.string.tomb_cost)
                        , getString(R.string.tomb_opening_hour)
                        , getString(R.string.tomb_description)
                        , R.drawable.humayun_tomb
                ));


        mVpLocation.setOffscreenPageLimit(1);
        mVpLocation.setAdapter(adapter);
    }

    /**
     * @author deepaks
     * @date 12 april 2018
     * @description method to set up the activity components
     */
    private void setUpActivityComponents() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        mTbLocation.setupWithViewPager(mVpLocation);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<LocationBean> mLocationList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return LocationFragment.newInstance(mLocationList.get(position));
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mLocationList.get(position).getName();
        }

        public void addFragment(Fragment fragment, LocationBean locationBean) {
            mFragmentList.add(fragment);
            mLocationList.add(locationBean);
        }

    }


}

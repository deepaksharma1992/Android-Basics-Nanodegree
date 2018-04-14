package com.deepaks.com.delhiguide.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.deepaks.com.delhiguide.R;
import com.deepaks.com.delhiguide.activity.MainActivity;
import com.deepaks.com.delhiguide.bean.LocationBean;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LocationFragment extends Fragment {

    private LocationBean locationBean;

    @BindView(R.id.iv_location_image)
    ImageView mIvLocation;

    @BindView(R.id.tv_description)
    TextView mTvDescription;
    @BindView(R.id.tv_cost)
    TextView mTvCost;
    @BindView(R.id.tv_location)
    TextView mTvLocation;
    @BindView(R.id.tv_opening_hour)
    TextView mTvOpeningHour;

    /**
     * @param location the location object
     * @return the fragment instance
     * @author deepaks
     * @date 14 april 2018
     */
    public static LocationFragment newInstance(LocationBean location) {
        Bundle args = new Bundle();
        args.putParcelable(MainActivity.LOCATION_EXTRA, location);
        LocationFragment fragment = new LocationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationBean = getArguments().getParcelable(MainActivity.LOCATION_EXTRA);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location, container, false);
        setUpFragmentElements(view);
        return view;
    }

    /**
     * @param view for the fragment
     * @author deeapks
     * @date 13 april 2018
     * @description method to set up the fragment elements
     */
    private void setUpFragmentElements(View view) {
        ButterKnife.bind(this, view);
        mIvLocation.setBackgroundResource(locationBean.getImageID());
        mTvCost.setText(locationBean.getEntryCost());
        mTvDescription.setText(locationBean.getDescription());
        mTvOpeningHour.setText(locationBean.getOpeningHour());
        mTvLocation.setText(locationBean.getLocation());
    }
}

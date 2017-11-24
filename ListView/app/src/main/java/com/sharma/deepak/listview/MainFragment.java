package com.sharma.deepak.listview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by deepak on 19-07-2017.
 */

public class MainFragment extends Fragment {
    ListView lvList;
    ArrayList<ListModel> lModel = new ArrayList<>();
    SwipeRefreshLayout mSwipeLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initView(view);
        setUpAdapter();
        Log.e("fragemnt", "fragment");

        mSwipeLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Log.i("swiped", "onRefresh called from SwipeRefreshLayout");

                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                        myUpdateOperation();
                    }
                }
        );
        return view;
    }

    private void myUpdateOperation() {
        Log.e("swipe clicked", "swipe_my_operation");
        mSwipeLayout.setRefreshing(false);
    }

    private void setUpAdapter() {
        ListAdapter mAdapter = new ListAdapter(getActivity(), lModel);
        lvList.setAdapter(mAdapter);
    }

    private void initView(View view) {
        lvList = (ListView) view.findViewById(R.id.lv_list_item);
        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        lModel.add(new ListModel("hello", false));
        lModel.add(new ListModel("hello", false));
        lModel.add(new ListModel("hello", false));
        lModel.add(new ListModel("hello", false));
        lModel.add(new ListModel("hello", false));
        lModel.add(new ListModel("hello", false));
        lModel.add(new ListModel("hello", false));
        lModel.add(new ListModel("hello", false));
        lModel.add(new ListModel("hello", false));
        lModel.add(new ListModel("hello", false));
        lModel.add(new ListModel("hello", false));
        lModel.add(new ListModel("hello", false));
        lModel.add(new ListModel("hello", false));
        lModel.add(new ListModel("hello", false));
        lModel.add(new ListModel("hello", false));
        lModel.add(new ListModel("hello", false));
        lModel.add(new ListModel("hello", false));
        lModel.add(new ListModel("hello", false));
        lModel.add(new ListModel("hello", false));
        lModel.add(new ListModel("hello", false));
        lModel.add(new ListModel("hello", false));
        lModel.add(new ListModel("hello", false));
        lModel.add(new ListModel("hello", false));
        lModel.add(new ListModel("hello", false));
        lModel.add(new ListModel("hello", false));
        lModel.add(new ListModel("hello", false));
        lModel.add(new ListModel("hello", false));
        lModel.add(new ListModel("hello", false));
        lModel.add(new ListModel("hello", false));
        lModel.add(new ListModel("hello", false));

        lModel.add(new ListModel("hello", true));
        lModel.add(new ListModel("hello", true));
        lModel.add(new ListModel("hello", true));
        lModel.add(new ListModel("hello", true));
        lModel.add(new ListModel("hello", true));
        lModel.add(new ListModel("hello", true));


    }

}

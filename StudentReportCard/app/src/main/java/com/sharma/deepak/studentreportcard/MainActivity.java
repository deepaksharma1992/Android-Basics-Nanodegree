package com.sharma.deepak.studentreportcard;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.sharma.deepak.studentreportcard.adapter.ReportCardAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ReportCardAdapter.ReportCardItemInterface {
    public static final int ADD_STUDENT_ACTIVITY_REQUEST_CODE = 1;
    private List<ReportCard> reportCardList = new ArrayList<>();
    private RecyclerView mRvReportCardList;
    private ReportCardAdapter mAdapter;
    private TextView mTvNoItem;
    public static final String DETAIL_EXTRA = "Detail Extra";

    /*
    * @created by -deepak
    * @date 30-6-2017
    * @description  overriden lifecycle method
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewComponents();
    }

    /*
    * @created by -deepak
    * @date 30-6-2017
    * @description  method to initialise the ui components
    */
    private void initViewComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add);
        fab.setOnClickListener(this);
        mTvNoItem = (TextView) findViewById(R.id.tv_no_item);
        mTvNoItem.setVisibility(View.VISIBLE);
        mRvReportCardList = (RecyclerView) findViewById(R.id.rv_repoert_card_list);
        mAdapter = new ReportCardAdapter(this, reportCardList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRvReportCardList.setLayoutManager(mLayoutManager);
        mRvReportCardList.setItemAnimator(new DefaultItemAnimator());
        mRvReportCardList.setAdapter(mAdapter);
    }

       /*
        * @created by -deepak
        * @date 30-6-2017
        * @description  method to perform the click event on view components
        */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fab_add:
                Intent addIntent = new Intent(this, AddStudentActivity.class);
                startActivityForResult(addIntent, ADD_STUDENT_ACTIVITY_REQUEST_CODE);
        }
    }
        /*
        * @created by -deepak
        * @date 30-6-2017
        * @description  method to receive the result from Add student activity
        */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_STUDENT_ACTIVITY_REQUEST_CODE) {
            mTvNoItem.setVisibility(View.INVISIBLE);
            ReportCard reportCard = (ReportCard) data.getSerializableExtra(AddStudentActivity.STUDENT_EXTRA_DATA);
            reportCardList.add(reportCard);
            mAdapter.notifyDataSetChanged();
        }

    }
    /*
        * @created by -deepak
        * @date 30-6-2017
        * @description  method to start thr activity to launch report card detail activity
        */
    @Override
    public void OnReportCardItemClick(int position) {
        Intent detailIntent = new Intent(this, StudentDetailActivity.class);
        detailIntent.putExtra(DETAIL_EXTRA, reportCardList.get(position));
        startActivity(detailIntent);
    }
}

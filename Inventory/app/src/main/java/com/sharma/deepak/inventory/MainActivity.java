package com.sharma.deepak.inventory;

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

import com.sharma.deepak.inventory.adapter.ProductAdapter;
import com.sharma.deepak.inventory.data.InventoryDbHelper;
import com.sharma.deepak.inventory.model.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ProductAdapter.ReportCardItemInterface {
    private List<Product> productList = new ArrayList<>();
    private RecyclerView mRvProductList;
    private ProductAdapter mAdapter;
    private TextView mTvNoItem;
    private InventoryDbHelper mDbHelper;
    public static final String DETAIL_EXTRA = "detail extra";

    /*
    * @created by -deepak
    * @date 17/7/2017
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
    * @date 17/7/2017
    * @description  method onResume activity lifecycle method
    */
    @Override
    protected void onResume() {
        super.onResume();
        updateList();
    }

    /*
    * @created by -deepak
    * @date 17/7/2017
    * @description  method to update the list
    */
    private void updateList() {
       productList = mDbHelper.getAllProducts();
        mAdapter.swap(productList);
        if(productList.size()>0){
            mTvNoItem.setVisibility(View.INVISIBLE);
        }else
            mTvNoItem.setVisibility(View.VISIBLE);
    }

    /*
    * @created by -deepak
    * @date 17/7/2017
    * @description  method to initialise the ui components
    */
    private void initViewComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDbHelper = new InventoryDbHelper(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add);
        fab.setOnClickListener(this);
        mTvNoItem = (TextView) findViewById(R.id.tv_no_item);
        mRvProductList = (RecyclerView) findViewById(R.id.rv_report_card_list);
        mAdapter = new ProductAdapter(this, this, productList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRvProductList.setLayoutManager(mLayoutManager);
        mRvProductList.setItemAnimator(new DefaultItemAnimator());
        mRvProductList.setAdapter(mAdapter);
    }

    /*
    * @created by -deepak
    * @date 17/7/2017
    * @description  method to perform the click event on view components
    */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fab_add:
                Intent addIntent = new Intent(this, AddProductActivity.class);
                startActivity(addIntent);
        }
    }

    /*
    * @created by -deepak
    * @date 17/7/2017
    * @description  method to open detail activity on item clicked
    */
    @Override
    public void OnReportCardItemClick(int position) {
        Intent detailIntent = new Intent(this, ProductDetailActivity.class);
        detailIntent.putExtra(DETAIL_EXTRA, productList.get(position));
        startActivity(detailIntent);
    }

    /*
    * @created by -deepak
    * @date 17/7/2017
    * @description  method to update the list on sale button click
    */
    @Override
    public void OnSaleButtonClicked() {
        updateList();
    }
}

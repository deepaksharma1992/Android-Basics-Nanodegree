package com.sharma.deepak.inventory;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sharma.deepak.inventory.data.InventoryDbHelper;
import com.sharma.deepak.inventory.model.Product;

import java.io.File;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private Product mProduct;
    private TextView mTvName, mTvPrice, mTvDate, mTvQuantity;
    private InventoryDbHelper mDbHelper;
    private ImageView mIvImage;

    /*
    * @created by -deepak
    * @date 17/7/2017
    * @description  overriden lifecycle method
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        initViewElements();
        mProduct = (Product) getIntent().getSerializableExtra(MainActivity.DETAIL_EXTRA);
        setDataToViews(mProduct);
    }

    /*
    * @created by -deepak
    * @date 17/7/2017
    * @description method to set data to the views
    */
    private void setDataToViews(Product product) {
        mTvDate.setText(product.getPurchaseDate());
        mTvName.setText(product.getProductName());
        mTvPrice.setText("$" + product.getProductPrice() + "");
        mTvQuantity.setText(product.getProductQuantity() + "");
        Log.e("address",mProduct.getImageAddress());
        String filepath= Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+mProduct.getImageAddress();
        File imgFile = new File(filepath);
        if (imgFile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            mIvImage.setImageBitmap(myBitmap);
        }
    }

    /*
    * @created by -deepak
    * @date 17/7/2017
    * @description  method to initialise view elements
    */
    private void initViewElements() {
        mTvDate = (TextView) findViewById(R.id.tv_product_date);
        mTvPrice = (TextView) findViewById(R.id.tv_product_price);
        mTvName = (TextView) findViewById(R.id.tv_product_name);
        mTvQuantity = (TextView) findViewById(R.id.tv_quantity);
        mIvImage = (ImageView) findViewById(R.id.iv_productImage);
        Button orderBtn = (Button) findViewById(R.id.btn_order);
        orderBtn.setOnClickListener(this);
        ImageView plusBtn = (ImageView) findViewById(R.id.iv_plus);
        plusBtn.setOnClickListener(this);
        ImageView minusBtn = (ImageView) findViewById(R.id.iv_minus);
        minusBtn.setOnClickListener(this);
        mDbHelper = new InventoryDbHelper(this);
    }

    /*
    * @created by -deepak
    * @date 17/7/2017
    * @description  overriden menu method to get the menu
    */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /*
    * @created by -deepak
    * @date 17/7/2017
    * @description  overriden menu method to handle the menu item clicked
    */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                showDeleteDialog();

        }
        return super.onOptionsItemSelected(item);
    }

    /*
    * @created by -deepak
    * @date 17/7/2017
    * @description  method to prompt the dialog
    */
    private void showDeleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.delete_product));
        builder.setMessage(getString(R.string.will_delete_record));
        //Yes Button
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ProductDetailActivity.this, getString(R.string.deleted), Toast.LENGTH_SHORT).show();
                mDbHelper.deleteProduct(mProduct.getProductId());
                dialog.dismiss();
                finish();
            }
        });
        //No Button
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    /*
    * @created by -deepak
    * @date 17/7/2017
    * @description  overriden method to handle the UI click events
    */
    @Override
    public void onClick(View v) {
        int currentQuantity = Integer.parseInt(mTvQuantity.getText().toString());
        switch (v.getId()) {
            case R.id.iv_plus:
                ++currentQuantity;
                mTvQuantity.setText(currentQuantity + "");
                mDbHelper.updateProductQuantity(mProduct.getProductId(), Integer.parseInt(mTvQuantity.getText().toString()));
                break;
            case R.id.iv_minus:
                if (validateQuantity(currentQuantity)) {
                    --currentQuantity;
                    mTvQuantity.setText(currentQuantity + "");
                    mDbHelper.updateProductQuantity(mProduct.getProductId(), Integer.parseInt(mTvQuantity.getText().toString()));
                } else
                    Toast.makeText(this, getString(R.string.not_less_than_zero), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_order:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + mProduct.getPhone()));
                startActivity(intent);
                break;
        }
    }

    /*
    * @created by -deepak
    * @date 17/7/2017
    * @description  method to validate the quantity
    */
    private boolean validateQuantity(int currentQuantity) {
        if (currentQuantity > 0) {
            return true;
        } else {
            return false;
        }
    }


}

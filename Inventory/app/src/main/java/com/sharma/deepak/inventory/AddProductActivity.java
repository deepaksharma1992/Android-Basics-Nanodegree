package com.sharma.deepak.inventory;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.sharma.deepak.inventory.data.InventoryDbHelper;
import com.sharma.deepak.inventory.model.Product;
import com.sharma.deepak.inventory.utility.Utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddProductActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEtName, mEtPrice, mEtQuantity, mEtDate, mEtPhone;
    private InventoryDbHelper mDbHelper;
    private Calendar myCalendar = Calendar.getInstance();
    private String imageAddress, userChoosenTask;
    private ImageView mIvImage;
    private static final int REQUEST_CAMERA = 0;
    private static final String JPG = ".jpg";

    /*
   * @created by -deepak
   * @date 17/7/2017
   * @description  overriden lifecycle method
   */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        initViewComponents();
    }

    /*
    * @created by -deepak
    * @date 17/7/2017
    * @description  method to initialise the ui components
    */
    private void initViewComponents() {
        Button submitButton = (Button) findViewById(R.id.btn_submit);
        submitButton.setOnClickListener(this);
        mEtName = (EditText) findViewById(R.id.et_product_name);
        mEtPrice = (EditText) findViewById(R.id.et_product_price);
        mEtQuantity = (EditText) findViewById(R.id.et_product_quantity);
        mEtDate = (EditText) findViewById(R.id.et_purchase_date);
        mEtPhone = (EditText) findViewById(R.id.et_phone_number);
        mIvImage = (ImageView) findViewById(R.id.iv_productImage);
        mEtDate.setOnClickListener(this);
        mDbHelper = new InventoryDbHelper(this);
        FloatingActionButton addBtn = (FloatingActionButton) findViewById(R.id.fab_add);
        addBtn.setOnClickListener(this);
    }

    /*
    * @created by -deepak
    * @date 17/7/2017
    * @description  method to perform the click event on view components
    */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                if (validateFields()) {
                    String name = mEtName.getEditableText().toString();
                    int price = Integer.parseInt(mEtPrice.getEditableText().toString());
                    int quantity = Integer.parseInt(mEtQuantity.getEditableText().toString());
                    String date = mEtDate.getEditableText().toString();
                    String phone = mEtPhone.getEditableText().toString();
                    Product product = new Product(name, date, price, quantity, phone, imageAddress);
                    mDbHelper.addProduct(product);
                    finish();
                }
                break;
            case R.id.et_purchase_date:
                new DatePickerDialog(this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.fab_add:
                selectImage();
                break;
        }
    }

    /*
    * @created by -deepak
    * @date 17/7/2017
    * @description  method to validate all the edit text i activity
    */
    private boolean validateFields() {
        if (TextUtils.isEmpty(imageAddress)) {
            Toast.makeText(this, R.string.image_select, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(mEtName.getText().toString())) {
            Toast.makeText(this, R.string.name_error, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(mEtPrice.getText().toString())) {
            Toast.makeText(this, R.string.price_error, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Integer.parseInt(mEtPrice.getText().toString()) < 1) {
            Toast.makeText(this, R.string.price_value_error, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(mEtQuantity.getEditableText().toString())) {
            Toast.makeText(this, R.string.quantity_error, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Integer.parseInt(mEtQuantity.getEditableText().toString()) < 1) {
            Toast.makeText(this, R.string.quantity_value_error, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(mEtPhone.getText().toString())) {
            Toast.makeText(this, R.string.phone_error, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(mEtDate.getText().toString())) {
            Toast.makeText(this, R.string.date_error, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /*
    * @created by -deepak
    * @date 17/7/2017
    * @description  method to update the date text
    */
    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        mEtDate.setText(sdf.format(myCalendar.getTime()));
    }

    // date picker dialog class
    private DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        imageAddress = System.currentTimeMillis() + JPG;
        File destination = new File(Environment.getExternalStorageDirectory(), imageAddress);

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mIvImage.setImageBitmap(thumbnail);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChoosenTask.equals(getString(R.string.take_photo))) {
                        cameraIntent();
                    } else {
                        //code for deny
                    }
                    break;
                }
        }
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }


    private void selectImage() {
        final CharSequence[] items = {getString(R.string.take_photo),
                getString(R.string.cancel)};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.add_photo));
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(AddProductActivity.this);

                if (items[item].equals(getString(R.string.take_photo))) {
                    userChoosenTask = getString(R.string.take_photo);
                    if (result)
                        cameraIntent();

                } else if (items[item].equals(getString(R.string.cancel))) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
}

package com.sharma.deepak.inventory.data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sharma.deepak.inventory.model.Product;

import java.util.ArrayList;
import java.util.List;


public class InventoryDbHelper extends SQLiteOpenHelper {

    // sql string to create table
    private static final String SQL_CREATE_TABLE_TASKS = String.format("create table %s"
                    + " (%s integer primary key autoincrement, %s text, %s integer, %s integer, %s text, %s text, %s text)",
            InventoryContract.TABLE_PRODUCT,
            InventoryContract.InventoryTable._ID,
            InventoryContract.InventoryTable.COLUMN_NAME,
            InventoryContract.InventoryTable.COLUMN_PRICE,
            InventoryContract.InventoryTable.COLUMN_QUANTITY,
            InventoryContract.InventoryTable.COLUMN_DATE,
            InventoryContract.InventoryTable.COLUMN_PHONE,
            InventoryContract.InventoryTable.COLUMN_IMAGE
    );

    /*
    * constructor for setting the database
    * created by deepak on 16-7-2017
     */
    public InventoryDbHelper(Context context) {
        super(context, InventoryContract.DB_NAME, null, InventoryContract.DB_VERSION);
    }

    /*
    * method for creating the database
    * created by deepak on 16-7-2017
    */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_TASKS);
    }

    /*
    * method  for upgarading the database
    * created by deepak on 16-7-2017
    */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + InventoryContract.TABLE_PRODUCT);
        onCreate(db);
    }

    /*
    * method  for adding a new product to database
    * created by deepak on 16-7-2017
    */
    public void addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(InventoryContract.InventoryTable.COLUMN_NAME, product.getProductName());
        values.put(InventoryContract.InventoryTable.COLUMN_PRICE, product.getProductPrice());
        values.put(InventoryContract.InventoryTable.COLUMN_QUANTITY, product.getProductQuantity());
        values.put(InventoryContract.InventoryTable.COLUMN_DATE, product.getPurchaseDate());
        values.put(InventoryContract.InventoryTable.COLUMN_PHONE, product.getPhone());
        values.put(InventoryContract.InventoryTable.COLUMN_IMAGE, product.getImageAddress());
        // Inserting Row
        db.insert(InventoryContract.TABLE_PRODUCT, null, values);
        db.close(); // Closing database connection
    }

    /*
    * method  for getting all product from database
    * created by deepak on 16-7-2017
    */
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + InventoryContract.TABLE_PRODUCT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Product product = new Product(cursor.getInt(cursor.getColumnIndex(InventoryContract.InventoryTable._ID))
                        , cursor.getString(cursor.getColumnIndex(InventoryContract.InventoryTable.COLUMN_NAME))
                        , cursor.getString(cursor.getColumnIndex(InventoryContract.InventoryTable.COLUMN_DATE))
                        , cursor.getInt(cursor.getColumnIndex(InventoryContract.InventoryTable.COLUMN_PRICE))
                        , cursor.getInt(cursor.getColumnIndex(InventoryContract.InventoryTable.COLUMN_QUANTITY))
                        , cursor.getString(cursor.getColumnIndex(InventoryContract.InventoryTable.COLUMN_PHONE))
                        , cursor.getString(cursor.getColumnIndex(InventoryContract.InventoryTable.COLUMN_IMAGE))
                );

                productList.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return product list
        return productList;
    }

    /*
    * method  for deleting a product from database
    * created by deepak on 16-7-2017
    */
    public void deleteProduct(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(InventoryContract.TABLE_PRODUCT, InventoryContract.InventoryTable._ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }

    /*
    * method  for updating a product quantity on database
    * created by deepak on 16-7-2017
    */
    public int updateProductQuantity(int id, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(InventoryContract.InventoryTable.COLUMN_QUANTITY, quantity);
        return db.update(InventoryContract.TABLE_PRODUCT, cv, InventoryContract.InventoryTable._ID + " = ?",
                new String[]{String.valueOf(id)});
    }
}

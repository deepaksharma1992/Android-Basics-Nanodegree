package com.sharma.deepak.inventory.data;

import android.provider.BaseColumns;

/**
 * Created by deepak on 16-07-2017.
 */

public class InventoryContract {
    public static final String DB_NAME = "inventory_db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_PRODUCT = "product_table";

    // inner class for table name
    public static class InventoryTable implements BaseColumns {
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_IMAGE = "image";
    }
}

package com.sharma.deepak.habittracker.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by deepak on 7/5/2017.
 */

public class HabitDbHelper extends SQLiteOpenHelper {
    /*
    * @author deepak
    * @created 7/5/2017
    * @description constructor to initialize the database class
    * */
    public HabitDbHelper(Context context) {
        super(context, HabitTrackerContract.DBName, null, HabitTrackerContract.DBVersion);
    }

    /*
    * @author deepak
    * @created 7/5/2017
    * @description on create method to create the database tables
    * */
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_HABIT_TABLE = "CREATE TABLE " + HabitTrackerContract.HabitTable.TABLE_NAME
                + "(" + HabitTrackerContract.HabitTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + HabitTrackerContract.HabitTable.COLUMN_HABIT + " STRING,"
                + HabitTrackerContract.HabitTable.COLUMN_HABIT_TYPE + " INTEGER NOT NULL,"
                + HabitTrackerContract.HabitTable.COLUMN_HABIT_STATUS + " INTEGER DEFAULT " + HabitTrackerContract.HabitTable.NOT_COMPLETED
                + ")";

        db.execSQL(CREATE_HABIT_TABLE);

    }

    /*
    * @author deepak
    * @created 7/2/2017
    * @description on upgare method to upgrade the database column
    * */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /*
    * @author deepak
    * @created 7/5/2017
    * @description method to insert the values in the habit table
    * */
    public void insertHabit(String habitName, int habitType, int habitStatus) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HabitTrackerContract.HabitTable.COLUMN_HABIT, habitName);
        values.put(HabitTrackerContract.HabitTable.COLUMN_HABIT_TYPE, habitType);
        values.put(HabitTrackerContract.HabitTable.COLUMN_HABIT_STATUS, habitStatus);
        db.insert(HabitTrackerContract.HabitTable.TABLE_NAME, null, values);
    }

    /*
    * @author deepak
    * @created 7/5/2017
    * @description method to query  the habit data returning the cursor
    * */
    public Cursor queryHabit(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                HabitTrackerContract.HabitTable.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                groupBy,
                having,
                orderBy
        );
        return cursor;
    }
}

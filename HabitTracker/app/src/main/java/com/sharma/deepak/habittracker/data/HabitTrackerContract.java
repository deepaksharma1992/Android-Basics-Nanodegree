package com.sharma.deepak.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by deepak on 7/5/2017.
 */

public class HabitTrackerContract {
    public static final String DBName = "habit_tracker_db";
    public static final int DBVersion = 1;

    // class to create all the habit table constants
    public class HabitTable implements BaseColumns {
        public static final String TABLE_NAME = "habit_table";

        //columns for habit tracker
        public static final String COLUMN_ID = BaseColumns._ID;
        public static final String COLUMN_HABIT = "habit";
        public static final String COLUMN_HABIT_TYPE = "habit_type";
        public static final String COLUMN_HABIT_STATUS = "habit_status";

        // constants to track habit type
        public static final int DAILY_HABIT = 0;
        public static final int WEEKLY_HABIT = 1;
        public static final int MONTHLY_HABIT = 2;

        //constant to track the habit completion status
        public static final int NOT_COMPLETED = 0;
        public static final int COMPLETED = 1;
    }
}

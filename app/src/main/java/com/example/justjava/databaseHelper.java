package com.example.justjava;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

public final class databaseHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.name + " TEXT," +
                    FeedEntry.chocolate + " BOOL," +
                    FeedEntry.whippedCreme + " BOOL," +
                    FeedEntry.total + " INT," +
                    FeedEntry.quantity + " INT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + databaseHelper.FeedEntry.TABLE_NAME;
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
//    private databaseHelper() {}
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Android.db";
    public databaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "Orders";
        public static final String name = "name";
        public static final String quantity = "quantity";
        public static final String whippedCreme = "whippedCreme";
        public static final String chocolate = "chocolate";
        public static final String total = "total";
    }




    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
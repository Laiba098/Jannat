package com.example.onlinetourism;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "OnlineTourism.db";
    private static final String CREATE_TBL_SERVICESEEKER = "CREATE TABLE "
            + DatabaseContract.ServiceSeeker.TABLE_NAME + " ("
            + DatabaseContract.ServiceSeeker.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DatabaseContract.ServiceSeeker.COL_NAME + " TEXT NOT NULL, "
            + DatabaseContract.ServiceSeeker.COL_CONTACT + " TEXT,"
            + DatabaseContract.ServiceSeeker.COL_LOCATION + " TEXT,"
            + DatabaseContract.ServiceSeeker.COL_EMAIL + " TEXT,"
            + DatabaseContract.ServiceSeeker.COL_PASSWORD+ " TEXT )";

    private static final String CREATE_TBL_SERVICEPROVIDER = "CREATE TABLE "
            + DatabaseContract.ServiceProvider.TABLE_NAME + " ("
            + DatabaseContract.ServiceProvider.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DatabaseContract.ServiceProvider.COL_NAME + " TEXT NOT NULL, "
            + DatabaseContract.ServiceProvider.COL_CONTACT + " TEXT,"
            + DatabaseContract.ServiceProvider.COL_LOCATION + " TEXT,"
            + DatabaseContract.ServiceProvider.COL_EMAIL + " TEXT,"
            + DatabaseContract.ServiceProvider.COL_PASSWORD+ " TEXT )";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TBL_SERVICEPROVIDER);
        db.execSQL(CREATE_TBL_SERVICESEEKER);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
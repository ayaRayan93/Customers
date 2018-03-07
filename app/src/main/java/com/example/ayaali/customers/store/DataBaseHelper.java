package com.example.ayaali.customers.store;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by AyaAli on 07/03/2018.
 */


public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "appDB";
    private static final int DATABASE_VERSION = 1;

    public DataBaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        CustomerTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        CustomerTable.onUpgrade(db,oldVersion,newVersion);
    }
}

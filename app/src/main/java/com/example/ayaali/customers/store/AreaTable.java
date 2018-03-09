package com.example.ayaali.customers.store;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by AyaAli on 09/03/2018.
 */

public class AreaTable {

    // Contacts table name
    public static final String AreaTable ="area" ;
    // Customer Table Columns names
    public static final String AreaId ="SalesZoneCode" ;
    public static final String AreaName ="SalesZoneName" ;

    public static String CREATE_Database_TABLE = "CREATE TABLE " + AreaTable + "("
            + AreaId + " TEXT PRIMARY KEY,"
            + AreaName + " TEXT" + ")";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_Database_TABLE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {

        database.execSQL("DROP TABLE IF EXISTS " + AreaTable);
        onCreate(database);
    }
}

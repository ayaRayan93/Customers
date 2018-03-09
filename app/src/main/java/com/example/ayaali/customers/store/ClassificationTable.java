package com.example.ayaali.customers.store;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by AyaAli on 09/03/2018.
 */

public class ClassificationTable {
    // Contacts table name
    public static final String ClassificationTable ="classification" ;
    // Customer Table Columns names
    public static final String ClassificationId ="CustomerClassCode" ;
    public static final String ClassificationName ="CustomerClassName" ;

    public static String CREATE_Database_TABLE = "CREATE TABLE " + ClassificationTable + "("
            + ClassificationId + " TEXT PRIMARY KEY,"
            + ClassificationName + " TEXT" + ")";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_Database_TABLE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {

        database.execSQL("DROP TABLE IF EXISTS " + ClassificationTable);
        onCreate(database);
    }
}

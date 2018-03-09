package com.example.ayaali.customers.store;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.example.ayaali.customers.model.Area;
import com.example.ayaali.customers.model.Classification;
import com.example.ayaali.customers.model.Customer;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by AyaAli on 09/03/2018.
 */

public class ReadDataFromDB {

    public static List<Customer> getAllCustomerForSalesPerson(Context context)
    {
        String[] projection={CustomerTable.CustomerCode,
                CustomerTable.CustName,
                CustomerTable.StreetAra,
                CustomerTable.Classification,
                CustomerTable.PersonToConnect,
                CustomerTable.Tel,
                CustomerTable.TAXID,
                CustomerTable.SaleAreaCode,
                CustomerTable.Flag,
        };

        List<Customer> customerList = new ArrayList<>();
// Select All Query
        String selectQuery = "SELECT * FROM " + CustomerTable.CustomerTable;
        CustomerContentProvider  movieContentProvider=new CustomerContentProvider(context);
        //SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = movieContentProvider.query(CustomerContentProvider.CONTENT_URI,projection,selectQuery,null,null); //db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Customer customer = new Customer();
                customer.setCustomerCode(cursor.getString(0));
                customer.setCustName(cursor.getString(1));
                customer.setStreetAra(cursor.getString(2));
                customer.setClassification(cursor.getString(3));
                customer.setPersonToConnect(cursor.getString(4));
                customer.setTel(cursor.getString(5));
                customer.setTAXID(cursor.getString(6));
                customer.setSaleAreaCode(cursor.getString(7));
                customer.setFlag(cursor.getString(8));
// Adding contact to list
                customerList.add(customer);
            } while (cursor.moveToNext());
        }
// return contact list
        return customerList;

    }

    public static List<Classification> getAllCustomerClassification()
    {
        String[] projection={ClassificationTable.ClassificationId,
                ClassificationTable.ClassificationName,
        };

        List<Classification> ClassificationList = new ArrayList<>();
// Select All Query
        String selectQuery = "SELECT * FROM " + ClassificationTable.ClassificationTable;
        ClassificationContentProvider  ClassificationContentProvider=new ClassificationContentProvider();
        //SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = ClassificationContentProvider.query(ClassificationContentProvider.CONTENT_URI,projection,selectQuery,null,null); //db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Classification classification = new Classification();
                classification.setId(cursor.getString(0));
                classification.setName(cursor.getString(1));
// Adding contact to list
                ClassificationList.add(classification);
            } while (cursor.moveToNext());
        }
// return contact list
        return ClassificationList;

    }

    public static List<Area> getAllCustomerArea(Context context)
    {
        String[] projection={AreaTable.AreaId,
                AreaTable.AreaName,
        };

        List<Area> AreaList = new ArrayList<>();
// Select All Query
        String selectQuery = "SELECT * FROM " + AreaTable.AreaTable;
        AreaContentProvider  areaContentProvider=new AreaContentProvider(context);
        //SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = areaContentProvider.query(areaContentProvider.CONTENT_URI,projection,selectQuery,null,null); //db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Area area = new Area();
                area.setId(cursor.getString(0));
                area.setName(cursor.getString(1));
// Adding contact to list
                AreaList.add(area);
            } while (cursor.moveToNext());
        }
// return contact list
        return AreaList;

    }
}

package com.example.ayaali.customers.json;

import com.example.ayaali.customers.model.Customer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AyaAli on 05/03/2018.
 */

public class Parser {

    public static List<Customer> parseStringToJson(String data) {
        List<Customer> modelCustomer;

        try {
            JSONArray jsonArray = new JSONArray(data);
            modelCustomer = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject employeeJsonObject = jsonArray.getJSONObject(i);
                String CustomerCode = employeeJsonObject.optString("CustomerCode");
                String CustName =employeeJsonObject.optString("CustName");
                String CustomerNameLat = employeeJsonObject.optString("CustomerNameLat");
                String StreetAra = employeeJsonObject.optString("StreetAra");
                String Classification = employeeJsonObject.optString("Classification");
                String PersonToConnect = employeeJsonObject.optString("PersonToConnect");
                String Tel = employeeJsonObject.optString("Tel");
                String TAXID = employeeJsonObject.optString("TAXID");
                String SaleAreaCode = employeeJsonObject.optString("SaleAreaCode");
                String Notes = employeeJsonObject.optString("Notes");
                String SalesRepCode = employeeJsonObject.optString("SalesRepCode");
                String CodeList = employeeJsonObject.optString("CodeList");
                String NotActive = employeeJsonObject.optString("NotActive");


                Customer customer = new Customer();
                customer.setCustomerCode(CustomerCode);
                customer.setCustName(CustName);
                customer.setCustomerNameLat(CustomerNameLat);
                customer.setStreetAra(StreetAra);
                customer.setClassification(Classification);

                customer.setPersonToConnect(PersonToConnect);
                customer.setTel(Tel);
                customer.setTAXID(TAXID);

                customer.setSaleAreaCode(SaleAreaCode);
                customer.setNotes(Notes);
                customer.setSalesRepCode(SalesRepCode);
                customer.setCodeList(CodeList);
                customer.setNotActive(NotActive);

                modelCustomer.add(customer);



            }

            return modelCustomer;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }
}

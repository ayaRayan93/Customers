package com.example.ayaali.customers.activity;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ayaali.customers.R;
import com.example.ayaali.customers.model.Area;
import com.example.ayaali.customers.model.Classification;
import com.example.ayaali.customers.model.Customer;
import com.example.ayaali.customers.store.CustomerContentProvider;
import com.example.ayaali.customers.store.CustomerTable;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
;import static com.example.ayaali.customers.store.ReadDataFromDB.getAllCustomerArea;
import static com.example.ayaali.customers.store.ReadDataFromDB.getAllCustomerClassification;

public class AddNewCustomer extends AppCompatActivity {

    @BindView(R.id.Code)EditText Code;
    @BindView(R.id.Name)EditText Name;
    @BindView(R.id.Address)EditText Address;
    @BindView(R.id.ContactPerson)EditText ContactPerson;
    @BindView(R.id.Mobile)EditText Mobile;
    @BindView(R.id.TaxNumber)EditText TaxNumber;


    @BindView(R.id.spinnerClassification)Spinner spinnerClassification;
    @BindView(R.id.spinnerArea)Spinner spinnerArea;
    List<Classification> dataClass=null;
    List<Area> dataArea=null;

    HashMap<Integer,String> spinnerMap;
    HashMap<Integer,String> spinnerMapArea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_customer);
        ButterKnife.bind(this);
        Button addCustomer=(Button)findViewById(R.id.save);
        addCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewCustomer();
                Toast.makeText(AddNewCustomer.this, "Done", Toast.LENGTH_SHORT).show();
            }
        });

        dataClass= getAllCustomerClassification();
        String[] spinnerArray = new String[dataClass.size()];
        spinnerMap = new HashMap<Integer, String>();
        for (int i = 0; i < dataClass.size(); i++)
        {
            spinnerMap.put(i,dataClass.get(i).getId());
            spinnerArray[i] = dataClass.get(i).getName();
        }
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClassification.setAdapter(adapter);


        dataArea=getAllCustomerArea(this);
        String[] spinnerArrayArea = new String[dataArea.size()];
        spinnerMapArea = new HashMap<Integer, String>();
        for (int i = 0; i < dataArea.size(); i++)
        {
            spinnerMapArea.put(i,dataArea.get(i).getId());
            spinnerArrayArea[i] = dataArea.get(i).getName();
        }
        ArrayAdapter<String>  adapter1 =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, spinnerArrayArea);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArea.setAdapter(adapter1);

    }
    public void addNewCustomer()
    {
        String code=Code.getText().toString();
        String name=Name.getText().toString();
        String address=Address.getText().toString();
        String contactPerson=ContactPerson.getText().toString();
        String mobile=Mobile.getText().toString();
        String taxNumber=TaxNumber.getText().toString();
        String classification=spinnerMap.get(spinnerClassification.getSelectedItemPosition());
        String area=spinnerMapArea.get(spinnerArea.getSelectedItemPosition());
        Customer newCustomer=new Customer(code,name,address,classification,contactPerson,mobile,taxNumber,area);

        // SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CustomerTable.CustomerCode, newCustomer.getCustomerCode());
        values.put(CustomerTable.CustName, newCustomer.getCustName());
        values.put(CustomerTable.StreetAra,newCustomer.getStreetAra());
        values.put(CustomerTable.Classification,newCustomer.getClassification());
        values.put(CustomerTable.PersonToConnect,newCustomer.getPersonToConnect());
        values.put(CustomerTable.Tel,newCustomer.getTel());
        values.put(CustomerTable.TAXID,newCustomer.getTAXID());
        values.put(CustomerTable.Flag,"0");//new not uploaded yet
        // Inserting Row
        //db.insert(TABLE_MOVIES, null, values);
        //db.close(); // Closing database connection
        CustomerContentProvider moviesContentProvider=new CustomerContentProvider(this);
        moviesContentProvider.insert(CustomerContentProvider.CONTENT_URI_add,values);

    }


}

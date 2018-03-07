package com.example.ayaali.customers.activity;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ayaali.customers.R;
import com.example.ayaali.customers.model.Customer;
import com.example.ayaali.customers.store.CustomerContentProvider;
import com.example.ayaali.customers.store.CustomerTable;

import butterknife.BindView;
import butterknife.ButterKnife;
;
import static java.security.AccessController.getContext;

public class AddNewCustomer extends AppCompatActivity {

    @BindView(R.id.Code)EditText Code;
    @BindView(R.id.Name)EditText Name;
    @BindView(R.id.Address)EditText Address;
    @BindView(R.id.ContactPerson)EditText ContactPerson;
    @BindView(R.id.Mobile)EditText Mobile;
    @BindView(R.id.TaxNumber)EditText TaxNumber;
    @BindView(R.id.Classification)EditText Classification;
    @BindView(R.id.Area)EditText Area;
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
    }
    public void addNewCustomer()
    {
        String code=Code.getText().toString();
        String name=Name.getText().toString();
        String address=Address.getText().toString();
        String contactPerson=ContactPerson.getText().toString();
        String mobile=Mobile.getText().toString();
        String taxNumber=TaxNumber.getText().toString();
        String classification=Classification.getText().toString();
        String area=Area.getText().toString();
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
        // Inserting Row
        //db.insert(TABLE_MOVIES, null, values);
        //db.close(); // Closing database connection
        CustomerContentProvider moviesContentProvider=new CustomerContentProvider(this);
        moviesContentProvider.insert(CustomerContentProvider.CONTENT_URI_add,values);

    }
}

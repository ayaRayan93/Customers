package com.example.ayaali.customers.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ayaali.customers.R;
import com.example.ayaali.customers.adapter.CustomerAdapter;
import com.example.ayaali.customers.app.AppController;
import com.example.ayaali.customers.json.Parser;
import com.example.ayaali.customers.model.Customer;
import com.example.ayaali.customers.store.CustomerContentProvider;
import com.example.ayaali.customers.store.CustomerTable;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;


    @BindView(R.id.swipeRefresh) SwipeRefreshLayout mSwipeRefreshLayout;

    private Menu menu;
    protected CustomerAdapter itemAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected List<Customer> dataSet;
    private int flag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button AddNew=(Button)findViewById(R.id.AddNew);
        AddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("AddNewCustomer");
                startActivity(intent);
            }
        });
        dataSet = new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mSwipeRefreshLayout=(SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        mRecyclerView.setHasFixedSize(true);
        itemAdapter = new CustomerAdapter(this,dataSet);
        mRecyclerView.setAdapter(itemAdapter);

        // Set the color scheme of the SwipeRefreshLayout by providing 4 color resource ids
        //noinspection ResourceAsColor
        mSwipeRefreshLayout.setColorScheme(
                R.color.colorPrimaryDark, R.color.colorAccent,
                R.color.colorAccent, R.color.colorPrimaryDark);


        mLayoutManager = new GridLayoutManager(this,1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        if (!mSwipeRefreshLayout.isRefreshing())
        {
            mSwipeRefreshLayout.setRefreshing(true);
        }

        final EditText search=(EditText)findViewById(R.id.search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        initiateRefresh(flag);
        insertCustomersTolocalDB(dataSet);
    }

    private void filter(String text) {
        //new array list that will hold the filtered data
        List<Customer> filterdNames = new ArrayList<>();

        //looping through existing elements
        for (Customer s : dataSet) {
            //if the existing elements contains the search input
            if (s.getCustName().toLowerCase().contains(text.toLowerCase())) {
                //adding the element to filtered list
                filterdNames.add(s);

            }
        }
        itemAdapter.filterList(filterdNames);
    }



    public  void initiateRefresh(int i)
    {
        String Url;
        if(i!=0)
            Url="http://toh.hadeya.net/api/TOHCustomers/allTOHCustomers/13007";
        else
            Url="http://toh.hadeya.net/api/TOHCustomers/allTOHCustomers/13007";
        /**
         * Execute the background task, which uses {@link AsyncTask} to load the data.
         */
        // We first check for cached request
        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(Url);
        if (entry != null) {
            // fetch the data from cache
            try {
                String data = new String(entry.data, "UTF-8");
                clearDataSet();
                Iterator iterator = Parser.parseStringToJson(data).iterator();
                while (iterator.hasNext()){
                    Customer movie = (Customer) iterator.next();
                    dataSet.add(movie);
                    itemAdapter.notifyItemInserted(dataSet.size() - 1);
                }

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }

        /////////////connection//////////
        StringRequest strReq = new StringRequest(Request.Method.GET, Url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Log.d("response", response);
                clearDataSet();
                Iterator iterator = Parser.parseStringToJson(response).iterator();
                while (iterator.hasNext()){
                    Customer movie = (Customer) iterator.next();
                    dataSet.add(movie);
                    itemAdapter.notifyItemInserted(dataSet.size() - 1);
                }
                onRefreshComplete();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // Stop the refreshing indicator
                mSwipeRefreshLayout.setRefreshing(false);
                Log.d("response", error.toString());
            }
        });

        // Adding request to volley request queue
        AppController.getInstance().addToRequestQueue(strReq);

    }

    private void clearDataSet()
    {
        if (dataSet != null){
            dataSet.clear();
            itemAdapter.notifyDataSetChanged();
        }
    }
    private void onRefreshComplete()
    {
        mSwipeRefreshLayout.setRefreshing(false);

    }
    public void insertCustomersTolocalDB(List<Customer> customers)
    {
        for (int i=0;i<customers.size();i++)
        {
            addCustomer(customers.get(i));
        }
    }

    public void addCustomer(Customer customer)
    {

        // SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CustomerTable.CustomerCode, customer.getCustomerCode());
        values.put(CustomerTable.CustName, customer.getCustName());
        values.put(CustomerTable.StreetAra,customer.getStreetAra());
        values.put(CustomerTable.Classification,customer.getClassification());
        values.put(CustomerTable.PersonToConnect,customer.getPersonToConnect());
        values.put(CustomerTable.Tel,customer.getTel());
        values.put(CustomerTable.TAXID,customer.getTAXID());
        values.put(CustomerTable.Flag,"1");//
        // Inserting Row
        //db.insert(TABLE_MOVIES, null, values);
        //db.close(); // Closing database connection
        CustomerContentProvider moviesContentProvider=new CustomerContentProvider(this);
        moviesContentProvider.insert(CustomerContentProvider.CONTENT_URI_add,values);

    }
}

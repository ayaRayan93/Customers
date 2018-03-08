package com.example.ayaali.customers.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ayaali.customers.R;
import com.example.ayaali.customers.model.Customer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

/**
 * Created by AyaAli on 05/03/2018.
 */

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {

    private List<Customer> DataSet;
    private static Context context;

    public CustomerAdapter(Context cont,List<Customer> dataSet)
    {
        context=cont;
        DataSet = dataSet;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {
        //@BindView(R.id.image)ImageView poster;
        @BindView(R.id.title)TextView title;
        // @BindView(R.id.text)TextView text;

        public ViewHolder(View v)
        {
            super(v);

            ButterKnife.bind(this,v);

        }

      /*  public ImageView getPoster() {
            return poster;
        }*/

        public TextView getTitle() {
            return title;
        }

        public void setTitle(TextView title) {
            this.title = title;
        }

       /* public TextView getText() {
            return text;
        }

        public void setText(TextView text) {
            this.text = text;
        }*/
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        return  new CustomerAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position)
    {
        if (DataSet.get(position) != null)
        {
            Log.d("", "Element " + position + " set.");
            holder.getTitle().setText(DataSet.get(position).getCustName());

        }
    }

    @Override
    public int getItemCount() {
        return DataSet.size();
    }

    public void filterList(List<Customer> filterdNames) {
        this.DataSet = filterdNames;
        notifyDataSetChanged();
    }
}



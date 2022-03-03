package com.example.onlinetourism;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class List2 extends BaseAdapter {
    private Activity context;
    ArrayList<List1> customer;
    public List2()
    {

    }
    public List2(Activity context, ArrayList cust) {
        // super(context, R.layout.row_item, countries);
        this.context = context;
        this.customer=cust;

    }

    public static class ViewHolder
    {
        TextView textViewCapital;
        TextView textViewCountry;
        TextView tv;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;

        LayoutInflater inflater = context.getLayoutInflater();
        CustomerLists.ViewHolder vh;
        if(convertView==null) {
            vh=new CustomerLists.ViewHolder();
            row = inflater.inflate(R.layout.activity_lists, null, true);
            vh.textViewCountry = (TextView) row.findViewById(R.id.textViewCountry);
            vh.textViewCapital = (TextView) row.findViewById(R.id.textViewCapital);
            vh.tv = (TextView) row.findViewById(R.id.ordertxt);
            // store the holder with the view.
            row.setTag(vh);
        }
        else {
            vh = (CustomerLists.ViewHolder) convertView.getTag();
        }
        vh.textViewCountry.setText(customer.get(position).getName());
        vh.textViewCapital.setText(customer.get(position).getPlace());
        vh.tv.setText(customer.get(position).getName());
        Log.d("Tag",customer.get(position).getPlace());
        return  row;
    }

    public long getItemId(int position) {
        return position;
    }

    public Object getItem(int position) {
        return position;
    }

    public int getCount() {

        if(customer.size()<=0)
            return 1;
        return customer.size();
    }
}
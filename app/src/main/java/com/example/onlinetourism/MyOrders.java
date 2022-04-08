package com.example.onlinetourism;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyOrders extends AppCompatActivity {
    String serviceproviderid;
    ArrayList<List1> arrayList = new ArrayList<>();
    DatabaseHelper dbh;
    SQLiteDatabase db;
    Activity activity;
    ListView lv;
    String s1, s2, s3, s4, s5, s6, s7,bookingid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        lv = (ListView) findViewById(R.id.listview1);
        Intent inten = getIntent();
        serviceproviderid = inten.getExtras().getString("serviceproviderid");
        activity = this;
        dbh = new DatabaseHelper(this);
        db = dbh.getReadableDatabase();
        List2 customList = new List2(activity, arrayList);
        lv.setAdapter(customList);

        //move activity

        String[] colms = {DatabaseContract.Bookings.COL_COMPANYID, DatabaseContract.Bookings.COL_SERVICESEEEKERID, DatabaseContract.Bookings.COL_PLACE,  DatabaseContract.Bookings.COL_ID};
        Cursor cc = db.query("Bookings", colms, "CompanyId=?", new String[]{serviceproviderid}, null, null, null);
        if (cc.getCount()==0) {
            Toast.makeText(getApplicationContext(),"No Record exist",Toast.LENGTH_LONG).show();
        }
        else {



            while (cc.moveToNext()) {

                s1 = cc.getString(0);
                s2 = cc.getString(1);
                s3 = cc.getString(2);
                bookingid = cc.getString(3);

                //service seeker id say sab info get ker key neechecy display krni hay

                // Toast.makeText(MyOrders.this, "serviceseeker id is" + s2, Toast.LENGTH_SHORT).show();
                String[] colms1 = {DatabaseContract.ServiceSeeker.COL_NAME, DatabaseContract.ServiceSeeker.COL_LOCATION, DatabaseContract.ServiceSeeker.COL_CONTACT, DatabaseContract.ServiceSeeker.COL_EMAIL};
                Cursor cc1 = db.query("ServiceSeeker", colms1, "ID=?", new String[]{s2}, null, null, null);
                if (cc1.getCount()==0) {
                    Toast.makeText(getApplicationContext(),"No Record exist",Toast.LENGTH_LONG).show();
                }
                else  {

                    // Toast.makeText(getApplicationContext(), "No Record exist", Toast.LENGTH_LONG).show();


                    while (cc1.moveToNext()) {

                        s4 = cc1.getString(0);
                        s5 = cc1.getString(1);
                        s6 = cc1.getString(2);
                        s7 = cc1.getString(3);
                    }

                    List1 mObj = new List1(s4, s7, "Contact: " +s6);
                    arrayList.add(mObj);


                }

                List2 customList1 = new List2(activity, arrayList);
                lv.setAdapter(customList1);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        Intent intent = new Intent(MyOrders.this, CustomerInformation.class);
                        intent.putExtra("bookingid", bookingid);
                        intent.putExtra("customername", s4);
                        intent.putExtra("customerlocation", s5);
                        intent.putExtra("customercontact",s6);
                        intent.putExtra("customeremail",s7);
                        startActivity(intent);
                        // Toast.makeText(getApplicationContext(), "You Selected " + arrayList.get(position).getName() + " as Country", Toast.LENGTH_LONG).show();
                    }
                });


            }

        }
    }
}
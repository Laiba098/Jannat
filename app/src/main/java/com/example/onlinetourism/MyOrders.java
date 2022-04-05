package com.example.onlinetourism;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyOrders extends AppCompatActivity {
String serviceproviderid;
    ArrayList<List1> arrayList=new ArrayList<>();
    DatabaseHelper dbh;
    SQLiteDatabase db;
    Activity activity;
    ListView lv;
    String s1,s2,s3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        lv = (ListView) findViewById(R.id.listview1);
        Intent inten = getIntent();
        serviceproviderid= inten.getExtras().getString("serviceproviderid");
        activity = this;
        dbh = new DatabaseHelper(this);
        db = dbh.getReadableDatabase();
        List2 customList = new List2(activity, arrayList);
        lv.setAdapter(customList);

        //move activity

        String[] colms = {DatabaseContract.Bookings.COL_COMPANYID, DatabaseContract.Bookings.COL_SERVICESEEEKERID,  DatabaseContract.Bookings.COL_PLACE};
        Cursor cc = db.query("Bookings", colms, "CompanyId=?", new String[]{serviceproviderid}, null, null, null);
        if (cc.getCount() > 0) {

            // Toast.makeText(getApplicationContext(), "No Record exist", Toast.LENGTH_LONG).show();


            while (cc.moveToNext()) {

                s1 = cc.getString(0);
                s2 = cc.getString(1);
                s3 = cc.getString(1);

                //service seeker id say sab info get ker key neechecy display krni hay

                Toast.makeText(MyOrders.this, "serviceseeker id is" + s2, Toast.LENGTH_SHORT).show();

                List1 mObj = new List1(s1,s2, "Place: "+s3);
                arrayList.add(mObj);


            }

            List2 customList1 = new List2(activity, arrayList);
            lv.setAdapter(customList1);


        }

    }
}
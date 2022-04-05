package com.example.onlinetourism;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UserHomePage extends AppCompatActivity {
    ListView lv;
    ArrayList<List1> arrayList=new ArrayList<>();
    DatabaseHelper dbh;
    SQLiteDatabase db;
    Activity activity;
    String str,s1,s2,s3, s4;
    EditText edt;
    ImageButton img;
    EditText searchbar;
    MenuItem chatwithothers, ordershistory;
    String lang;
    Context context;
    Resources resources;
    String languages;
    private ProgressDialog progressDialog;
    TextView tv;
    Button nearest;
    String serviceseekerid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_page);
        activity = this;
        dbh = new DatabaseHelper(this);
        Intent inten = getIntent();
        languages= inten.getExtras().getString("language");
        serviceseekerid= inten.getExtras().getString("Serviceseekerid");

        img=findViewById(R.id.search);
        db = dbh.getReadableDatabase();
        tv = (TextView) findViewById(R.id.txt);
        edt=(EditText) findViewById(R.id.searchbar);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // db = dbh.getReadableDatabase();
              //  Toast.makeText(UserHomePage.this, "message: "+edt.getText().toString(), Toast.LENGTH_LONG).show();
                if (!edt.getText().toString().equals(" ")) {
                    String strs = edt.getText().toString();
                    String[] colm = {DatabaseContract.TourPlans.COL_ID, DatabaseContract.TourPlans.COL_COMPANYNAME, DatabaseContract.TourPlans.COL_VEHICLENAME, DatabaseContract.TourPlans.COL_PLACE};
                    Cursor c = db.query(DatabaseContract.TourPlans.TABLE_NAME, colm, DatabaseContract.TourPlans.COL_PLACE+"=?", new String[] { strs}
                            , null, null, null, null);
                    if (c.getCount() > 0) {
                        arrayList.clear();
                        while (c.moveToNext()) {

                            long id = c.getLong(0);
                            s1 = c.getString(1);
                            s2 = c.getString(2);
                            s4 = c.getString(3);
                            s3 = String.valueOf(id);
                           // Toast.makeText(UserHomePage.this, "message222: " + edt.getText().toString(), Toast.LENGTH_LONG).show();
                                List1 mObj = new List1(s1,s2, "Place: "+s4);
                                arrayList.add(mObj);



                        }

                        List2 customList = new List2(activity, arrayList);


                        lv.setAdapter(customList);

                      /*  lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                String ss = arrayList.get(position).getOrderNo();
                                Intent intent = new Intent(MilkManList.this, MilkManDetails.class);
                                intent.putExtra("val", ss);
                                intent.putExtra("val2", str);
                                intent.putExtra("language", lang);
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(), "You Selected " + arrayList.get(position).getName() + " as Country", Toast.LENGTH_LONG).show();
                            }
                        });*/

                    }else {

                        Toast.makeText(UserHomePage.this, "No Tour Team Found", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(UserHomePage.this, "Please Enter Any Location ", Toast.LENGTH_LONG).show();
                }
            }
        });
        //Toast.makeText(getApplicationContext(), "Record id" + str, Toast.LENGTH_LONG).show();


        lv = (ListView) findViewById(R.id.list1);
        //move activity

        String[] colms = {DatabaseContract.TourPlans.COL_ID, DatabaseContract.TourPlans.COL_COMPANYNAME, DatabaseContract.TourPlans.COL_VEHICLENAME, DatabaseContract.TourPlans.COL_PLACE};
        Cursor cc = db.query(DatabaseContract.TourPlans.TABLE_NAME, colms, null, null
                , null, null, null, null);
        if (cc.getCount() > 0) {

           // Toast.makeText(getApplicationContext(), "No Record exist", Toast.LENGTH_LONG).show();


            while (cc.moveToNext()) {
                long id = cc.getLong(0);
                s1 = cc.getString(1);
                s2 = cc.getString(2);
                s4 = cc.getString(3);
                s3 = String.valueOf(id);
                List1 mObj = new List1(s1,s2, "Place: "+s4);
                    arrayList.add(mObj);


            }

            List2 customList = new List2(activity, arrayList);
            lv.setAdapter(customList);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    String compname = arrayList.get(position).getName();
                    String vehiclename  = arrayList.get(position).getVehicle();
                    String placename = arrayList.get(position).getPlace();
                    Intent intent = new Intent(UserHomePage.this, CompanyInformation.class);
                    intent.putExtra("compname", compname);
                    intent.putExtra("vehiclename", vehiclename);
                    intent.putExtra("placename", placename);
                    intent.putExtra("Serviceseekerid",serviceseekerid);

                    startActivity(intent);
                   // Toast.makeText(getApplicationContext(), "You Selected " + arrayList.get(position).getName() + " as Country", Toast.LENGTH_LONG).show();
                }
            });


        }
        else {

            Toast.makeText(UserHomePage.this, "No Record exist", Toast.LENGTH_LONG).show();
        }

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu2, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {



        // return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.checkplacereviews:
                Intent in = new Intent(getApplicationContext(), PlaceReviews.class);
                startActivity(in);break;


            default:
                return super.onOptionsItemSelected(item);

        }
        return true;
    }
}
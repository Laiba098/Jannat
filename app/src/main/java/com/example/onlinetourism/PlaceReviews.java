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

public class PlaceReviews extends AppCompatActivity {
    ListView lv;
    ArrayList<List3> arrayList=new ArrayList<>();
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
        setContentView(R.layout.activity_place_reviews);
        activity = this;
        dbh = new DatabaseHelper(this);
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
                    String[] colm = {DatabaseContract.PlaceReview.COL_PLACED_BY,DatabaseContract.PlaceReview.COL_PLACE, DatabaseContract.PlaceReview.COL_REVIEW};
                    Cursor c = db.query(DatabaseContract.PlaceReview.TABLE_NAME, colm, DatabaseContract.PlaceReview.COL_PLACE+"=?", new String[] { strs}
                            , null, null, null, null);
                    if (c.getCount() > 0) {
                        arrayList.clear();
                        while (c.moveToNext()) {

                            s1 = c.getString(0);
                            s2 = c.getString(1);
                            s4 = c.getString(2);
                            // Toast.makeText(UserHomePage.this, "message222: " + edt.getText().toString(), Toast.LENGTH_LONG).show();
                            List3 mObj = new List3(s1,s2,s4);
                            arrayList.add(mObj);



                        }

                        List4 customList = new List4(activity, arrayList);


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

                        Toast.makeText(PlaceReviews.this, "No any review Found", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(PlaceReviews.this, "Please Enter Any Location ", Toast.LENGTH_LONG).show();
                }
            }
        });
        //Toast.makeText(getApplicationContext(), "Record id" + str, Toast.LENGTH_LONG).show();


        lv = (ListView) findViewById(R.id.list1);
        //move activity

        String[] colm = {DatabaseContract.PlaceReview.COL_PLACED_BY,DatabaseContract.PlaceReview.COL_PLACE, DatabaseContract.PlaceReview.COL_REVIEW};
        Cursor c = db.query(DatabaseContract.PlaceReview.TABLE_NAME, colm, null, null
                , null, null, null, null);
        if (c.getCount() > 0) {
            arrayList.clear();
            while (c.moveToNext()) {

                s1 = c.getString(0);
                s2 = c.getString(1);
                s4 = c.getString(2);
                // Toast.makeText(UserHomePage.this, "message222: " + edt.getText().toString(), Toast.LENGTH_LONG).show();
                List3 mObj = new List3(s1,s2,s4);
                arrayList.add(mObj);


            }

            List4 customList = new List4(activity, arrayList);
            lv.setAdapter(customList);




        }
        else {

            Toast.makeText(PlaceReviews.this, "No Record exist", Toast.LENGTH_LONG).show();
        }

    }
    }
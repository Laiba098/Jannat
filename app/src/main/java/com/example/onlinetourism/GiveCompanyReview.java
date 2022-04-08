package com.example.onlinetourism;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class GiveCompanyReview extends AppCompatActivity {
    EditText et;
    ListView lv;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    Activity activity;
    ArrayList<ReviewList> rev=new ArrayList<ReviewList>();
    String str,str1,str2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_company_review);
        Intent intent=getIntent();
        str1=intent.getStringExtra("companyid");
        str2=intent.getStringExtra("serviceseekerid");
        et=(EditText)findViewById(R.id.edt);
        getSupportActionBar().setTitle("Reviews");
        activity = this;
        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getReadableDatabase();
        lv = (ListView) findViewById(R.id.list);
        String[] colm={DatabaseContract.CompanyReviews.COL_REVIEW};
        Cursor cr=db.query(DatabaseContract.CompanyReviews.TABLE_NAME,colm,"PlacedBy = ? And PlacedTo = ?", new String[] {str2,str1}
                , null, null, null, null);
        if (cr.getCount()==0) {
            Toast.makeText(getApplicationContext(),"No Record exist",Toast.LENGTH_LONG).show();
        }
        else
        {
            rev.clear();
            while(cr.moveToNext())
            {
                str=cr.getString(0);
                ReviewList mObj = new ReviewList(str);
                rev.add(mObj);
           }
            reviewHolder rList = new reviewHolder(activity, rev);


            lv.setAdapter(rList);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                    //Toast.makeText(getApplicationContext(), "You Selected " + rev.get(position).getName() + " as Country", Toast.LENGTH_LONG).show();
                }
            });
        }
        db.close();


    }
    public void onbtn(View v)
    {

        if(et.getText().toString().equals(" "))
        {
            Toast.makeText(getApplicationContext(), "Please Enter Your Review", Toast.LENGTH_LONG).show();
        }
        else {
            db = dbHelper.getWritableDatabase();




            str=et.getText().toString();
            ReviewList mObj = new ReviewList(str);
            rev.add(mObj);
            reviewHolder rList = new reviewHolder(activity, rev);
            lv.setAdapter(rList);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                    //  Toast.makeText(getApplicationContext(), "You Selected " + rev.get(position).getName() + " as Country", Toast.LENGTH_LONG).show();
                }
            });
            ContentValues values = new ContentValues();
            values.put(DatabaseContract.CompanyReviews.COL_PLACED_BY, str2);
            values.put(DatabaseContract.CompanyReviews.COL_PLACED_TO, str1);
            values.put(DatabaseContract.CompanyReviews.COL_REVIEW, str);
            long newRowId = db.insert(DatabaseContract.CompanyReviews.TABLE_NAME, null, values);
            if (newRowId > 0) {
                Toast.makeText(getApplicationContext(), "Review has been taken", Toast.LENGTH_LONG).show();
            }
            db.close();

            et.setHint("Type Your Review Here");
        }
    }
}
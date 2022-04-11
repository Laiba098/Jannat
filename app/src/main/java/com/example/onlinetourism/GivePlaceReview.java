package com.example.onlinetourism;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GivePlaceReview extends AppCompatActivity {
String str1,str2,str3,review1;
EditText place,review;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_place_review);
        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getReadableDatabase();
        Intent intent=getIntent();
        str1=intent.getStringExtra("companyid");
        str2=intent.getStringExtra("serviceseekerid");
        str3=intent.getStringExtra("place");
        btn=findViewById(R.id.btn);
        place=findViewById(R.id.place);
        review=findViewById(R.id.review);
        place.setText(str3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                db = dbHelper.getWritableDatabase();
                review1=review.getText().toString();
                values.put(DatabaseContract.PlaceReview.COL_PLACED_BY, str2);
                values.put(DatabaseContract.PlaceReview.COL_PLACE, str3);
                values.put(DatabaseContract.PlaceReview.COL_REVIEW, review1);
               // Toast.makeText(getApplicationContext(), "Review "+review1+"is placed", Toast.LENGTH_LONG).show();

                long newRowId = db.insert(DatabaseContract.PlaceReview.TABLE_NAME, null, values);
                if (newRowId > 0) {
                    Toast.makeText(getApplicationContext(), "Review has been taken for "+str3, Toast.LENGTH_LONG).show();
                }


            }
        });



    }
}
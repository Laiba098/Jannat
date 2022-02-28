package com.example.onlinetourism;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FIrstPage extends AppCompatActivity {
    Button serviceseeker,serrviceprovider;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    String check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        serviceseeker=findViewById(R.id.serviceseeker);
        serrviceprovider=findViewById(R.id.serviceprovider);
        dbHelper = new DatabaseHelper(this);
        db=dbHelper.getReadableDatabase();


    }
    public void Login(View v)
    {



        if(v.getId()==R.id.serviceseeker)
        {
            check="serviceseeker";
                    Intent intent = new Intent(FIrstPage.this, SignInPage.class);
                    intent.putExtra("check",check);
                    startActivity(intent);
            }

        else if(v.getId()==R.id.serviceprovider)
        {
            check="serviceprovider";


                    Intent intent = new Intent(FIrstPage.this, SignInPage.class);
                    intent.putExtra("check",check);
                    startActivity(intent);


        }

    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}
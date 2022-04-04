package com.example.onlinetourism;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class CompanyInformation extends AppCompatActivity {
    String id,compname1,vehiclename1,placename1;
    TextView compname,vehicle,numberofseats,place,vehiclerent,roomrent,driverrent,foorrent, totalrent;
    DatabaseHelper dbh;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_information);
        dbh = new DatabaseHelper(this);
        db = dbh.getReadableDatabase();
        Intent intent = getIntent();
        compname1 =intent.getStringExtra("compname");
        placename1 =intent.getStringExtra("placename");
        vehiclename1 =intent.getStringExtra("vehiclename");
        compname=findViewById(R.id.companyname);
        vehicle=findViewById(R.id.vehicle);
        numberofseats=findViewById(R.id.numberofseats);
        place=findViewById(R.id.place);
        vehiclerent=findViewById(R.id.vehiclerent);
        roomrent=findViewById(R.id.roomrent);
        driverrent=findViewById(R.id.driverrent);
        foorrent=findViewById(R.id.foodrent);
        totalrent=findViewById(R.id.totalrent);

        compname.setText(compname1);
        vehicle.setText(vehiclename1);
        place.setText(placename1);


    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu3, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }
}
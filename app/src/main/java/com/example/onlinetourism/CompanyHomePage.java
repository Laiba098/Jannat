package com.example.onlinetourism;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CompanyHomePage extends AppCompatActivity {
EditText companyname, vehiclename, numofseats, place,vehiclerent, roomrent, driverrent, foodrent;
String companyname1, vehiclename1, numofseats1, place1,vehiclerent1, roomrent1, driverrent1, foodrent1;
Button btn;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_home_page);
        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();
        companyname=findViewById(R.id.companyname);
        vehiclename=findViewById(R.id.vehiclename);
        place=findViewById(R.id.place);
        vehiclerent=findViewById(R.id.vehiclerent);
        roomrent=findViewById(R.id.roomrent);
        driverrent=findViewById(R.id.driverrent);
        foodrent=findViewById(R.id.FoodRent);
        numofseats=findViewById(R.id.numbofseats);
        btn=findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                companyname1=companyname.getText().toString();
                vehiclename1=vehiclename.getText().toString();
                numofseats1=numofseats.getText().toString();
                place1=place.getText().toString();
                vehiclerent1=vehiclerent.getText().toString();
                roomrent1=roomrent.getText().toString();
                driverrent1=driverrent.getText().toString();
                foodrent1=foodrent.getText().toString();


                if(companyname.equals("") || vehiclename.equals("") || numofseats.equals("") || place.equals("") || vehiclerent.equals("") || roomrent.equals("") || driverrent.equals("")|| foodrent.equals(""))
                {
                    Toast.makeText(CompanyHomePage.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }

                ContentValues values = new ContentValues();
                values.put(DatabaseContract.TourPlans.COL_COMPANYNAME, companyname1);
                values.put(DatabaseContract.TourPlans.COL_VEHICLENAME, vehiclename1);
                values.put(DatabaseContract.TourPlans.COL_NUMOFSEATS, numofseats1);
                values.put(DatabaseContract.TourPlans.COL_PLACE, place1);
                values.put(DatabaseContract.TourPlans.COL_VEHICLERENT, vehiclerent1);
                values.put(DatabaseContract.TourPlans.COL_ROOMRENT, roomrent1);
                values.put(DatabaseContract.TourPlans.COL_DRIVERRENT, driverrent1);
                values.put(DatabaseContract.TourPlans.COL_FOODRENT, foodrent1);


                long newRowId = db.insert(DatabaseContract.TourPlans.TABLE_NAME, null, values);
                if (newRowId > 0) {
                    Toast.makeText(CompanyHomePage.this, "Tour Details has been Shared Successfully", Toast.LENGTH_SHORT).show();
                }
                db.close();
                companyname.setText(null);
                vehiclerent.setText(null);
                vehiclename.setText(null);
                numofseats.setText(null);
                place.setText(null);
                roomrent.setText(null);
                driverrent.setText(null);
                foodrent.setText(null);



            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu1, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }
}
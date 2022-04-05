package com.example.onlinetourism;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class CompanyInformation extends AppCompatActivity {
    String id,compname1,vehiclename1,placename1;
    TextView compname,vehicle,numberofseats,place,vehiclerent,roomrent,driverrent,foorrent, totalrent;
    DatabaseHelper dbh;
    SQLiteDatabase db;
    String companyid,contactnumb;

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

        String[] columns = { "NumOfSeats", "VehicleRent","RoomRent","DriverRent","FoodRent"};
        Cursor cursor = db.query("TourPlans", columns, "CompanyName=?", new String[]{compname1}, null, null, null);


        while (cursor.moveToNext()) {
            String noofseats1 = cursor.getString(0);
            String vehiclerent1 = cursor.getString(1);
            String roomrent1 = cursor.getString(2);
            String driverrent1 = cursor.getString(3);
            String foodrent1 = cursor.getString(4);
            numberofseats.setText(noofseats1);
            vehiclerent.setText(vehiclerent1);
            roomrent.setText(roomrent1);
            driverrent.setText(driverrent1);
            foorrent.setText(foodrent1);

           int totalrent1= Integer.parseInt(vehiclerent1)+ Integer.parseInt(roomrent1)+Integer.parseInt(driverrent1)+Integer.parseInt(foodrent1);
            String s=String.valueOf(totalrent1);
            totalrent.setText(s);
        }


    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu3, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {



        // return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.callcompany:



                String[] columns = {"CompanyId"};
                String[] columns1 = {"Contact"};
                Cursor cursor = db.query("TourPlans", columns, "CompanyName=?", new String[]{compname1}, null, null, null);


                while (cursor.moveToNext()) {
                   companyid = cursor.getString(0);
                }

                Cursor cursor1 = db.query("ServiceProvider", columns1, "ID=?", new String[]{companyid}, null, null, null);


                while (cursor1.moveToNext()) {
                    contactnumb = cursor1.getString(0);
                }
             //   Toast.makeText(CompanyInformation.this, "number is "+contactnumb, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+contactnumb));
                startActivity(intent);

              break;

            case R.id.chat:
              break;

            case R.id.googlemap:
                break;

            case R.id.checkreview:
                break;

            case R.id.giveplacereview:
                break;

            case R.id.givecompreview:
                break;
            default:
                return super.onOptionsItemSelected(item);

        }
        return true;
    }
}
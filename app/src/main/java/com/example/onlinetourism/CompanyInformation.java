package com.example.onlinetourism;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class CompanyInformation extends AppCompatActivity {
    String id,compname1,vehiclename1,placename1;
    TextView compname,vehicle,numberofseats,place,vehiclerent,roomrent,driverrent,foorrent, totalrent;
    DatabaseHelper dbh;
    SQLiteDatabase db;
    String companyid,serviceseekerid,contactnumb;
    Button bookvehicle;
    String noofseats1;
    String vehiclerent1;
    String roomrent1;
    String driverrent1;
    String foodrent1,serviceseekername,serviceprovidername;

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
        serviceseekerid=intent.getStringExtra("Serviceseekerid");
       // Toast.makeText(CompanyInformation.this, "id is" +serviceseekerid, Toast.LENGTH_SHORT).show();
         bookvehicle=findViewById(R.id.bookvehicle);
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
            noofseats1 = cursor.getString(0);
            vehiclerent1 = cursor.getString(1);
            roomrent1 = cursor.getString(2);
             driverrent1 = cursor.getString(3);
            foodrent1 = cursor.getString(4);
            numberofseats.setText(noofseats1);
            vehiclerent.setText(vehiclerent1);
            roomrent.setText(roomrent1);
            driverrent.setText(driverrent1);
            foorrent.setText(foodrent1);

           int totalrent1= Integer.parseInt(vehiclerent1)+ Integer.parseInt(roomrent1)+Integer.parseInt(driverrent1)+Integer.parseInt(foodrent1);
            String s=String.valueOf(totalrent1);
            totalrent.setText(s);


            String[] columns2 = {"CompanyId"};
            String[] columns1 = {"Contact","Name"};
            Cursor cursor2 = db.query("TourPlans", columns2, "CompanyName=?", new String[]{compname1}, null, null, null);


            while (cursor2.moveToNext()) {
                companyid = cursor2.getString(0);
            }

            Cursor cursor1 = db.query("ServiceProvider", columns1, "ID=?", new String[]{companyid}, null, null, null);


            while (cursor1.moveToNext()) {

                contactnumb = cursor1.getString(0);
                serviceprovidername= cursor1.getString(1);
            }
        }
        bookvehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put(DatabaseContract.Bookings.COL_COMPANYID,companyid);
                values.put(DatabaseContract.Bookings.COL_SERVICESEEEKERID,serviceseekerid);
                values.put(DatabaseContract.Bookings.COL_COMPANYNAME, compname1);
                values.put(DatabaseContract.Bookings.COL_VEHICLENAME, vehiclename1);
                values.put(DatabaseContract.Bookings.COL_NUMOFSEATS, noofseats1);
                values.put(DatabaseContract.Bookings.COL_PLACE, placename1);
                values.put(DatabaseContract.Bookings.COL_VEHICLERENT, vehiclerent1);
                values.put(DatabaseContract.Bookings.COL_ROOMRENT, roomrent1);
                values.put(DatabaseContract.Bookings.COL_DRIVERRENT, driverrent1);
                values.put(DatabaseContract.Bookings.COL_FOODRENT, foodrent1);


                long newRowId = db.insert(DatabaseContract.Bookings.TABLE_NAME, null, values);
                if (newRowId > 0) {
                    Toast.makeText(CompanyInformation.this, "Booking Done", Toast.LENGTH_SHORT).show();
                }
            }
        });


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



             //   Toast.makeText(CompanyInformation.this, "number is "+contactnumb, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+contactnumb));
                startActivity(intent);

              break;

            case R.id.chat:
                Intent intentt=new Intent(CompanyInformation.this,Chat.class);
               // intentt.putExtra("serviceproviderid",compname1);

                String[] colms1 = {DatabaseContract.ServiceSeeker.COL_NAME};
                Cursor cc1 = db.query("ServiceSeeker", colms1, "ID=?", new String[]{serviceseekerid}, null, null, null);
                if (cc1.getCount() > 0) {

                    // Toast.makeText(getApplicationContext(), "No Record exist", Toast.LENGTH_LONG).show();


                    while (cc1.moveToNext()) {

                        serviceseekername = cc1.getString(0);
                    }}
               // intentt.putExtra("serviceseekerid",serviceseekerid);
                intentt.putExtra("serviceseekername",serviceseekername);
                intentt.putExtra("serviceprovidername",serviceprovidername);
                intentt.putExtra("using",serviceseekername);
               // String tablename= serviceseekername+serviceprovidername;
               // Toast.makeText(CompanyInformation.this, "Table Name: "+tablename, Toast.LENGTH_SHORT).show();
                startActivity(intentt);
                break;
            case R.id.googlemap:
                Intent intent6=new Intent(CompanyInformation.this,MapsActivity.class);
                intent6.putExtra("place",placename1);
                startActivity(intent6);
                break;

            case R.id.checkreview:
                Intent intent3=new Intent(CompanyInformation.this,CompanyReviews.class);
                intent3.putExtra("serviceproviderid",companyid);
                startActivity(intent3);
                break;

            case R.id.giveplacereview:
                Intent intent1=new Intent(CompanyInformation.this,GivePlaceReview.class);
                intent1.putExtra("companyid",companyid);
                intent1.putExtra("serviceseekerid",serviceseekerid);
                intent1.putExtra("place",placename1);
                startActivity(intent1);
                break;

            case R.id.givecompreview:
                Intent intent2=new Intent(CompanyInformation.this,GiveCompanyReview.class);
                intent2.putExtra("companyid",companyid);
                intent2.putExtra("serviceseekerid",serviceseekerid);
                startActivity(intent2);
                break;
            default:
                return super.onOptionsItemSelected(item);

        }
        return true;
    }
}
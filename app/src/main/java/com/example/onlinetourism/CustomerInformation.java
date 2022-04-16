package com.example.onlinetourism;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class CustomerInformation extends AppCompatActivity {
TextView custname,custlocation,custemail,custcontact,bookedplace, bookedvehicle,bookedrent;
    String custname1,custlocation1,custemail1,custcontact1,bookid, s1,s2,s3,s4,s5,s6,s7,totalrent,serviceproviderid,serviceprovidername;
     Button btn;
    DatabaseHelper dbh;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_information);
        custname=findViewById(R.id.customername);
        custemail=findViewById(R.id.customeremail);
        custcontact=findViewById(R.id.customercontact);
        custlocation=findViewById(R.id.customerlocation);
        bookedplace=findViewById(R.id.bookedplace);
        bookedrent=findViewById(R.id.totalrent);
        bookedvehicle=findViewById(R.id.bookedvehicle);
        btn=findViewById(R.id.callcustomer);
        dbh = new DatabaseHelper(this);
        db = dbh.getReadableDatabase();

        Bundle bn = getIntent().getExtras();
        bookid= bn.getString("bookingid");
        custname1 = bn.getString("customername");
         custlocation1= bn.getString("customerlocation");
        custemail1= bn.getString("customeremail");
        custcontact1 = bn.getString("customercontact");
        serviceproviderid = bn.getString("serviceproviderid");


        String[] colms3 = {DatabaseContract.ServiceProvider.COL_NAME};
        Cursor cc11 = db.query("ServiceProvider", colms3, "ID=?", new String[]{serviceproviderid}, null, null, null);
        if (cc11.getCount() > 0) {

            // Toast.makeText(getApplicationContext(), "No Record exist", Toast.LENGTH_LONG).show();


            while (cc11.moveToNext()) {

                serviceprovidername = cc11.getString(0);

            }}



        custname.setText(custname1);
        custcontact.setText(custcontact1);
        custemail.setText(custemail1);
        custlocation.setText(custlocation1);

        String[] colms1 = {DatabaseContract.Bookings.COL_PLACE, DatabaseContract.Bookings.COL_VEHICLENAME, DatabaseContract.Bookings.COL_VEHICLERENT,DatabaseContract.Bookings.COL_ROOMRENT,DatabaseContract.Bookings.COL_DRIVERRENT,DatabaseContract.Bookings.COL_FOODRENT};
        Cursor cc1 = db.query("Bookings", colms1, "ID=?", new String[]{bookid}, null, null, null);
        if (cc1.getCount() > 0) {

            // Toast.makeText(getApplicationContext(), "No Record exist", Toast.LENGTH_LONG).show();


            while (cc1.moveToNext()) {

                s1 = cc1.getString(0);
                s2 = cc1.getString(1);
                s3 = cc1.getString(2);
                s4 = cc1.getString(3);
                s5 = cc1.getString(4);
                s6 = cc1.getString(5);

                bookedplace.setText(s1);
                bookedvehicle.setText(s2);
                String totalrent4= String.valueOf(Integer.parseInt(s3)+ Integer.parseInt(s4)+Integer.parseInt(s5)+Integer.parseInt(s6));
                bookedrent.setText(totalrent4);

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:"+custcontact1));
                        startActivity(intent);
                    }
                });





            }




        }



    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu4, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {



        // return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {


            case R.id.chat:
                Intent intentt=new Intent(CustomerInformation.this,Chat.class);
               // String tablename=serviceprovidername+custname1;
                intentt.putExtra("serviceseekername",custname1);
                intentt.putExtra("serviceprovidername",serviceprovidername);
                intentt.putExtra("using",serviceprovidername);
                // String tablename= serviceseekername+serviceprovidername;
                // Toast.makeText(CompanyInformation.this, "Table Name: "+tablename, Toast.LENGTH_SHORT).show();
                startActivity(intentt);
                //Toast.makeText(CustomerInformation.this, "Table name: "+tablename, Toast.LENGTH_SHORT).show();
                startActivity(intentt);
                break;

            default:
                return super.onOptionsItemSelected(item);

        }
        return true;
    }
}
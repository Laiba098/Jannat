package com.example.onlinetourism;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.maps.android.SphericalUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    private GoogleMap mMap;
    LatLng latLng3=null;
    private MarkerOptions options = new MarkerOptions();
    DatabaseHelper dbh;
    SQLiteDatabase db;
    String place,s1,s2,str1;
    ArrayList<LatLng> arr=new ArrayList<>();
    ArrayList<nearest> arrn=new ArrayList<>();
    ArrayList<Double> arrl=new ArrayList<>();
    double distance;
    double max;
    Polyline p;
    PolylineOptions p1;
    int count;
    int num=0;
    TextView dis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        dbh = new DatabaseHelper(this);
        Intent inten = getIntent();
        place = inten.getStringExtra("place");
        db = dbh.getReadableDatabase();
        dis=(TextView)findViewById(R.id.distance);
        client = LocationServices.getFusedLocationProviderClient(this);
        //permissions check ho rahey hain

        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);

    }
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
                nearest ns=new nearest(place);
                arrn.add(ns);
                List<Address> addressList2=null;
                Geocoder geocoder2=new Geocoder(MapsActivity.this);
                try {
                    addressList2=geocoder2.getFromLocationName(place,1);
                } catch (IOException e) {
                    Toast.makeText(MapsActivity.this, "error in ", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                Address address2=addressList2.get(0);
                arr.add(new LatLng(address2.getLatitude(),address2.getLongitude()));





        db.close();
        for (LatLng point : arr) {
            options.position(point);
            options.title(arrn.get(num).getLoc());
            //options.snippet("someDesc");
            mMap.addMarker(options);
            num++;
        }

        if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //after checking permission, calling methos
            getCurrentLocation();
        } else {
            //request permissions
            ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44
            );

        }



    }
    private void getCurrentLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location!=null)
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            latLng3=new LatLng(location.getLatitude(),location.getLongitude());
                            MarkerOptions options=new MarkerOptions().position(latLng3).title("your current location");
                            //for zooming app;
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng3,10));
                            googleMap.addMarker(options);
                            for (int i = 0; i < arr.size(); i++) {
                                //latLng2=new LatLng(arr(i).get)
                                distance = SphericalUtil.computeDistanceBetween(latLng3, arr.get(i));
                                distance = (distance / 1000);
                                arrl.add(distance);
                            }
                            max = arrl.get(0);
                            for (int z = 0; z < arrl.size(); z++) {
                                if (max > arrl.get(z)) {
                                    max = arrl.get(z);
                                    count = z;
                                }
                            }
                            dis.setText("Distance = " + arrl.get(count) + " Km");
                            p1 = new PolylineOptions().clickable(true).add(latLng3, arr.get(count));
                            p =  googleMap.addPolyline(p1);
                            //polyline1=googleMap.addPolyline(new PolylineOptions().clickable(true).add(latLng1,latLng3));
                            //  distn.setText("Distance to current location: "+ SphericalUtil.computeDistanceBetween(latLng1, latLng3)+"Km");
                        }
                    });
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
        {
            getCurrentLocation();
        }
    }

}
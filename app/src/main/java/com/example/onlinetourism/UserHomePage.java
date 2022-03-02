package com.example.onlinetourism;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class UserHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_page);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu2, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {



        // return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.checkplacereviews:
                Intent in = new Intent(getApplicationContext(), PlaceReviews.class);
                startActivity(in);break;

            case R.id.giveplacereviews:
                Intent in1 = new Intent(getApplicationContext(), GivePlaceReview.class);
                startActivity(in1);break;


            default:
                return super.onOptionsItemSelected(item);

        }
        return true;
    }
}
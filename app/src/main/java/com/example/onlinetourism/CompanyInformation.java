package com.example.onlinetourism;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;

public class CompanyInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_information);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu3, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }
}
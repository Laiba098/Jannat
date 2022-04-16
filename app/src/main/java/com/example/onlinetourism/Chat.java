package com.example.onlinetourism;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Chat extends AppCompatActivity {
    String serviceseekername, serviceprovidername;
    String tablecustcom,tablecompcust;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Bundle bn = getIntent().getExtras();
        serviceseekername= bn.getString("serviceseekername");
        serviceprovidername= bn.getString("serviceprovidername");

        tablecompcust=serviceprovidername+serviceseekername;
        tablecustcom=serviceseekername+serviceprovidername;

        Toast.makeText(Chat.this, "Tables are:" +tablecustcom+tablecompcust, Toast.LENGTH_SHORT).show();



    }
}
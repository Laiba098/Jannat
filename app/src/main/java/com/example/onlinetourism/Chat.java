package com.example.onlinetourism;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Chat extends AppCompatActivity {
    String serviceseekername, serviceprovidername;
    String tablecustcom,tablecompcust;
    ListView listView;
    ImageView sendbtn;
    EditText messagebox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Bundle bn = getIntent().getExtras();
        serviceseekername= bn.getString("serviceseekername");
        serviceprovidername= bn.getString("serviceprovidername");
        listView=findViewById(R.id.listview);
        sendbtn=findViewById(R.id.sendbtn);
        messagebox=findViewById(R.id.messageBox);


        tablecompcust=serviceprovidername+serviceseekername;
        tablecustcom=serviceseekername+serviceprovidername;

        Toast.makeText(Chat.this, "Tables are:" +tablecustcom+tablecompcust, Toast.LENGTH_SHORT).show();



    }
}
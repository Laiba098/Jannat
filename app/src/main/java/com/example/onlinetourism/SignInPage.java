package com.example.onlinetourism;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SignInPage extends AppCompatActivity {

    Button login;
    TextView createaccount;
    EditText name,password;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    String id,check;
    long count;
    LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);


        Intent intent = getIntent();
        check=intent.getStringExtra("check");
        // Toast.makeText(SignIn.this, "You Are "+check, Toast.LENGTH_SHORT).show();
        login=findViewById(R.id.login);
        dbHelper = new DatabaseHelper(this);
        db=dbHelper.getReadableDatabase();
        createaccount=findViewById(R.id.createaccount);
        name=findViewById(R.id.name);
        password=findViewById(R.id.password);
        ll =  findViewById(R.id.layout);
        //  txt1=findViewById(R.id.txt1);
        //txt2=findViewById(R.id.txt2);
        createaccount=findViewById(R.id.createaccount);
        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(SignInPage.this,CreateAccount.class);
                intent1.putExtra("check",check);
                startActivity(intent1);
            }
        });
    }

    public void login(View view)
    {
        String[] values = {name.getText().toString(), password.getText().toString()};
        String[] columns = { "ID","Name", "Password"};
        db=dbHelper.getReadableDatabase();
        if(name.equals("") || password.equals(""))
        {
            Toast.makeText(SignInPage.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }
        else
        {


            name.setText(null);
            password.setText(null);

            if(check.equals("serviceseeker"))
            {

                Cursor cursor = db.query("ServiceSeeker", columns, "EMAIL=? AND Password=?", values, null, null, null);

                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        do{

                            id=  cursor.getString(0);
                            Intent intent = new Intent(SignInPage.this, UserHomePage.class);
                            intent.putExtra("check",check);
                            intent.putExtra("pId", id);

                            startActivity(intent);
                        }  while (cursor.moveToNext());

                    }
                    else {
                        Toast.makeText(SignInPage.this, "Wrong email or password", Toast.LENGTH_SHORT).show();
                    }
                    cursor.close();

                }

            }
            if(check.equals("serviceprovider"))
            {
                Cursor cursor = db.query("ServiceProvider", columns, "EMAIL=? AND Password=?", values, null, null, null);
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        do{

                            id=  cursor.getString(0);
                            ContentValues args = new ContentValues();
                            Intent intent = new Intent(SignInPage.this, CompanyHomePage.class);
                            intent.putExtra("check",check);
                            intent.putExtra("pId", id);
                            startActivity(intent);
                        }  while (cursor.moveToNext());

                    } else {
                        Toast.makeText(SignInPage.this, "Wrong email or password", Toast.LENGTH_SHORT).show();
                    }
                }

            }

        }
    }

}
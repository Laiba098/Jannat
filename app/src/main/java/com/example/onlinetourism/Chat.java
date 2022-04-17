package com.example.onlinetourism;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Chat extends AppCompatActivity {
    String serviceseekername, serviceprovidername,msg,usingperson;
    String tablecustcom,tablecompcust;
    ListView listView;
    ImageView sendbtn;
    EditText messagebox;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    Activity activity;
    String s1,s2;
    ArrayList<review2> rev=new ArrayList<review2>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();
        Bundle bn = getIntent().getExtras();
        serviceseekername= bn.getString("serviceseekername");
        serviceprovidername= bn.getString("serviceprovidername");
        usingperson= bn.getString("using");
        listView=findViewById(R.id.listview);
        sendbtn=findViewById(R.id.sendbtn);
        messagebox=findViewById(R.id.messageBox);


        tablecompcust=serviceprovidername+serviceseekername;
        tablecustcom=serviceseekername+serviceprovidername;

        Boolean res=isTableExists(db,tablecompcust);
        Boolean res1=isTableExists1(db,tablecustcom);
        if (res==false || res1==false) {
            db.execSQL("CREATE TABLE IF NOT EXISTS " + tablecompcust
                    + " (Sendername VARCHAR, Message VARCHAR);");
            db.execSQL("CREATE TABLE IF NOT EXISTS " + tablecustcom
                    + " (Sendername VARCHAR, Message VARCHAR);");
        }


        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getReadableDatabase();
        activity = this;

            String[] colm={"Sendername","message"};
            Cursor cr=db.query(tablecompcust,colm,null,
                     null, null, null, null);
            if (cr.getCount()==0) {
              //  Toast.makeText(getApplicationContext(),"No Record exist",Toast.LENGTH_LONG).show();
            }
            else {
                //rev.clear();
                while (cr.moveToNext()) {
                    s1 = cr.getString(0);
                    s2 = cr.getString(1);
                    review2 mObj = new review2("Message: " + s2, s1);
                    rev.add(mObj);
                }
                review2Holder rList = new review2Holder(activity, rev);


                listView.setAdapter(rList);
            }




        //Toast.makeText(Chat.this, "Tables are:" +tablecustcom+tablecompcust, Toast.LENGTH_SHORT).show();

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg=messagebox.getText().toString();
                Boolean res=isTableExists(db,tablecompcust);
                Boolean res1=isTableExists1(db,tablecustcom);
                if (res==true || res1==true)
                {
                   // Toast.makeText(Chat.this, "Table exit", Toast.LENGTH_SHORT).show();
                    db.execSQL("INSERT INTO " + tablecompcust +" (Sendername, message) "
                            + " Values ('" + usingperson + "', '" + msg + "');");
                    db.execSQL("INSERT INTO " + tablecustcom +" (Sendername, message) "
                            + " Values ('" + usingperson + "', '" + msg + "');");

                    String[] colm={"Sendername","message"};
                    Cursor cr=db.query(tablecompcust,colm,null,
                            null, null, null, null);
                    if (cr.getCount()==0) {
                        Toast.makeText(getApplicationContext(),"No Record exist",Toast.LENGTH_LONG).show();
                    }
                    else {
                        //rev.clear();
                        cr.moveToPosition(cr.getCount() - 1);
                            s1 = cr.getString(0);
                            s2 = cr.getString(1);
                            review2 mObj = new review2("Message: " + s2, s1);
                            rev.add(mObj);
                        review2Holder rList = new review2Holder(activity, rev);


                        listView.setAdapter(rList);
                        messagebox.setText(null);
                    }


                }
                else
                {
                    // Toast.makeText(Chat.this, "Table not exit", Toast.LENGTH_SHORT).show();
                    db.execSQL("CREATE TABLE IF NOT EXISTS " + tablecompcust
                            + " (Sendername VARCHAR, Message VARCHAR);");
                    db.execSQL("CREATE TABLE IF NOT EXISTS " + tablecustcom
                            + " (Sendername VARCHAR, Message VARCHAR);");
                    db.execSQL("INSERT INTO " + tablecompcust +" (Sendername, message) "
                            + " Values ('" + usingperson + "', '" + msg + "');");
                    db.execSQL("INSERT INTO " + tablecustcom +" (Sendername, message) "
                            + " Values ('" + usingperson + "', '" + msg + "');");


                    String[] colm={"Sendername","message"};
                    Cursor cr=db.query(tablecompcust,colm,null,
                            null, null, null, null);
                    if (cr.getCount()==0) {
                        //Toast.makeText(getApplicationContext(),"No Record exist",Toast.LENGTH_LONG).show();
                    }
                    else {
                        //rev.clear();
                        cr.moveToPosition(cr.getCount() - 1);
                            s1 = cr.getString(0);
                            s2 = cr.getString(1);
                            review2 mObj = new review2("Message: " + s2, s1);
                            rev.add(mObj);

                        review2Holder rList = new review2Holder(activity, rev);


                        listView.setAdapter(rList);
                        messagebox.setText(null);
                    }






                }

            }
        });



    }
    private boolean isTableExists(SQLiteDatabase db, String table){
        String sql = "SELECT name FROM sqlite_master WHERE type='table' AND name='"+tablecompcust+"'";
        Cursor mCursor = db.rawQuery(sql, null);
        if (mCursor.getCount() > 0) {
            return true;
        }
        mCursor.close();
        return false;
    }
    private boolean isTableExists1(SQLiteDatabase db, String table){
        String sql = "SELECT name FROM sqlite_master WHERE type='table' AND name='"+tablecustcom+"'";
        Cursor mCursor = db.rawQuery(sql, null);
        if (mCursor.getCount() > 0) {
            return true;
        }
        mCursor.close();
        return false;
    }
}
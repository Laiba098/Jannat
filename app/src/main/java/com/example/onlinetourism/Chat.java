package com.example.onlinetourism;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class Chat extends AppCompatActivity {
    String serviceseekername, serviceprovidername,msg,usingperson;
    String tablecustcom,tablecompcust;
    ListView listView;
    ImageView sendbtn;
    EditText messagebox;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;

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
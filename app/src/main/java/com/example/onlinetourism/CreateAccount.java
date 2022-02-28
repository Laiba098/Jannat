package com.example.onlinetourism;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class CreateAccount extends AppCompatActivity {
    EditText name, location, email, password, contact;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    TextView createaccount;
    Button btn;
    String check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        name = findViewById(R.id.name);
        location = findViewById(R.id.location);
        dbHelper = new DatabaseHelper(this);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        contact = findViewById(R.id.contactnumber);
        createaccount=findViewById(R.id.createaccount);
        //details=findViewById(R.id.details);
        btn=findViewById(R.id.btn);
        Intent intent = getIntent();
        check=intent.getStringExtra("check");
        //  Toast.makeText(CreateAccount.this, "Create Account For "+check, Toast.LENGTH_SHORT).show();


    }
    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
    public void SaveDetails(View view) {
        db = dbHelper.getWritableDatabase();
        boolean opt=false;
        boolean opt1=false;
        String Name = name.getText().toString();
        String Location = location.getText().toString();
        String Email = email.getText().toString();
        String Password = password.getText().toString();
        String ContactNumber = contact.getText().toString();
        String[] column = {"Email", "Name"};
        String MobilePattern = "[0-9,+]{13}";
        final Pattern Password_Pattern=
                Pattern.compile("^"+
                        "(?=.*[0-9])"+ //ATLEAST ONE DIGIT
                        "(?=.*[a-z])" + //ATLEAST ON LOWERCASE LETTER
                        "(?=.*[A-Z])" + //ATLEAST ONE UPPERCASE LETTER
                        "(?=.*[@#$%^&+=])" + //ATLEAST ONE SPECIAL CHARACTER
                        ".{6,}"+ //ATLEAST 6 CHARACTERS
                        "$");

        if (Name.equals("") || Location.equals("") || Email.equals("") || Password.equals("") || ContactNumber.equals("")) {
            Toast.makeText(this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
        }
        else if(Character.isDigit(Name.charAt(0)))
        {
            Toast.makeText(this, "Name must start with Alphabet", Toast.LENGTH_SHORT).show();
            name.setText(null);
            //name.setError("Name must start with AlphabetName must start with Alphabet");


        }
        else if (isEmail(email) == false) {
            // email.setError("Invalid email");
            Toast.makeText(this, "Invalid email", Toast.LENGTH_SHORT).show();
            email.setText(null);

        }
        else if (!Password_Pattern.matcher(Password).matches())
        {

            // password.setError("password too Weak\n Use atleast one digit\n one uppercase letter\n one small case letter\n one sepcial charater\n atleast 6 charaters");
            Toast.makeText(this, "password too Weak, Use atleast one digit, one uppercase letter, one small case letter, one special charater, atleast 6 charaters", Toast.LENGTH_LONG).show();
            password.setText(null);
        }
        else if(!contact.getText().toString().matches(MobilePattern)) {
            // contact.setError("Please enter valid 11 digit phone number");
            Toast.makeText(this, "Please enter valid 11 digit phone number", Toast.LENGTH_LONG).show();
            contact.setText(null);
        }

        else {


            if(check.equals("serviceseeker"))
            {
                Cursor cursor = db.query("ServiceSeeker", column, null, null, null, null, null);
                if(cursor !=null)
                {
                    while(cursor.moveToNext())
                    {
                        String s1,s2;
                        s1=cursor.getString(0);
                        s2=cursor.getString(1);
                        if(s2.equals(Name))
                        {
                            opt=true;
                        }
                        if(s1.equals(Email))
                        {
                            opt1=true;
                        }
                    }

                }
                if(opt) {
                    Toast.makeText(CreateAccount.this,"This name already exist",Toast.LENGTH_SHORT).show();
                }else if(opt1)
                {
                    Toast.makeText(CreateAccount.this,"This Email already exist",Toast.LENGTH_SHORT).show();
                }
                else
                {

                    ContentValues values = new ContentValues();
                    values.put(DatabaseContract.ServiceSeeker.COL_NAME, Name);
                    values.put(DatabaseContract.ServiceSeeker.COL_CONTACT, ContactNumber);
                    values.put(DatabaseContract.ServiceSeeker.COL_LOCATION, Location);
                    values.put(DatabaseContract.ServiceSeeker.COL_EMAIL, Email);
                    values.put(DatabaseContract.ServiceSeeker.COL_PASSWORD, Password);

                    long newRowId = db.insert(DatabaseContract.ServiceSeeker.TABLE_NAME, null, values);
                    if (newRowId > 0) {
                        Toast.makeText(CreateAccount.this, "Account Successfully Created", Toast.LENGTH_SHORT).show();
                    }
                    db.close();
                    name.setText(null);
                    location.setText(null);
                    email.setText(null);
                    contact.setText(null);
                    password.setText(null);
                    Intent intent = new Intent(CreateAccount.this, SignInPage.class);
                    //  intent.putExtra("val1", Email);
                    intent.putExtra("check", check);
                    startActivity(intent);


                }
            }

            if(check.equals("serviceprovider"))
            {
                Cursor cursor = db.query("ServiceProvider", column, null, null, null, null, null);
                if(cursor !=null)
                {
                    while(cursor.moveToNext())
                    {
                        String s1,s2;
                        s1=cursor.getString(0);
                        s2=cursor.getString(1);
                        if(s2.equals(Name))
                        {
                            opt=true;
                        }
                        if(s1.equals(Email))
                        {
                            opt1=true;
                        }
                    }

                }
                if(opt) {
                    Toast.makeText(CreateAccount.this,"This name already exist",Toast.LENGTH_SHORT).show();
                }else if(opt1)
                {
                    Toast.makeText(CreateAccount.this,"This Email already exist",Toast.LENGTH_SHORT).show();
                }
                else
                {

                    ContentValues values = new ContentValues();
                    values.put(DatabaseContract.ServiceProvider.COL_NAME, Name);
                    values.put(DatabaseContract.ServiceProvider.COL_CONTACT, ContactNumber);
                    values.put(DatabaseContract.ServiceProvider.COL_LOCATION, Location);
                    values.put(DatabaseContract.ServiceProvider.COL_EMAIL, Email);
                    values.put(DatabaseContract.ServiceProvider.COL_PASSWORD, Password);

                    long newRowId = db.insert(DatabaseContract.ServiceProvider.TABLE_NAME, null, values);
                    if (newRowId > 0) {
                        Toast.makeText(CreateAccount.this, "Account Successfully Created", Toast.LENGTH_SHORT).show();
                    }
                    db.close();
                    name.setText(null);
                    location.setText(null);
                    email.setText(null);
                    contact.setText(null);
                    password.setText(null);
                    Intent intent = new Intent(CreateAccount.this, SignInPage.class);
                    intent.putExtra("check", check);
                    startActivity(intent);

                }
            }



        }


    }
    public static boolean isValidPassword(String s) {
        Pattern PASSWORD_PATTERN
                = Pattern.compile(
                "[a-zA-Z0-9\\!\\@\\#\\$]{8,24}");

        return !TextUtils.isEmpty(s) && PASSWORD_PATTERN.matcher(s).matches();
    }
}
package com.example.onlinetourism;

import android.provider.BaseColumns;

public class DatabaseContract {
    public DatabaseContract() {}
    public static abstract class ServiceSeeker implements BaseColumns {
        public static final String TABLE_NAME = "ServiceSeeker";
        public static final String COL_ID = "ID";
        public static final String COL_NAME = "Name";
        public static final String COL_CONTACT="Contact";
        public static final String COL_LOCATION="Location";
        public static final String COL_EMAIL = "Email";
        public static final String COL_PASSWORD="Password";
    }
    public static abstract class ServiceProvider implements BaseColumns {
        public static final String TABLE_NAME = "Mechanic";
        public static final String COL_ID = "ID";
        public static final String COL_NAME = "Name";
        public static final String COL_CONTACT="Contact";
        public static final String COL_LOCATION="Location";
        public static final String COL_EMAIL = "Email";
        public static final String COL_PASSWORD="Password";

    }


}

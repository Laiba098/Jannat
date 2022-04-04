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
        public static final String TABLE_NAME = "ServiceProvider";
        public static final String COL_ID = "ID";
        public static final String COL_NAME = "Name";
        public static final String COL_CONTACT="Contact";
        public static final String COL_LOCATION="Location";
        public static final String COL_EMAIL = "Email";
        public static final String COL_PASSWORD="Password";

    }

    public static abstract class TourPlans implements BaseColumns {
        public static final String TABLE_NAME = "TourPlans";
        public static final String COL_ID = "ID";
        public static final String COL_COMPANYID = "CompanyId";
        public static final String COL_COMPANYNAME = "CompanyName";
        public static final String COL_VEHICLENAME="VehicleName";
        public static final String COL_NUMOFSEATS="NumOfSeats";
        public static final String COL_PLACE = "Place";
        public static final String COL_VEHICLERENT="VehicleRent";
        public static final String COL_ROOMRENT="RoomRent";
        public static final String COL_DRIVERRENT="DriverRent";
        public static final String COL_FOODRENT="FoodRent";

    }
}

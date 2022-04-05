package com.example.onlinetourism;


public class List1 {
    String name;
    String vehicle;
    String place;
    String id;

    public String getServiceseekerid() {
        return serviceseekerid;
    }

    public void setServiceseekerid(String serviceseekerid) {
        this.serviceseekerid = serviceseekerid;
    }

    public String getServiceproviderid() {
        return serviceproviderid;
    }

    public void setServiceproviderid(String serviceproviderid) {
        this.serviceproviderid = serviceproviderid;
    }

    String serviceseekerid, serviceproviderid;



    public List1(String name, String vehicle, String place, String id) {
        this.name = name;
        this.vehicle = vehicle;
        this.place=place;
        this.place=id;

    }
    public List1(String name, String vehicle, String place) {
        this.name = name;
        this.vehicle = vehicle;
        this.place=place;

    }
    public List1(String serviceseekerid) {
        this.serviceseekerid = serviceseekerid;

    }
    public String getName() {
        return name;
    }
    public void setName(String nm) {
        this.name = nm;
    }
    public String getVehicle() {
        return vehicle;
    }
    public void setVehicle(String cont)
    {
        this.vehicle = cont;
    }
    public String getPlace() {
        return place;
    }
    public void setPlace(String ord)
    {
        this.place = ord;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Override
    public String toString()
    { return "Name : "+name+" Place : "+place;}
}

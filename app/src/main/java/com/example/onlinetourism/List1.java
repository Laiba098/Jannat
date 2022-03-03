package com.example.onlinetourism;


public class List1 {
    String name;
    String vehicle;
    String place;

    public List1(String name,String vehicle,String place) {
        this.name = name;
        this.vehicle = vehicle;
        this.place=place;
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
    @Override
    public String toString()
    { return "Name : "+name+" Place : "+place;}
}

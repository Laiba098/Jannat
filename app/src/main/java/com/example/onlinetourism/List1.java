package com.example.onlinetourism;


public class List1 {
    String name;
    String vehicle;
    String place;
    String id;

    public String getPlace6() {
        return place6;
    }

    public void setPlace6(String place6) {
        this.place6 = place6;
    }

    public String getReview6() {
        return review6;
    }

    public void setReview6(String review6) {
        this.review6 = review6;
    }

    public String getPlaceby6() {
        return placeby6;
    }

    public void setPlaceby6(String placeby6) {
        this.placeby6 = placeby6;
    }

    public List1(String place6, String review6, String placeby6, String dummy6, String dummy16) {
        this.place6 = place6;
        this.review6 = review6;
        this.placeby6 = placeby6;
        this.dummy6 = dummy6;
        this.dummy16 = dummy16;
    }

    String place6,review6,placeby6,dummy6,dummy16;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    String contact;

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
    public List1(String name, String contact) {
        this.name = name;
        this.contact= contact;

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

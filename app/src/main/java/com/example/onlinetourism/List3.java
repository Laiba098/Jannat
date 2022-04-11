package com.example.onlinetourism;

public class List3 {
    public List3(String placeby, String place, String review) {
        this.placeby = placeby;
        this.place = place;
        this.review = review;
    }

    String placeby;
    String place;

    public String getPlaceby() {
        return placeby;
    }

    public void setPlaceby(String placeby) {
        this.placeby = placeby;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    String review;
}

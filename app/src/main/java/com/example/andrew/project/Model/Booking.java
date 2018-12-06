package com.example.andrew.project.Model;

public class Booking {

    private ServiceProvider provider;
    private HomeOwner booker;
    private int rating;
    private int time;

    public Booking () {
        return;
    }

    public Booking(ServiceProvider provider, HomeOwner booker) {
        this.provider = provider;
        this.booker = booker;
    }

    public ServiceProvider getProvider () {
        return provider;
    }

    public HomeOwner getBooker () {
        return booker;
    }

    public int getRating() {
        return rating;
    }

    public int getTime () {
        return time;
    }


    public void setProvider(ServiceProvider provider) {
        this.provider = provider;
    }

    public void setBooker (HomeOwner booker) {
        this.booker = booker;
    }

    public void setRating (int rating) {
        this.rating = rating;
    }

    public void setTime (int time) {
        this.time = time;
    }



}

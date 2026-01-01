package com.example.bookevents.model;


public class Gallery {

    private String galleryNo;
    private int totalSeats;
    private int availableSeats;
    private double price;

    public Gallery(){

    }

    public Gallery(String galleryNo, int totalSeats, double price) {
        this.galleryNo = galleryNo;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.price = price;
    }

    public String getGalleryNo() {
        return galleryNo;
    }

    public void setGalleryNo(String galleryNo) {
        this.galleryNo = galleryNo;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

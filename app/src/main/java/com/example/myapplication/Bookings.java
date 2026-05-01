package com.example.myapplication;

public class Bookings {
    private String bookingId;
    private String userId;
    private String movieName;
    private String moviePoster;
    private int seats;
    private int totalPrice;
    private String dateTime;
    private long timestamp;

    public Bookings() {}

    public Bookings(String userId, String movieName, String moviePoster, int seats, int totalPrice, String dateTime, long timestamp) {
        this.userId = userId;
        this.movieName = movieName;
        this.moviePoster = moviePoster;
        this.seats = seats;
        this.totalPrice = totalPrice;
        this.dateTime = dateTime;
        this.timestamp = timestamp;
    }

    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }

    public String getUserId() { return userId; }
    public String getMovieName() { return movieName; }
    public String getMoviePoster() { return moviePoster; }
    public int getSeats() { return seats; }
    public int getTotalPrice() { return totalPrice; }
    public String getDateTime() { return dateTime; }
    public long getTimestamp() { return timestamp; }

    public void setUserId(String userId) { this.userId = userId; }
    public void setMovieName(String movieName) { this.movieName = movieName; }
    public void setMoviePoster(String moviePoster) { this.moviePoster = moviePoster; }
    public void setSeats(int seats) { this.seats = seats; }
    public void setTotalPrice(int totalPrice) { this.totalPrice = totalPrice; }
    public void setDateTime(String dateTime) { this.dateTime = dateTime; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
}
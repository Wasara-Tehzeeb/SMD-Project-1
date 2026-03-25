package com.example.myapplication;

public class Movie {
    private int poster;
    private String name;
    private String genre;
    private String trailerUrl;
    public Movie(int poster, String name, String genre, String trailerUrl) {
        this.poster = poster;
        this.name = name;
        this.genre = genre;
        this.trailerUrl = trailerUrl;
    }

    public int getPoster() {
        return poster;
    }
    public String getName() {
        return name;
    }
    public String getGenre() {
        return genre;
    }
    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }
}

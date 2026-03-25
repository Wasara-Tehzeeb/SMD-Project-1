package com.example.myapplication;

public class Movie {
    private int poster;
    private String name;
    private String genre;
    public Movie(int poster, String name, String genre) {
        this.poster = poster;
        this.name = name;
        this.genre = genre;
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

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}

package com.example.testaplication.Adapter;

public class Review {
    private String username;
    private String name_manga;
    private String commnent;
    private int id;

    public Review(int id,String username, String name_manga, String commnent) {
        this.username = username;
        this.name_manga = name_manga;
        this.commnent = commnent;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName_manga() {
        return name_manga;
    }

    public void setName_manga(String name_manga) {
        this.name_manga = name_manga;
    }

    public String getCommnent() {
        return commnent;
    }

    public void setCommnent(String commnent) {
        this.commnent = commnent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

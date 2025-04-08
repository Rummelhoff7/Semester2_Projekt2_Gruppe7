package org.example.semester2_projekt2_gruppe7.model;

public class Wish {
    int id;
    int user_id;
    String name;
    String description;
    String img;
    double price;

    public Wish(int id, int user_id, String name, String description, String img) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.description = description;
        this.img = img;
        this.price = price;

    }

    public Wish() {}


    public Wish(String name, String description, String img) {
        this.name = name;
        this.description = description;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String toString(){
        return "id "+id+ "user_id"+user_id+"name"+name +"description"+description+"img "+img;
    }

    }





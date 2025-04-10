package org.example.semester2_projekt2_gruppe7.model;

public class Wishidea {
    private int id;
    private int wishlist_id;
    private int user_id;
    private String title;
    private String description;

    public Wishidea(int id, int wishlist_id, int user_id, String title, String description) {
        this.id = id;
        this.wishlist_id = wishlist_id;
        this.user_id = user_id;
        this.title = title;
        this.description = description;
    }

    public Wishidea(int wishlist_id, int user_id, String title, String description) {
        this.wishlist_id = wishlist_id;
        this.user_id = user_id;
        this.title = title;
        this.description = description;
    }

    public Wishidea(int id, String title, String description) {
        this.id=id;
        this.title = title;
        this.description = description;
    }

    public Wishidea() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWishlist_id() {
        return wishlist_id;
    }

    public void setWishlist_id(int wishlist_id) {
        this.wishlist_id = wishlist_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}

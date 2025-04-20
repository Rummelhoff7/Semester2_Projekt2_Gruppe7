package org.example.semester2_projekt2_gruppe7.model;

public class Wishidea {
    private int id;
    private int wishlist_id;
    private String title;
    private String description;

    public Wishidea(int wishlistId, String title, String description) {
        this.wishlist_id = wishlistId;
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

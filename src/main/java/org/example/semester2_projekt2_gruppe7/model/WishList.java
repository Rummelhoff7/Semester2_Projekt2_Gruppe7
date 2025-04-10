package org.example.semester2_projekt2_gruppe7.model;

public class WishList {
    private int id;
    private int user_id;
    private String name;
    private String img;

    public WishList(int id, int user_id, String name, String img) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.img = img;
    }


    public WishList(int id, int user_id, String name) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
    }
    public WishList() {
    }

    public WishList(int user_id, String name, String img) {
        this.user_id = user_id;
        this.name = name;
        this.img = img;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "WishList{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                '}'
                ;
    }
}


package org.example.semester2_projekt2_gruppe7.repository;

import org.example.semester2_projekt2_gruppe7.model.Wish;
import org.example.semester2_projekt2_gruppe7.model.Wishidea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class WishRepository {

    @Autowired
    private DataSource dataSource;

    public ArrayList<Wish> getAllWish() {
        ArrayList<Wish> wishes = new ArrayList<>();
        String sql = "SELECT * FROM wish";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Wish wish = new Wish();
                wish.setId(resultSet.getInt("id"));
                wish.setName(resultSet.getString("name"));
                wish.setDescription(resultSet.getString("description"));
                wish.setPrice(resultSet.getDouble("price"));
                wish.setWishlist_id(resultSet.getInt("wishlist_id"));
                wish.setImg(resultSet.getString("img"));
                wishes.add(wish);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wishes;
    }

    public Wish getWishById(int id){
        Wish wish = new Wish();
        String sql = "SELECT * FROM wish WHERE id = ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    wish.setId(resultSet.getInt("id"));
                    wish.setName(resultSet.getString("name"));
                    wish.setDescription(resultSet.getString("description"));
                    wish.setImg(resultSet.getString("img"));
                    wish.setPrice(resultSet.getDouble("price"));
                    //wishes.add(wish);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return wish;
    }

    public ArrayList<Wish> getWishByWistList_id(int wishlist_id){
        ArrayList<Wish> wishList = new ArrayList<>();
    String sql = "SELECT * FROM wish WHERE wishlist_id = ?";

        try(Connection connection = dataSource.getConnection();
    PreparedStatement statement = connection.prepareStatement(sql)){

        statement.setInt(1, wishlist_id);

        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Wish wish = new Wish();
                wish.setWishlist_id(resultSet.getInt("wishlist_id"));
                wish.setId(resultSet.getInt("id"));
                wish.setName(resultSet.getString("name"));
                wish.setDescription(resultSet.getString("description"));
                wish.setImg(resultSet.getString("img"));
                wish.setPrice(resultSet.getDouble("price"));
                wishList.add(wish);
            }
        }
    }catch (SQLException e){
        e.printStackTrace();
    }
        return wishList;
}


    public void deleteWish (int id) {
        String sql = "DELETE FROM wish WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save (Wish wish){
        String sql = "INSERT INTO wish(wishlist_id, name, description, img, price) VALUES (?, ?, ?, ?,?)";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, wish.getWishlist_id());
            statement.setString(2, wish.getName());
            statement.setString(3, wish.getDescription());
            statement.setString(4,wish.getImg());
            statement.setDouble(5,wish.getPrice());

            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void update (Wish updatedWish) {
        String sql = "UPDATE wish SET name = ?, description = ?, img = ?, price = ? WHERE id = ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, updatedWish.getName());
            statement.setString(2, updatedWish.getDescription());
            statement.setString(3, updatedWish.getImg());
            statement.setDouble(4, updatedWish.getPrice());
            statement.setInt(5, updatedWish.getId());

            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}


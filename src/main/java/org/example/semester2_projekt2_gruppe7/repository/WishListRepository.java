package org.example.semester2_projekt2_gruppe7.repository;
import org.example.semester2_projekt2_gruppe7.model.Wish;
import org.example.semester2_projekt2_gruppe7.model.WishList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

@Repository
public class WishListRepository {

    // Dette repository bruges når databasen er tilkoblet.

    @Autowired
    private DataSource dataSource;

    public ArrayList<WishList> getAllWishList() {
        ArrayList<WishList> wishlisting = new ArrayList<>();
        String sql = "SELECT * FROM wishList";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                WishList wishList = new WishList();
                wishList.setId(resultSet.getInt("id"));
                wishList.setUser_id(resultSet.getInt("user_id"));
                wishList.setName(resultSet.getString("name"));
                wishList.setImg(resultSet.getString("img"));

                wishlisting.add(wishList); //måske ikke 1
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return wishlisting;
    }

    public void save(WishList wishList) {
        String sql = "INSERT INTO wishList (user_id, name, img) VALUES (?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(2, wishList.getUser_id());
            statement.setString(3, wishList.getName());
            statement.setString(4, wishList.getImg());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM wishList WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public WishList getWishListById(int id) {
        WishList wishList = new WishList();
        String sql = "SELECT * FROM wishList WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    wishList.setId(resultSet.getInt("id"));
                    wishList.setUser_id(resultSet.getInt("user_id"));
                    wishList.setName(resultSet.getString("name"));
                    wishList.setImg(resultSet.getString("img"));



                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return wishList;
    }

    public void update(WishList wishList) {
        String sql = "UPDATE wishList SET user_id = ?, name = ?, img = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, wishList.getUser_id());
            statement.setString(2, wishList.getName());
            statement.setString(3, wishList.getImg());
            statement.setInt(4, wishList.getId());



            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
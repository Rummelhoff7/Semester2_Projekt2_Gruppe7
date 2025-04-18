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

            statement.setInt(1, wishList.getUser_id());
            statement.setString(2, wishList.getName());
            statement.setString(3, wishList.getImg());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String deleteWishesSql = "DELETE FROM wish WHERE wishlist_id = ?";
        String deleteWishlistSql = "DELETE FROM wishlist WHERE id = ?";

        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement wishStmt = connection.prepareStatement(deleteWishesSql);
                 PreparedStatement wishlistStmt = connection.prepareStatement(deleteWishlistSql)) {

                wishStmt.setInt(1, id);
                wishStmt.executeUpdate();

                wishlistStmt.setInt(1, id);
                wishlistStmt.executeUpdate();

                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
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

    public ArrayList<WishList> getWishListbyUser_id(int user_id) {
        ArrayList<WishList> wishListing = new ArrayList<>();
        String sql = "SELECT * FROM wishList WHERE user_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, user_id);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    WishList wishList = new WishList();
                    wishList.setUser_id(resultSet.getInt("user_id"));
                    wishList.setId(resultSet.getInt("id"));
                    wishList.setName(resultSet.getString("name"));
                    wishList.setImg(resultSet.getString("img"));
                    wishListing.add(wishList);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return wishListing;
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
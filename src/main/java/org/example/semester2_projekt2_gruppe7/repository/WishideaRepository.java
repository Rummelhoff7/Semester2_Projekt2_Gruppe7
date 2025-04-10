package org.example.semester2_projekt2_gruppe7.repository;

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
public class WishideaRepository {

    @Autowired
    private DataSource dataSource;

    public ArrayList<Wishidea> getAllWishidea() {
        ArrayList<Wishidea> wishideaList = new ArrayList<>();
        String sql = "SELECT * FROM wishidea";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()){

            while(resultSet.next()){
                Wishidea wishidea = new Wishidea();
                wishidea.setId(resultSet.getInt("id"));
                wishidea.setWishlist_id(resultSet.getInt("wishlist_id"));
                wishidea.setUser_id(resultSet.getInt("user_id"));
                wishidea.setTitle(resultSet.getString("title"));
                wishidea.setDescription(resultSet.getString("description"));
                wishideaList.add(wishidea);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return wishideaList;
    }



    public Wishidea getWishideabyID(int id){
        Wishidea wishidea = new Wishidea();
        String sql = "SELECT * FROM wishidea WHERE id = ?";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, id);

            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    wishidea.setId(resultSet.getInt("id"));
                    wishidea.setWishlist_id(resultSet.getInt("wishlist_id"));
                    wishidea.setUser_id(resultSet.getInt("user_id"));
                    wishidea.setTitle(resultSet.getString("title"));
                    wishidea.setDescription(resultSet.getString("description"));
                }
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return wishidea;
    }

    public ArrayList<Wishidea> getWishideaby_wistlist_idANDuser_id(int wishlist_id, int user_id) {
        ArrayList<Wishidea> wishideaList = new ArrayList<>();

        String sql = "SELECT * FROM wishidea WHERE wishlist_id = ? and user_id = ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, wishlist_id);
            statement.setInt(2, user_id);

            try(ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()){
                    Wishidea wishidea = new Wishidea();
                    wishidea.setId(resultSet.getInt("id"));
                    wishidea.setWishlist_id(resultSet.getInt("wishlist_id"));
                    wishidea.setWishlist_id(resultSet.getInt("wishlist_id"));
                    wishidea.setTitle(resultSet.getString("title"));
                    wishidea.setDescription(resultSet.getString("description"));
                    wishideaList.add(wishidea);
                }
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return wishideaList;
    }

    public void deleteWishidea(int id){
        String sql = "DELETE FROM wishidea WHERE id = ?";


        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void save(Wishidea wishidea){
        String sql = "INSERT INTO wishidea(wishlist_id, user_id, title, description) VALUES(?,?,?,?)";



        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            //Here you can write java code to wishlist_id and user_id

            statement.setInt(1, wishidea.getWishlist_id());
            statement.setInt(2, wishidea.getUser_id());


            statement.setString(3, wishidea.getTitle());
            statement.setString(4, wishidea.getDescription());

            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void update(Wishidea updatedWishidea){
        String sql = "UPDATE wishidea SET title = ?, description = ? WHERE id = ?";

        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, updatedWishidea.getTitle());
            statement.setString(2, updatedWishidea.getDescription());
            statement.setInt(3, updatedWishidea.getId());

            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}

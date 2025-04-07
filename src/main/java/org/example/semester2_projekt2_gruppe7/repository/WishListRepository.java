
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
    public ArrayList<WishList> getAllCars() {
        ArrayList<WishList> wishList = new ArrayList<>();
        String sql = "SELECT * FROM WishList";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                WishList wishList1 = new WishList();
                wishList1.setId(resultSet.getInt("id"));
                wishList1.setUser_id(resultSet.getInt("user_id"));
                wishList1.setName(resultSet.getString("name"));
                wishList1.setImg(resultSet.getString("img"));

                wishList.add(wishList1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return wishList;
    }

    public void save(WishList wishList) {
        String sql = "INSERT INTO cars (id, userid, name,img) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, wishList.getId());
            statement.setInt(2, wishList.getUser_id());
            statement.setString(3, wishList.getName());
            statement.setString(4, wishList.getImg());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM WishList WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public WishList getCarById(int id) {
        WishList wishList = null;
        String sql = "SELECT * FROM cars WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    wishList = new WishList();
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
        String sql = "UPDATE cars SET brand = ?, modelyear = ?, type = ?, colour = ?, licenseplate = ?, img = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, wishList.getId());
            statement.setInt(2, wishList.getUser_id());
            statement.setString(3, wishList.getName());
            statement.setString(4, wishList.getImg());


            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
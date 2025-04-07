package org.example.semester2_projekt2_gruppe7.repository;

import org.example.semester2_projekt2_gruppe7.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class UserRepository {

    @Autowired
    private DataSource dataSource;

    public ArrayList<User> getAllUsers() {
        ArrayList<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM user";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql); // PreparedStatement hjælper med at undgå SQL-injections.
            ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString ("name"));
                user.setPassword(resultSet.getString ("password"));
                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;

    }


    public User getUserByID (int id) {
        User user = new User();
        // sql "id" strengen henvender sig til databasen. ? Er der hvor vi sætter id i metoden
        String sql = "SELECT * FROM user WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            // executeQuery = Forespørgsel til databasen om User id
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString ("name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;

    }


    //HER SKAL OGSÅ PASSWORD BRUGES
    public void deleteUser (int id){
        // sql "id" strengen henvender sig til databasen. ? Er der hvor vi sætter id i metoden
        String sql = "DELETE FROM user WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            //Parameter index 1 er = ?
            statement.setInt(1, id);

            // executeUpdate = Ændrer og opdaterer databasen.
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void saveUser (User user){
        //Skriver ikke id, da sql database-id er på AUTO INCREMENT.
      String sql = "INSERT INTO user (name, password) VALUES (?,?)";

      try (Connection connection = dataSource.getConnection();
           PreparedStatement statement = connection.prepareStatement(sql)) {

          statement.setString(1, user.getName());
          statement.setString(2, user.getPassword());

          statement.executeUpdate();


      } catch (SQLException e) {
          e.printStackTrace();
      }
    }


    public void updateUser (User updatedUser) {
    String sql = "UPDATE user SET name = ?, password = ? WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1,updatedUser.getName());
            statement.setString(2, updatedUser.getPassword());

            // executeUpdate = Ændrer og opdaterer databasen.
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

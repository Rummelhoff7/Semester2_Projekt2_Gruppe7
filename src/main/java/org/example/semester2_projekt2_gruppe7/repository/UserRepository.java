package org.example.semester2_projekt2_gruppe7.repository;

import org.example.semester2_projekt2_gruppe7.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
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

    // Metode til at finde User i databasen
    public int authenticateUser(String name, String password) {
        String sql = "SELECT * FROM user WHERE name = ? AND password = ?";

        // Connect til database
        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {

            // ? placeholders bliver sat ind i statements
            statement.setString(1, name);
            statement.setString(2, password);

            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
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



    public boolean saveUser (User user){
        //Skriver ikke id, da sql database-id er på AUTO INCREMENT.
      String sql = "INSERT INTO user (name, password) VALUES (?,?)";

      try (Connection connection = dataSource.getConnection();
           PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

          statement.setString(1, user.getName());
          statement.setString(2, user.getPassword());




          // Her har jeg gjort man får id fra den nye User man har lavet ind i klassen så den bliver referatet til når man linker til wishlist.html
          int rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
              return false;
          }

          try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
              if (generatedKeys.next()) {
                  int generatedId = generatedKeys.getInt(1);
                  user.setId(generatedId);
              }
          }





          return true;

          //Denne exception hjælper med at håndtere sql, når et username allerede findes.
          // I sql.test står den som unique.
      } catch (SQLIntegrityConstraintViolationException e) {
          System.out.println("Username already exists");
          return false;

      } catch (SQLException e) {
          e.printStackTrace();
      } return false;
    }


    public void updateUser (User updatedUser) {
    String sql = "UPDATE user SET name = ?, password = ? WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1,updatedUser.getName());
            statement.setString(2, updatedUser.getPassword());
            statement.setInt(3, updatedUser.getId());

            // executeUpdate = Ændrer og opdaterer databasen.
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

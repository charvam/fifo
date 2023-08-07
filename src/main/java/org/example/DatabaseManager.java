package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private Connection connection;

    public DatabaseManager() {
        try {
            connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");
            createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(
                    "CREATE TABLE IF NOT EXISTS SUSERS (" +
                            "USER_ID INT PRIMARY KEY, " +
                            "USER_GUID VARCHAR(255), " +
                            "USER_NAME VARCHAR(255)" +
                            ")"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO SUSERS (USER_ID, USER_GUID, USER_NAME) VALUES (?, ?, ?)"
        )) {
            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.setString(2, user.getUserGuid());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM SUSERS");
            while (resultSet.next()) {
                int userId = resultSet.getInt("USER_ID");
                String userGuid = resultSet.getString("USER_GUID");
                String userName = resultSet.getString("USER_NAME");
                User user = new User(userId, userGuid, userName);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void deleteAllUsers() {
        try (Statement statement = connection.createStatement();) {
            statement.execute("DELETE FROM SUSERS");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



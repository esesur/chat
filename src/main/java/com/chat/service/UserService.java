package com.chat.service;

import com.chat.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserService {
    private final List<User> users = new ArrayList<>();

    private Connection connection;
    private Statement statement;

    public UserService(ConnectionManager connectionManager) {
        try {
            connection = connectionManager.getConnection();
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addUser(User user) {
        String query = "INSERT INTO Users SELECT '%s' WHERE NOT EXISTS (SELECT '%s' FROM Users WHERE username='%s');"
                .formatted(user.getName(), user.getName(), user.getName());
        try {
            statement.executeUpdate(query);
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    public void removeUser(User user) {
        String query = "DELETE FROM Users WHERE username='%s' ;".formatted(user.getName());
        try {
            statement.executeUpdate(query);
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    public List<User> getUsers() {
        return this.users; // TODO
    }
}

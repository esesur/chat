package com.chat.service;

import com.chat.model.Message;
import com.chat.model.User;
import com.chat.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Service
public class MessageService {
    private MessageRepository messageRepository;

    private Connection connection;
    private Statement statement;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

//    public MessageService(ConnectionManager connectionManager) {
//        try {
//            connection = connectionManager.getConnection();
//            statement = connection.createStatement();
//            initDb();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public void addMessage(Message message) {
        messageRepository.save(message);
    }

//    public void addMessage(Message message) {
//        String username = message.getSender().getName();
//        String content = message.getContent();
//        Long timestamp = message.getTimestamp();
//        String query = "INSERT INTO Messages VALUES ('%s', '%s', '%d');".formatted(username, content, timestamp);
//        try {
//            statement.executeUpdate(query);
//        } catch (SQLException sqlException) {
//            throw new RuntimeException(sqlException);
//        }

    public List<Message> getMessages() {
        String query = "SELECT * FROM Messages;";
        List<Message> messages = new LinkedList<>();
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User user = new User(resultSet.getString("username"));
                String text = resultSet.getString("message");
                Message message = new Message(user.getId(), text);
//                message.setTimeSent(resultSet.getLong("time"));
                messages.add(message);
            }
            return messages;
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }
//    }

    public void clearMessages() {
        String query = "DELETE FROM Messages;";
        try {
            statement.execute(query);
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    private void initDb() {
        try {
            statement.execute("DROP TABLE IF EXISTS Users CASCADE;");
            statement.execute("DROP TABLE IF EXISTS Messages CASCADE;");
            statement.execute("CREATE TABLE IF NOT EXISTS Users (username TEXT PRIMARY KEY NOT NULL);");
            statement.execute("""
                    CREATE TABLE IF NOT EXISTS Messages (username TEXT NOT NULL,
                                                     message TEXT NOT NULL,
                                                     time bigint NOT NULL,
                                                     FOREIGN KEY (username) REFERENCES Users(username));""");
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }
}

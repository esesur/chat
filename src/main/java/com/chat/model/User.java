package com.chat.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
public class User {
    @Id
    private long id;
    private final String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public long getId() {
        return this.id;
    }
}

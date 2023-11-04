package com.chat.repository;

import com.chat.model.Message;
import com.chat.model.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends ListCrudRepository<Message, User> {

}

package com.studiant.com.domain.repository;

import com.studiant.com.domain.model.User;

/**
 * A repository that is responsible for getting our welcome message.
 */
public interface UserRepository {

    void insertUser(User user);
    User getConnectedProfile();


}

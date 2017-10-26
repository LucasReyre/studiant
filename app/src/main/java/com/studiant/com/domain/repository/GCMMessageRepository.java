package com.studiant.com.domain.repository;

import com.studiant.com.domain.model.Job;
import com.studiant.com.domain.model.User;

import java.util.ArrayList;

/**
 * A repository that is responsible for getting our welcome message.
 */
public interface GCMMessageRepository {

    void sendNotification(String token, String body);
}

package com.studiant.com.domain.repository;

import com.studiant.com.domain.model.Job;

/**
 * A repository that is responsible for getting our welcome message.
 */
public interface JobRepository {

    void insertJob(Job job);

}

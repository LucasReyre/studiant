package com.studiant.com.domain.repository;

import com.studiant.com.domain.model.Job;
import com.studiant.com.domain.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * A repository that is responsible for getting our welcome message.
 */
public interface JobRepository {

    void insertJob(Job job);
    ArrayList<Job> getJobsByUser(User user);
    ArrayList<Job> getJobs();


}

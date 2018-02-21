package com.studiant.com.domain.repository;

import com.studiant.com.domain.model.Job;
import com.studiant.com.domain.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A repository that is responsible for getting our welcome message.
 */
public interface JobRepository {

    void insertJob(Job job);
    ArrayList<Job> getJobsByUser(User user);
    ArrayList<Job> getHistoriqueJobsByUser(User user);
    ArrayList<Job> getJobsWithFilter(Map<String, String> filterMap);
    void deleteJob(String idJob);
    ArrayList<Job> getJobs();
    Job updateJob(Job job);


}

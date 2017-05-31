package com.studiant.com.domain.model;

/**
 * Created by lucas on 30/05/2017.
 */

public class Postulant {
    private String id;
    private String timePostulant;
    private String statutPostulant;
    private User user;
    private Job job;

    public Postulant() {
    }

    public Postulant(String id, String timePostulant, String statutPostulant, User user, Job job) {
        this.id = id;
        this.timePostulant = timePostulant;
        this.statutPostulant = statutPostulant;
        this.user = user;
        this.job = job;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimePostulant() {
        return timePostulant;
    }

    public void setTimePostulant(String timePostulant) {
        this.timePostulant = timePostulant;
    }

    public String getStatutPostulant() {
        return statutPostulant;
    }

    public void setStatutPostulant(String statutPostulant) {
        this.statutPostulant = statutPostulant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}

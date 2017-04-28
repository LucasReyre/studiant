package com.studiant.com.storage.model;

import java.net.URI;

/**
 * Created by lucas on 28/04/2017.
 */

public class User {
    private String name;
    private String email;
    private int id;
    private URI profilePicture;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public URI getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(URI profilePicture) {
        this.profilePicture = profilePicture;
    }

    public User(String name, String email, int id, URI profilePicture) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.profilePicture = profilePicture;
    }
}

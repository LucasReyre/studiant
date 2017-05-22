package com.studiant.com.domain.model;

import android.net.Uri;

import java.io.Serializable;
import java.net.URI;

/**
 * Created by lucas on 28/04/2017.
 */

public class User implements Serializable{
    private String firstName;
    private String lastName;
    private String email;
    private String idExterne;
    private String profilePicture;
    private String birthday;
    private int age;
    private String description;
    private boolean permis;
    private String diplome;
    private int typeConnexion;
    private int typeUser;


    public User() {
    }

    public User(String firstName, String lastName, String email, String idExterne, String profilePicture, String birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.idExterne = idExterne;
        this.profilePicture = profilePicture;
        this.birthday = birthday;
    }

    public User(String firstName, String lastName, String email, String idExterne, String profilePicture, String birthday, String description, boolean permis, String diplome, int typeConnexion, int typeUser) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.idExterne = idExterne;
        this.profilePicture = profilePicture;
        this.birthday = birthday;
        this.description = description;
        this.permis = permis;
        this.diplome = diplome;
        this.typeConnexion = typeConnexion;
        this.typeUser = typeUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdExterne() {
        return idExterne;
    }

    public void setIdExterne(String idExterne) {
        this.idExterne = idExterne;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPermis() {
        return permis;
    }

    public void setPermis(boolean permis) {
        this.permis = permis;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public int getTypeConnexion() {
        return typeConnexion;
    }

    public void setTypeConnexion(int typeConnexion) {
        this.typeConnexion = typeConnexion;
    }

    public int getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(int typeUser) {
        this.typeUser = typeUser;
    }


}

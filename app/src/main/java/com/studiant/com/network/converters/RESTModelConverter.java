package com.studiant.com.network.converters;

import android.net.Uri;
import android.util.Log;

import com.studiant.com.domain.model.Job;
import com.studiant.com.domain.model.User;
import com.studiant.com.network.model.RESTJob;
import com.studiant.com.network.model.RESTUtilisateur;

import java.util.ArrayList;

/**
 * Created by groupama on 12/05/2017.
 */

public class RESTModelConverter {


    public static User convertToUserModel(RESTUtilisateur restUtilisateur){
        String id = restUtilisateur.getmId();
        String nomUser = restUtilisateur.getmNomUtilisateur();
        String prenomUser = restUtilisateur.getmPrenomUtilisateur();
        String dateNaissanceUser = restUtilisateur.getmDateNaissanceUtilisateur();
        String photoUser = restUtilisateur.getmPhotoUtilisateur();
        String mailUser = restUtilisateur.getmMailUtilisateur();
        int typeUser = restUtilisateur.getmTypeUtilisateur();
        String idExterne = restUtilisateur.getmIdExterneUtilisateur();
        int typeConnexion = restUtilisateur.getmTypeConnexionUtilisateur();
        String descriptionUser = restUtilisateur.getmDescriptionUtilisateur();
        String diplomeUser = restUtilisateur.getmDiplomeUtilisateur();
        boolean permisUser = restUtilisateur.getmPermisUtilisateur();

        return new User(id, prenomUser, nomUser, mailUser,idExterne, photoUser, dateNaissanceUser, descriptionUser, permisUser, diplomeUser,typeConnexion, typeUser);
        //return new User(prenomUser,nomUser,mailUser,idExterne,photoUser, dateNaissanceUser);
    }


    public static RESTUtilisateur convertToRestUserModel(User user) {

        String nomUser = user.getLastName();
        String prenomUser = user.getFirstName();
        String dateNaissanceUser = user.getBirthday();
        String photoUser = user.getProfilePicture();
        String mailUser = user.getEmail();
        int typeUser = user.getTypeUser();
        String idExterne = user.getIdExterne();
        int typeConnexion = user.getTypeConnexion();
        String descriptionUser = user.getDescription();
        String diplomeUser = user.getDiplome();
        boolean permisUser = user.isPermis();

        return new RESTUtilisateur( nomUser,
                                    prenomUser,
                                    dateNaissanceUser,
                                    photoUser,
                                    mailUser,
                                    typeUser,
                                    idExterne,
                                    typeConnexion,
                                    descriptionUser,
                                    diplomeUser,
                                    permisUser);

    }

    public static RESTJob convertToRestJobModel(Job job) {

        String mDescription = job.getDescription();
        String mPrix = job.getPrix();
        String mAdresseJob = job.getAdresse();
        String mDate = job.getDate();
        String mHeure = job.getHeure();
        String mUtilisateurId = job.getUtilisateurId();

        return new RESTJob(mDescription,mPrix,mAdresseJob,mDate,mHeure,mUtilisateurId);

    }

    public static Job convertToJobModel(RESTJob restJob){
        String id = restJob.getmId();
        String prix = restJob.getmPrixJob();
        String description = restJob.getmDescriptionJob();
        String adresse = restJob.getmAdresseJob();
        String date = restJob.getmDateJob();
        String heure = restJob.getmHeureJob();
        String utilisateurId = restJob.getmUtilisateurId();

        Job job =  new Job(id,description,prix ,adresse, date, heure, utilisateurId);
        if (restJob.getmRestUtilisateur()!=null){
            User utilisateur = convertToUserModel(restJob.getmRestUtilisateur());
            job.setUtilisateur(utilisateur);
        }
        return job;

    }


    public static ArrayList<Job> convertToArrayListJobModel(ArrayList<RESTJob> restJobArrayList){
        ArrayList<Job> jobArrayList = new ArrayList<Job>();
        for (int i = 0; i<restJobArrayList.size();i++){
            jobArrayList.add(convertToJobModel(restJobArrayList.get(i)));
        }
        return jobArrayList;
    }


}

package com.studiant.com.presentation.presenters.converters;

import com.studiant.com.domain.model.Job;
import com.studiant.com.domain.model.User;

import java.util.ArrayList;

/**
 * Created by groupama on 12/05/2017.
 */

public class PresentationModelConverter {


    public static com.studiant.com.presentation.presenters.model.User convertToUserPresenterModel(User user){
        if (user == null)
            return null;

        com.studiant.com.presentation.presenters.model.User mUser = new com.studiant.com.presentation.presenters.model.User(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getIdExterne(),
                user.getProfilePicture(),
                user.getBirthday(),
                user.getDescription(),
                user.isPermis(),
                user.getDiplome(),
                user.getTypeConnexion(),
                user.getTypeUser(),
                user.getFirebaseToken());

        if (user.getIdMangoPay() != null)
            mUser.setIdMangoPay(user.getIdMangoPay());

        if (user.getTelephone() != null)
            mUser.setTelephone(user.getTelephone());

        if (user.getIdWallet() != null)
            mUser.setIdWallet(user.getIdWallet());

        if (user.getIdIban() != null)
            mUser.setIdIban(user.getIdIban());

        return mUser;
    }



    public static ArrayList<com.studiant.com.presentation.presenters.model.User> convertToArrayListPresenterUserModel(ArrayList<User> domainUserArrayList){

        ArrayList<com.studiant.com.presentation.presenters.model.User> userArrayList = new ArrayList<>();
        for (int i = 0; i<domainUserArrayList.size();i++){
            userArrayList.add(convertToUserPresenterModel(domainUserArrayList.get(i)));
        }
        return userArrayList;
    }


    public static User convertToUserDomainModel(com.studiant.com.presentation.presenters.model.User user){

        if (user == null)
            return null;

        User domainUser = new User(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getIdExterne(),
                user.getProfilePicture(),
                user.getBirthday(),
                user.getDescription(),
                user.isPermis(),
                user.getDiplome(),
                user.getTypeConnexion(),
                user.getTypeUser(),
                user.getFirebaseToken());

        if (user.getFirebaseToken() != null)
            domainUser.setFirebaseToken(user.getFirebaseToken());

        if (user.getIdMangoPay() != null)
            domainUser.setIdMangoPay(user.getIdMangoPay());

        if (user.getTelephone() != null)
            domainUser.setTelephone(user.getTelephone());

        if (user.getPassword() != null)
            domainUser.setPassword(user.getPassword());

        if (user.getIdWallet() != null)
            domainUser.setIdWallet(user.getIdWallet());

        if (user.getIban() != null)
            domainUser.setIban(user.getIban());

        if (user.getIdIban() != null)
            domainUser.setIdIban(user.getIdIban());

        return domainUser;
    }

    public static com.studiant.com.presentation.presenters.model.Job convertToJobPresenterModel(Job job) {

        com.studiant.com.presentation.presenters.model.Job presenterJob = new com.studiant.com.presentation.presenters.model.Job(job.getId(),
                job.getDescription(),
                job.getPrix(),
                job.getAdresse(),
                job.getDate(),
                job.getHeure(),
                job.getUtilisateurId(),
                convertToUserPresenterModel(job.getUtilisateur()));

        if(job.getCategorie() != null)
            presenterJob.setCategorie(job.getCategorie());

        if (job.getCity() != null)
            presenterJob.setCity(job.getCity());

        if (job.getLat() != null && job.getLng() != null){
            presenterJob.setLat(job.getLat());
            presenterJob.setLng(job.getLng());
        }

        if (job.getMoyenPayment() != null)
            presenterJob.setModePaiement(job.getMoyenPayment());

        if (job.getStatutJob() != null)
            presenterJob.setStatut(job.getStatutJob());

        if (job.getPostulants() == null)
            return presenterJob;
        else{
            presenterJob.setPostulants(convertToArrayListPresenterUserModel(job.getPostulants()));
            return presenterJob;
        }


    }


    public static ArrayList<User> convertToArrayListDomainUserModel(ArrayList<com.studiant.com.presentation.presenters.model.User> domainUserArrayList){
        if (domainUserArrayList == null)
            return null;

        ArrayList<User> userArrayList = new ArrayList<>();
        for (int i = 0; i<domainUserArrayList.size();i++){
            userArrayList.add(convertToUserDomainModel(domainUserArrayList.get(i)));
        }
        return userArrayList;
    }


    public static Job convertToJobDomainModel(com.studiant.com.presentation.presenters.model.Job job) {
        Job domainJob = new Job(job.getId(),
                job.getDescription(),
                job.getPrix(),
                job.getAdresse(),
                job.getDate(),
                job.getHeure(),
                job.getUtilisateurId(),
                convertToUserDomainModel(job.getUtilisateur()),
                convertToArrayListDomainUserModel(job.getPostulants()));

        if (job.getStatut() != null)
            domainJob.setStatutJob(job.getStatut());

        if (job.getModePaiement() != null)
            domainJob.setMoyenPayment(job.getModePaiement());

        if (job.getLat() != null && job.getLng() != null){
            domainJob.setLat(job.getLat());
            domainJob.setLng(job.getLng());
        }

        return domainJob;

    }

    public static ArrayList<com.studiant.com.presentation.presenters.model.Job> convertToArrayListPresenterJobModel(ArrayList<Job> domainJobArrayList){

        ArrayList<com.studiant.com.presentation.presenters.model.Job> jobArrayList = new ArrayList<>();
        for (int i = 0; i<domainJobArrayList.size();i++){
            jobArrayList.add(convertToJobPresenterModel(domainJobArrayList.get(i)));
        }
        return jobArrayList;
    }

}

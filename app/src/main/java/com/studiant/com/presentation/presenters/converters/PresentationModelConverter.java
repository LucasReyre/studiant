package com.studiant.com.presentation.presenters.converters;

import android.app.Presentation;
import android.util.Log;

import com.studiant.com.domain.model.Job;
import com.studiant.com.domain.model.Postulant;
import com.studiant.com.domain.model.User;
import com.studiant.com.network.model.RESTJob;
import com.studiant.com.network.model.RESTPostulant;
import com.studiant.com.network.model.RESTUtilisateur;

import java.util.ArrayList;

/**
 * Created by groupama on 12/05/2017.
 */

public class PresentationModelConverter {


    public static com.studiant.com.presentation.presenters.model.User convertToUserPresenterModel(User user){
        if (user == null)
            return null;

        return new com.studiant.com.presentation.presenters.model.User(user.getId(),
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
                                                                        user.getTypeUser());
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

        return new User(user.getId(),
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
                user.getTypeUser());
    }

    public static com.studiant.com.presentation.presenters.model.Job convertToJobPresenterModel(Job job) {
        Log.d("convertToJobPresenterMo", " - "+job.getId());
        return new com.studiant.com.presentation.presenters.model.Job(job.getId(),
                                                                        job.getDescription(),
                                                                        job.getPrix(),
                                                                        job.getAdresse(),
                                                                        job.getDate(),
                                                                        job.getHeure(),
                                                                        job.getUtilisateurId(),
                                                                        convertToUserPresenterModel(job.getUtilisateur()) ,
                                                                        convertToArrayListPresenterUserModel(job.getPostulants()));
    }


    public static ArrayList<User> convertToArrayListDomainUserModel(ArrayList<com.studiant.com.presentation.presenters.model.User> domainUserArrayList){
        ArrayList<User> userArrayList = new ArrayList<>();
        for (int i = 0; i<domainUserArrayList.size();i++){
            userArrayList.add(convertToUserDomainModel(domainUserArrayList.get(i)));
        }
        return userArrayList;
    }


    public static Job convertToJobDomainModel(com.studiant.com.presentation.presenters.model.Job job) {
        return new Job(job.getId(),
                job.getDescription(),
                job.getPrix(),
                job.getAdresse(),
                job.getDate(),
                job.getHeure(),
                job.getUtilisateurId(),
                convertToUserDomainModel(job.getUtilisateur()),
                convertToArrayListDomainUserModel(job.getPostulants()));

    }

    public static ArrayList<com.studiant.com.presentation.presenters.model.Job> convertToArrayListPresenterJobModel(ArrayList<Job> domainJobArrayList){

        ArrayList<com.studiant.com.presentation.presenters.model.Job> jobArrayList = new ArrayList<>();
        for (int i = 0; i<domainJobArrayList.size();i++){
            jobArrayList.add(convertToJobPresenterModel(domainJobArrayList.get(i)));
        }
        return jobArrayList;
    }

}

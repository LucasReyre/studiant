package com.studiant.com.network.converters;

import android.net.Uri;

import com.studiant.com.domain.model.User;
import com.studiant.com.network.model.RESTUtilisateur;

/**
 * Created by groupama on 12/05/2017.
 */

public class RESTModelConverter {


    public static User convertToUserModel(RESTUtilisateur restUtilisateur){
        String nomUser = restUtilisateur.getmNomUtilisateur();
        String prenomUser = restUtilisateur.getmPrenomUtilisateur();
        String dateNaissanceUser = restUtilisateur.getmDateNaissanceUtilisateur();
        Uri photoUser = Uri.parse(restUtilisateur.getmPhotoUtilisateur());
        String mailUser = restUtilisateur.getmMailUtilisateur();
        int typeUser = restUtilisateur.getmTypeUtilisateur();
        String idExterne = restUtilisateur.getmIdExterneUtilisateur();
        int typeConnexion = restUtilisateur.getmTypeConnexionUtilisateur();
        String descriptionUser = restUtilisateur.getmDescriptionUtilisateur();
        String diplomeUser = restUtilisateur.getmDiplomeUtilisateur();
        boolean permisUser = restUtilisateur.getmPermisUtilisateur();

        return new User(prenomUser,nomUser,mailUser,idExterne,photoUser, dateNaissanceUser);
    }


    public static RESTUtilisateur convertToRestModel(User user) {

        String nomUser = user.getLastName();
        String prenomUser = user.getFirstName();
        String dateNaissanceUser = user.getBirthday();
        String photoUser = user.getProfilePicture().toString();
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



}

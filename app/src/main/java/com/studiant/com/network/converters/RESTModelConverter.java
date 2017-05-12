package com.studiant.com.network.converters;

import com.studiant.com.domain.model.User;
import com.studiant.com.network.model.RESTUtilisateur;

/**
 * Created by groupama on 12/05/2017.
 */

public class RESTModelConverter {

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

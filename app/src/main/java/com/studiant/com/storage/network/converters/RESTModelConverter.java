package com.studiant.com.storage.network.converters;

import com.studiant.com.domain.model.Card;
import com.studiant.com.domain.model.CardReg;
import com.studiant.com.domain.model.Job;
import com.studiant.com.domain.model.PayIn;
import com.studiant.com.domain.model.Postulant;
import com.studiant.com.domain.model.User;
import com.studiant.com.storage.network.model.RESTCard;
import com.studiant.com.storage.network.model.RESTCardReg;
import com.studiant.com.storage.network.model.RESTJob;
import com.studiant.com.storage.network.model.RESTPayIn;
import com.studiant.com.storage.network.model.RESTPostulant;
import com.studiant.com.storage.network.model.RESTUtilisateur;

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


        String firebaseToken = restUtilisateur.getMfirebaseToken();

        User user = new User(id, prenomUser, nomUser, mailUser,idExterne, photoUser, dateNaissanceUser, descriptionUser, diplomeUser,typeConnexion, typeUser, firebaseToken);

        if (restUtilisateur.getmPermisUtilisateur() != null){
            user.setPermis(restUtilisateur.getmPermisUtilisateur());
        }

        if (restUtilisateur.getmIdMangopay() != null)
            user.setIdMangoPay(restUtilisateur.getmIdMangopay());


        return user;
        //return new User(prenomUser,nomUser,mailUser,idExterne,photoUser, dateNaissanceUser);
    }


    public static PayIn convertToPayInModel(RESTPayIn restPayIn){
        return new PayIn(restPayIn.getStatus(),restPayIn.getCreationDate(),restPayIn.getCreditedWalletId(),restPayIn.getAuthorId());
    }

    public static Card convertToCardModel(RESTCard restCard){

        String id = restCard.getId();
        String alias = restCard.getAlias();
        String cardProvider = restCard.getCardProvider();
        String cardType = restCard.getCardType();
        String country = restCard.getCountry();
        String expiration = restCard.getExpirationDate();
        String validity = restCard.getValidity();
        String userId = restCard.getUserId();

        return new Card(expiration,alias,cardType,cardProvider,country,validity,userId,id);

    }

    public static CardReg convertToCardRegModel(RESTCardReg restCardReg){

        String clientId = restCardReg.getClientId();
        String accessKey = restCardReg.getAccessKey();
        String cardPreregistrationId = restCardReg.getCardPreregistrationId();
        String cardType = restCardReg.getCardType();
        String baseUrl = restCardReg.getBaseUrl();
        String cardRegistrationUrl = restCardReg.getCardRegistrationUrl();
        String preregistrationData = restCardReg.getPreregistrationData();

        return new CardReg(accessKey,baseUrl,cardPreregistrationId,cardRegistrationUrl,cardType,clientId,preregistrationData);
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
        String mangoPayId = user.getIdMangoPay();



        RESTUtilisateur restUtilisateur = new RESTUtilisateur( nomUser,
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

        if (user.getFirebaseToken() != null)
            restUtilisateur.setMfirebaseToken(user.getFirebaseToken());

        if (user.getIdMangoPay() != null)
            restUtilisateur.setmIdMangopay(user.getIdMangoPay());


        return restUtilisateur;

    }

    public static RESTJob convertToRestJobModel(Job job) {

        String mDescription = job.getDescription();
        String mPrix = job.getPrix();
        String mAdresseJob = job.getAdresse();
        String mDate = job.getDate();
        String mHeure = job.getHeure();
        String mUtilisateurId = job.getUtilisateurId();
        String mStatutJob = job.getStatutJob();
        String typePaiement = job.getMoyenPayment();

        RESTJob restJob = new RESTJob(mDescription,mPrix,mAdresseJob,mDate,mHeure,mUtilisateurId,mStatutJob, typePaiement);

        if (job.getPostulantId() != null)
            restJob.setmPostulantId(job.getPostulantId());

        return restJob;

    }

    public static RESTPostulant convertToRestPostulantModel(Postulant postulant) {

        String mTimePostulant = postulant.getTimePostulant();
        String mStatutPostulant = postulant.getStatutPostulant();
        String mJobId = postulant.getJob().getId();
        String mUtilisateurId = postulant.getUser().getId();

        return new RESTPostulant(mTimePostulant,mStatutPostulant,mJobId,mUtilisateurId);

    }

    public static Job convertToJobModel(RESTJob restJob){
        String id = restJob.getmId();
        String prix = restJob.getmPrixJob();
        String description = restJob.getmDescriptionJob();
        String adresse = restJob.getmAdresseJob();
        String date = restJob.getmDateJob();
        String heure = restJob.getmHeureJob();
        String utilisateurId = restJob.getmUtilisateurId();
        String typePaiementJob = restJob.getTypePaiementJob();

        ArrayList<User> postulantsArrayList = new ArrayList<User>();

        Job job =  new Job(id,description,prix ,adresse, date, heure, utilisateurId);

        job.setMoyenPayment(typePaiementJob);

        if (restJob.getmRestPostulant() != null){
            ArrayList<RESTUtilisateur> restPostulantsArrayList = restJob.getmRestPostulant();

            for (int i = 0; i<restPostulantsArrayList.size();i++){
                postulantsArrayList.add(convertToUserModel(restPostulantsArrayList.get(i)));
            }

            job.setPostulants(postulantsArrayList);
        }


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

package com.studiant.com.domain.interactors.impl;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.studiant.com.AndroidApplication;
import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.base.AbstractInteractor;
import com.studiant.com.domain.interactors.interfaces.SaveUserInteractor;
import com.studiant.com.presentation.presenters.model.User;
import com.studiant.com.storage.Constants;

import static com.studiant.com.storage.Constants.SHARED_PREFERENCE_NAME;
import static com.studiant.com.storage.Constants.SHARED_PREFERENCE_USER;


public class SaveUserInteractorImpl extends AbstractInteractor implements SaveUserInteractor {

    private User mUser;

    public SaveUserInteractorImpl(Executor threadExecutor,
                                  MainThread mainThread, User user) {
        super(threadExecutor, mainThread);
        mUser = user;
    }

    @Override
    public void run() {
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = AndroidApplication.getContext().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(SHARED_PREFERENCE_USER, null);
        User user = gson.fromJson(json, User.class);

        if (user == null){
            String userJson = gson.toJson(mUser);
            SharedPreferences.Editor editor = AndroidApplication.getContext().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE).edit();
            editor.putString(Constants.SHARED_PREFERENCE_USER, userJson);
            editor.commit();

            System.out.println("save : "+userJson);
            System.out.println("save : "+mUser.getId() + " "+ mUser.getFirstName() + " " +mUser.getTelephone());
        }else{
            if (mUser.getIdIban() != null)
                user.setIdIban(mUser.getIdIban());

            if (mUser.getIban() != null)
                user.setIban(mUser.getIban());

            if ((user.getTelephone() == null && mUser.getTelephone() != null)|| !user.getTelephone().equals(mUser.getTelephone()))
                user.setTelephone(mUser.getTelephone());

            if ((user.getEmail() == null && mUser.getEmail() != null)|| !user.getEmail().equals(mUser.getEmail()))
                user.setEmail(mUser.getEmail());

            if ((user.getDiplome() == null && mUser.getDiplome() != null) || !user.getDiplome().equals(mUser.getDiplome()))
                user.setDiplome(mUser.getDiplome());

            String userJson = gson.toJson(user);
            SharedPreferences.Editor editor = AndroidApplication.getContext().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE).edit();
            editor.putString(Constants.SHARED_PREFERENCE_USER, userJson);
            editor.commit();

            System.out.println("else save : "+userJson);
            System.out.println("else save : "+mUser.getId() + " "+ mUser.getFirstName() + " " +mUser.getTelephone());
        }



    }
}

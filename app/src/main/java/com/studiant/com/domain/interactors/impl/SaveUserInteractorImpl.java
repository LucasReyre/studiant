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
        String userJson = gson.toJson(mUser);
        SharedPreferences.Editor editor = AndroidApplication.getContext().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(Constants.SHARED_PREFERENCE_USER, userJson);
        editor.commit();

        System.out.println("save : "+mUser.getId() + " "+ mUser.getFirstName());
    }
}

package com.studiant.com.domain.interactors.impl;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.studiant.com.AndroidApplication;
import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.base.AbstractInteractor;
import com.studiant.com.domain.interactors.interfaces.DeleteDataUserInteractor;
import com.studiant.com.domain.interactors.interfaces.SaveUserInteractor;
import com.studiant.com.presentation.presenters.model.User;
import com.studiant.com.storage.Constants;

import static com.studiant.com.storage.Constants.SHARED_PREFERENCE_NAME;


public class DeleteDataUserInteractorImpl extends AbstractInteractor implements DeleteDataUserInteractor {

    public DeleteDataUserInteractorImpl(Executor threadExecutor,
                                        MainThread mainThread) {
        super(threadExecutor, mainThread);
    }

    @Override
    public void run() {
        SharedPreferences.Editor editor = AndroidApplication.getContext().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE).edit();
        editor.remove(Constants.SHARED_PREFERENCE_USER).commit();
    }
}

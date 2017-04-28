package com.studiant.com.domain.interactors.interfaces;


import com.studiant.com.domain.interactors.base.Interactor;
import com.studiant.com.domain.model.User;


public interface GetProfileInteractor extends Interactor {

    interface Callback {
        void onProfileRetrieve(User user);
        void onRetrievalFailed(String error);
    }
}

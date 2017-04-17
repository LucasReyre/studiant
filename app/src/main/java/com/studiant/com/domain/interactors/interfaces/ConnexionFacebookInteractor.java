package com.studiant.com.domain.interactors.interfaces;


import com.studiant.com.domain.interactors.base.Interactor;


public interface ConnexionFacebookInteractor extends Interactor {

    interface Callback {
        void onConnexionFacebookSuccess();
        void onRetrievalFailed(String error);
    }
}

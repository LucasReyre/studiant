package com.studiant.com.domain.interactors;


import com.studiant.com.domain.interactors.base.Interactor;


public interface WelcomingInteractor extends Interactor {

    interface Callback {
        void onMessageRetrieved(String message);

        void onRetrievalFailed(String error);
    }
}

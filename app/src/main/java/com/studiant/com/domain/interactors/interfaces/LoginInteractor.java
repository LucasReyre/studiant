package com.studiant.com.domain.interactors.interfaces;


import com.studiant.com.domain.interactors.base.Interactor;
import com.studiant.com.domain.model.User;


public interface LoginInteractor extends Interactor {

    interface Callback {
        void onLoginSuccess(User user);
        void onLoginFailed(String error);
    }
}

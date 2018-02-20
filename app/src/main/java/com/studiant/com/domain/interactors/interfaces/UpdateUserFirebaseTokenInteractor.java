
package com.studiant.com.domain.interactors.interfaces;


import com.studiant.com.domain.interactors.base.Interactor;
import com.studiant.com.domain.model.User;


public interface UpdateUserFirebaseTokenInteractor extends Interactor {

    interface Callback {
        void onUserFirebaseTokenUpdate(User user);
        void onUpdateFailed(String error);
    }
}

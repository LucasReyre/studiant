
package com.studiant.com.domain.interactors.interfaces;


import com.studiant.com.domain.interactors.base.Interactor;
import com.studiant.com.domain.model.User;


public interface UpdateUserInteractor extends Interactor {

    interface Callback {
        void onUserUpdate(User user);
        void onUpdateFailed(String error);
    }
}

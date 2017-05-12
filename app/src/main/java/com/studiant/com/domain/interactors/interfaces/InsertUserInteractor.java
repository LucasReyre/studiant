
package com.studiant.com.domain.interactors.interfaces;


import com.studiant.com.domain.interactors.base.Interactor;
import com.studiant.com.domain.model.User;


public interface InsertUserInteractor extends Interactor {

    interface Callback {
        void onUserInsert();
        void onRetrievalFailed(String error);
    }
}


package com.studiant.com.domain.interactors.interfaces;


import com.studiant.com.domain.interactors.base.Interactor;


public interface GetPasswordInteractor extends Interactor {

    interface Callback {
        void onPasswordRetrieve();
        void onPasswordRetrievalFailed(String error);
    }
}

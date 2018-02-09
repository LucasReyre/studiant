
package com.studiant.com.domain.interactors.interfaces;


import com.studiant.com.domain.interactors.base.Interactor;


public interface GetPostulantInteractor extends Interactor {

    interface Callback {
        void onPostulantRetrieve();
        void onPostulantRetrievalFailed(String error);
        void onPostulantRequestError(String error);
    }
}

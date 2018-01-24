
package com.studiant.com.domain.interactors.interfaces;


import com.studiant.com.domain.interactors.base.Interactor;


public interface GetMoneyInteractor extends Interactor {

    interface Callback {
        void onMoneyRetrieve();
        void onMoneyRetrievalFailed(String error);
    }
}

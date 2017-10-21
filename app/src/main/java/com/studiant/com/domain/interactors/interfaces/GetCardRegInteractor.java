
package com.studiant.com.domain.interactors.interfaces;


import com.studiant.com.domain.interactors.base.Interactor;
import com.studiant.com.domain.model.CardReg;


public interface GetCardRegInteractor extends Interactor {

    interface Callback {
        void onCardRegRetrieve(CardReg cardReg);
        void onCardRegRetrievalFailed(String error);
    }
}

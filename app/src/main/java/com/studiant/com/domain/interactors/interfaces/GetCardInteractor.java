
package com.studiant.com.domain.interactors.interfaces;


import com.studiant.com.domain.interactors.base.Interactor;
import com.studiant.com.domain.model.Job;

import java.util.ArrayList;


public interface GetCardInteractor extends Interactor {

    interface Callback {
        void onCardRetrieve();
        void onCardRetrievalFailed(String error);
    }
}

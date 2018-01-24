
package com.studiant.com.domain.interactors.interfaces;


import com.studiant.com.domain.interactors.base.Interactor;
import com.studiant.com.domain.model.Job;


public interface GetPaiementInteractor extends Interactor {

    interface Callback {
        void onPaiementSuccess(Job job);
        void onPaiementFailed(String error);
    }
}

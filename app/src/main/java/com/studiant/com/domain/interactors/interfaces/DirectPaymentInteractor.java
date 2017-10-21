
package com.studiant.com.domain.interactors.interfaces;


import com.studiant.com.domain.interactors.base.Interactor;


public interface DirectPaymentInteractor extends Interactor {

    interface Callback {
        void onDirectPaymentAccepted();
        void onDirectPaymentFailed(String error);
    }
}


package com.studiant.com.domain.interactors.interfaces;


import com.studiant.com.domain.interactors.base.Interactor;


public interface CloseJobInteractor extends Interactor {

    interface Callback {
        void onJobClose();
        void onCloseFailed(String error);
    }
}

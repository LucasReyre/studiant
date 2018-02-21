
package com.studiant.com.domain.interactors.interfaces;


import com.studiant.com.domain.interactors.base.Interactor;
import com.studiant.com.domain.model.Job;


public interface DeleteJobInteractor extends Interactor {

    interface Callback {
        void onJobDelete();
        void onDeleteFailed(String error);
    }
}

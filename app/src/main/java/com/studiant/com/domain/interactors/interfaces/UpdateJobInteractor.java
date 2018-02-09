
package com.studiant.com.domain.interactors.interfaces;


import com.studiant.com.domain.interactors.base.Interactor;
import com.studiant.com.domain.model.Job;
import com.studiant.com.domain.model.User;


public interface UpdateJobInteractor extends Interactor {

    interface Callback {
        void onJobUpdate(Job job);
        void onUpdateFailed(String error);
    }
}

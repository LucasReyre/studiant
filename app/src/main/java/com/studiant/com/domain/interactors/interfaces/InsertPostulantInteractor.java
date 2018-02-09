
package com.studiant.com.domain.interactors.interfaces;


import com.studiant.com.domain.interactors.base.Interactor;
import com.studiant.com.presentation.presenters.model.Job;
import com.studiant.com.presentation.presenters.model.User;
import com.studiant.com.storage.network.WSException;


public interface InsertPostulantInteractor extends Interactor {

    interface Callback {
        void onPostulantInsert(Job job, User user);
        void onPostulantInsertFailed(WSException e);
    }
}

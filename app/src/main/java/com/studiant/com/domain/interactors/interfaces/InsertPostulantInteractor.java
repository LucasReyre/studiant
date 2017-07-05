
package com.studiant.com.domain.interactors.interfaces;


import com.studiant.com.domain.interactors.base.Interactor;
import com.studiant.com.presentation.presenters.model.Job;


public interface InsertPostulantInteractor extends Interactor {

    interface Callback {
        void onPostulantInsert(Job job);
        void onPostulantInsertFailed(String error);
    }
}

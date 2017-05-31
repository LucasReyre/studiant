
package com.studiant.com.domain.interactors.interfaces;


import com.studiant.com.domain.interactors.base.Interactor;


public interface InsertPostulantInteractor extends Interactor {

    interface Callback {
        void onPostulantInsert();
        void onPostulantInsertFailed(String error);
    }
}


package com.studiant.com.domain.interactors.interfaces;


import com.studiant.com.domain.interactors.base.Interactor;
import com.studiant.com.domain.model.User;


public interface ChoosePostulantInteractor extends Interactor {

    interface Callback {
        void onPostulantInsert();
        void onInsertFailed(String error);
    }
}

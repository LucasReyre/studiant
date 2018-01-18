
package com.studiant.com.domain.interactors.interfaces;


import com.studiant.com.domain.interactors.base.Interactor;
import com.studiant.com.domain.model.Rib;


public interface InsertRibInteractor extends Interactor {

    interface Callback {
        void onRibInsert(Rib rib);
        void onRibInsertFailed(String error);
    }
}

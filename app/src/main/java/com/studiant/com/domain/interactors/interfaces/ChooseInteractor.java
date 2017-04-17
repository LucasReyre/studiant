package com.studiant.com.domain.interactors.interfaces;


import com.studiant.com.domain.interactors.base.Interactor;


public interface ChooseInteractor extends Interactor {

    interface Callback {
        void onListCategoryRetrieved(String[] listItem);
        void onRetrievalFailed(String error);
    }
}

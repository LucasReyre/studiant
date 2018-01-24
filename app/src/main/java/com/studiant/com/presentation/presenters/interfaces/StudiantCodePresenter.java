package com.studiant.com.presentation.presenters.interfaces;

import com.studiant.com.presentation.presenters.base.BasePresenter;
import com.studiant.com.presentation.presenters.model.Job;
import com.studiant.com.presentation.ui.BaseView;


public interface StudiantCodePresenter extends BasePresenter {

    void getPaiement(String postulantId, String jobId);

    interface View extends BaseView {
        void onPaiementSuccess(Job job);
        void onPaiementError();
    }
}

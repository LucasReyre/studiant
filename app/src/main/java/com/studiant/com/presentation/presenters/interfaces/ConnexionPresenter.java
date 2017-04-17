package com.studiant.com.presentation.presenters.interfaces;

import com.studiant.com.presentation.presenters.base.BasePresenter;
import com.studiant.com.presentation.ui.BaseView;


public interface ConnexionPresenter extends BasePresenter {


    void setFacebookData();

    interface View extends BaseView {
        void onConnexionFacebookSuccess();
    }
}

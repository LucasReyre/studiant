package com.studiant.com.presentation.presenters.interfaces;

import com.studiant.com.presentation.presenters.base.BasePresenter;
import com.studiant.com.presentation.ui.BaseView;


public interface MainPresenter extends BasePresenter {

    interface View extends BaseView {
        void displayWelcomeMessage(String msg);
    }
}

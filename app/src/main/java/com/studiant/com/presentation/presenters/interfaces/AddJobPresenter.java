package com.studiant.com.presentation.presenters.interfaces;

import com.studiant.com.presentation.presenters.base.BasePresenter;
import com.studiant.com.presentation.ui.BaseView;


public interface AddJobPresenter extends BasePresenter {

    interface View extends BaseView {
        void displayListCategorie(String[] listItem);
    }
}

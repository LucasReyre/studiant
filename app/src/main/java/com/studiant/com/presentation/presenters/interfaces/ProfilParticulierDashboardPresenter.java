package com.studiant.com.presentation.presenters.interfaces;

import com.studiant.com.presentation.presenters.base.BasePresenter;
import com.studiant.com.presentation.presenters.model.User;
import com.studiant.com.presentation.ui.BaseView;


public interface ProfilParticulierDashboardPresenter extends BasePresenter {

    void saveUser(User user);

    interface View extends BaseView {
        void onUserUpdate();
        void onUserUpdateFailed();
    }
}

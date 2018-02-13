package com.studiant.com.presentation.presenters.interfaces;

import com.studiant.com.domain.model.User;
import com.studiant.com.presentation.presenters.base.BasePresenter;
import com.studiant.com.presentation.ui.BaseView;


public interface LoginPresenter extends BasePresenter {
    void login(String mail, String password);
    void getMdp(String mail);

    interface View extends BaseView {
        void onLoginSuccess(User user);
        void onLoginFailed(String error);
    }
}

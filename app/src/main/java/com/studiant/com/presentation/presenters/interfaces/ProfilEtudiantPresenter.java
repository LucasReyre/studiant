package com.studiant.com.presentation.presenters.interfaces;

import com.studiant.com.presentation.presenters.base.BasePresenter;
import com.studiant.com.presentation.presenters.model.User;
import com.studiant.com.presentation.ui.BaseView;


public interface ProfilEtudiantPresenter extends BasePresenter {

    void getMoney(User user);
    void saveUser(User user);

    interface View extends BaseView {
        void onMoneyRetrieve();
        void onMoneyRetrievedFail();
        void onUserUpdate();
        void onUserUpdateFailed();
    }
}

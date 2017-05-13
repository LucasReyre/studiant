package com.studiant.com.presentation.presenters.interfaces;

import com.studiant.com.domain.model.User;
import com.studiant.com.presentation.presenters.base.BasePresenter;
import com.studiant.com.presentation.ui.BaseView;


public interface SplashPresenter extends BasePresenter {

    interface View extends BaseView {
        void onProfileRetrieve(User user);
        void onRetrievalFailed();
    }
}

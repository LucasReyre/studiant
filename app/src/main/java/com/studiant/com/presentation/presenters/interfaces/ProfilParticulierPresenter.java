package com.studiant.com.presentation.presenters.interfaces;

import com.studiant.com.presentation.presenters.base.BasePresenter;
import com.studiant.com.presentation.presenters.model.User;
import com.studiant.com.presentation.ui.BaseView;


public interface ProfilParticulierPresenter extends BasePresenter {

    void getFacebookData();
    void saveUser(User user);
    void insertProfile(User user);
    void uploadImage(String image);

    interface View extends BaseView {
        void onProfileRetrieve(User user);
        void onImageUpload(String urlImage);
        void onUserInsert(User user);
    }
}

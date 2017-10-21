package com.studiant.com.presentation.presenters.interfaces;

import com.studiant.com.domain.model.Job;
import com.studiant.com.presentation.presenters.base.BasePresenter;
import com.studiant.com.presentation.presenters.model.User;
import com.studiant.com.presentation.ui.BaseView;


public interface AddJobPresenter extends BasePresenter {

    void insertJob(Job job);
    void getCard(User user);

    interface View extends BaseView {
        void displayListCategorie(String[] listItem);
        void displayAddCard();
        void displayPayment();
        void onJobInsert();
    }
}

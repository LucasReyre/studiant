package com.studiant.com.presentation.presenters.interfaces;

import com.studiant.com.domain.model.Card;
import com.studiant.com.domain.model.CardReg;
import com.studiant.com.domain.model.Job;
import com.studiant.com.presentation.presenters.base.BasePresenter;
import com.studiant.com.presentation.presenters.model.User;
import com.studiant.com.presentation.ui.BaseView;


public interface AddCbPresenter extends BasePresenter {

    void insertCard(Card card);
    void getCardReg(User user);

    interface View extends BaseView {
        void onGetCardReg(CardReg cardReg);
    }
}

package com.studiant.com.presentation.presenters.interfaces;

import com.studiant.com.domain.model.Card;
import com.studiant.com.domain.model.CardReg;
import com.studiant.com.presentation.presenters.base.BasePresenter;
import com.studiant.com.presentation.presenters.model.User;
import com.studiant.com.presentation.ui.BaseView;


public interface ConfirmPaymentPresenter extends BasePresenter {

    void directePayment(String mangoPayId, String amount);

    interface View extends BaseView {
        void onPaymentAccepted();
    }
}

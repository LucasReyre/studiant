package com.studiant.com.presentation.presenters.interfaces;

import com.studiant.com.domain.model.Job;
import com.studiant.com.domain.model.Rib;
import com.studiant.com.presentation.presenters.base.BasePresenter;
import com.studiant.com.presentation.presenters.model.User;
import com.studiant.com.presentation.ui.BaseView;


public interface AddRibPresenter extends BasePresenter {

    void insertRib(Rib rib);

    interface View extends BaseView {
        void onRibInsert(Rib rib);
        void onRibError();
    }
}
